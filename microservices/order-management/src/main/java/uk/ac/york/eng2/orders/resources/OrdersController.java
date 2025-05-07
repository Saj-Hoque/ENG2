package uk.ac.york.eng2.orders.resources;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Customer getOrderCustomer(@PathVariable Long id) { return customerRepository.findByOrdersId(id).orElse(null); }

    // Create a new order TODO: Need to do the calcualtion shit
    @Post
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
        }

        order.setTotalAmount(100F);
        order = orderRepository.save(order);
        return HttpResponse.created(URI.create(PREFIX + "/" + order.getId()));
    }

    // Update an order (by id) TODO: update calcualation shit
    @Transactional
    @Put("/{id}")
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
        }

        order.setTotalAmount(100F);
        orderRepository.save(order);
    }

    // Delete a order (by id)
    @Delete("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
    }

}
