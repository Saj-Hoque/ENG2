package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.domain.OrderItem;
import uk.ac.york.eng2.orders.dto.OrderCreateDTO;
import uk.ac.york.eng2.orders.dto.OrderUpdateDTO;

import java.util.List;

@Client("/orders")
public interface OrdersClient {

    @Get
    List<Order> getOrders();

    @Get("/{id}")
    Order getOrder(@PathVariable Long id);

    @Get("/{id}/customer")
    HttpResponse<Customer> getOrderCustomer(@PathVariable Long id);

    @Get("/{id}/orderItems")
    HttpResponse<List<OrderItem>> getOrderItems(@PathVariable Long id);

    @Post
    HttpResponse<Void> createOrder(@Body OrderCreateDTO dto);

    @Put("/{id}")
    HttpResponse<Void> updateOrder(@Body OrderUpdateDTO dto, @PathVariable Long id);

    @Delete("/{id}")
    HttpResponse<Void> deleteOrder(@PathVariable Long id);

}
