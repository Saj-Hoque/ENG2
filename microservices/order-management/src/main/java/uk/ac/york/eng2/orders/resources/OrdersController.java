package uk.ac.york.eng2.orders.resources;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.cs.eng2.products.api.PricingApi;
import uk.ac.york.cs.eng2.products.model.OrderRequestDTO;
import uk.ac.york.cs.eng2.products.model.OrderRequestDTOProductOrder;
import uk.ac.york.cs.eng2.products.model.OrderResponseDTO;
import uk.ac.york.cs.eng2.products.model.OrderResponseDTOProductPrice;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.domain.OrderItem;
import uk.ac.york.eng2.orders.dto.OrderCreateDTO;
import uk.ac.york.eng2.orders.dto.OrderUpdateDTO;
import uk.ac.york.eng2.orders.events.OrdersProducer;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderItemRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@io.swagger.v3.oas.annotations.tags.Tag(name="orders")
@Controller(OrdersController.PREFIX)
public class OrdersController {
    public static final String PREFIX = "/orders";

    @Inject
    private OrderRepository orderRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private PricingApi pricingApi;
    @Inject
    private OrdersProducer ordersProducer;
    @Inject
    private OrderItemRepository orderItemRepository;


    // List all orders
    @Get
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // Retrieve order by id
    @Get("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    // Retrieve customer of order id
    @Get("/{id}/customer")
    public HttpResponse<Customer> getOrderCustomer(@PathVariable Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        // If the order exists, return the customer associated with the order
        Customer customer = customerRepository.findByOrdersId(id).orElse(null);;
        return HttpResponse.ok(customer);
    }

    @Get("/{id}/orderItems")
    public HttpResponse<List<OrderItem>> getOrderItems(@PathVariable Long id) {

        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        // If the order exists, return the order items associated with the order
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);
        return HttpResponse.ok(orderItems);

    }

    // Create a new order
    @Post
    @ExecuteOn(TaskExecutors.BLOCKING)
    public HttpResponse<Void> createOrder(@Body OrderCreateDTO dto) {
        Order order = new Order();

        order.setDateCreated(LocalDate.now());
        order.setAddress(dto.getAddress());
        order.setPaid(false);
        order.setDelivered(false);

        if (dto.getCustomerId() != null) {
            @NonNull Optional<Customer> oCustomer = customerRepository.findById(dto.getCustomerId());
            if (oCustomer.isEmpty()) {
                throw new HttpStatusException(HttpStatus.NOT_FOUND, "Customer not found");
            }
            order.setCustomer(oCustomer.get());
        } else {
            throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Customer id must be provided");
        }

        Set<Long> uniqueProductIds = new HashSet<>();
        for (OrderRequestDTOProductOrder productOrder : dto.getOrderRequest().getOrder()) {
            if (!uniqueProductIds.add(productOrder.getProductId())) {
                // Duplicate productId detected
                throw new HttpStatusException(HttpStatus.BAD_REQUEST, "Duplicate productId found: " + productOrder.getProductId() + ". Please group quantities before submitting.");
            }
        }


        // Calculate totalAmount by invoking PM (OpenAPI)
        OrderRequestDTO request = dto.getOrderRequest();
        HttpResponse<OrderResponseDTO> response = pricingApi.priceCalculator(request);
        if (!response.status().equals(HttpStatus.OK)) {
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error invoking PM");
        }

        order.setTotalAmount(response.body().getOrderTotalPrice());
        order = orderRepository.save(order);

        // Update OrderItem data according to response
        for (OrderResponseDTOProductPrice productPrice : response.body().getProductPrices()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(productPrice.getProductId());
            orderItem.setQuantity(productPrice.getQuantity());
            orderItem.setUnitPrice(productPrice.getUnitPrice());
            orderItemRepository.save(orderItem);
        }

        // Send order-created event to PM through Kafka
        for (OrderRequestDTOProductOrder productOrder : request.getOrder()) {
            ordersProducer.orderCreated(productOrder.getProductId(), order.getDateCreated().toString());
        }

        return HttpResponse.created(URI.create(PREFIX + "/" + order.getId()));
    }

    // Update an order (by id)
    // "Orders are final once confirmed, and the only option is to cancel them" hence only `paid` and `delivered` can be updated.
    @Transactional
    @Put("/{id}")
    public void updateOrder(@Body OrderUpdateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Order> oOrder = orderRepository.findById(id);
        if (oOrder.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        Order order = oOrder.get();
        order.setPaid(dto.getPaid());
        order.setDelivered(dto.getDelivered());
        orderRepository.save(order);
    }

    // Delete an order (by id)
    @Delete("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            orderItemRepository.deleteByOrderId(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

}
