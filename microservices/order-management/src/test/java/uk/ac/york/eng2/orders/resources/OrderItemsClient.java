package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.domain.OrderItem;

import java.util.List;

@Client("/order_item")
public interface OrderItemsClient {

    @Get
    List<OrderItem> getOrderItems();

    @Get("/{id}")
    OrderItem getOrderItem(@PathVariable Long id);

    @Get("/{id}/order")
    HttpResponse<Order> getOrderItemOrder(@PathVariable Long id);
}
