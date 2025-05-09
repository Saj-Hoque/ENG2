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
import uk.ac.york.cs.eng2.products.model.OrderResponseDTO;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.dto.OrderCreateDTO;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

        // Calculate totalAmount by invoking PM (OpenAPI)
        OrderRequestDTO request = dto.getOrderRequest();
        HttpResponse<OrderResponseDTO> response = pricingApi.priceCalculator(request);
        if (!response.status().equals(HttpStatus.OK)) {
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error invoking PM");
        }

        // Update OrderItem
        // TODO:


        order.setTotalAmount(response.body().getOrderTotalPrice());
        order = orderRepository.save(order);
        return HttpResponse.created(URI.create(PREFIX + "/" + order.getId()));
    }

    // Update an order (by id)
    @Transactional
    @Put("/{id}")
    @ExecuteOn(TaskExecutors.BLOCKING)
    public void updateOrder(@Body OrderCreateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Order> oOrder = orderRepository.findById(id);
        if (oOrder.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        Order order = oOrder.get();
        // This will cause the order date to also be updated to current date.
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

        OrderRequestDTO request = dto.getOrderRequest();
        HttpResponse<OrderResponseDTO> response = pricingApi.priceCalculator(request);
        if (!response.status().equals(HttpStatus.OK)) {
            throw new HttpStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error calling PM");
        }

        // Update OrderItem
        // TODO:

        order.setTotalAmount(response.body().getOrderTotalPrice());
        orderRepository.save(order);
    }

    // Delete an order (by id)
    @Delete("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

}
