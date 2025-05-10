package uk.ac.york.eng2.orders.resources;


import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.domain.OrderItem;
import uk.ac.york.eng2.orders.repository.OrderItemRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.util.List;
import java.util.Optional;


@io.swagger.v3.oas.annotations.tags.Tag(name="order_item")
@Controller(OrderItemsController.PREFIX)
public class OrderItemsController {
    public static final String PREFIX = "/order_item";

    @Inject
    private OrderItemRepository orderItemRepository;
    @Inject
    private OrderRepository orderRepository;

    // List all order items
    @Get
    public List<OrderItem> getOrderItems() { return orderItemRepository.findAll(); }

    // Retrieve orderItem by id
    @Get("/{id}")
    public OrderItem getOrderItemById(Long id) { return orderItemRepository.findById(id).orElse(null); }

    // Retrieve order of orderItem id
    @Get("/{id}/order")
    public HttpResponse<Order> getOrderItemOrder(@PathVariable Long id) {

        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Order Item not found");
        }
        // If the order item exists, return the order associated with the order item
        Order order = orderRepository.findByOrderItemsId(id).orElse(null);
        return HttpResponse.ok(order);
    }

    // Should not have endpoint to create an orderItem - This will be done during order creation.
    // "Orders are final once confirmed, and the only option is to cancel them", Henceforth:
        // Should not have endpoint to update an orderItem
    // Should not have endpoint to delete an orderItem - This will be done during order deletion

}