package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.products.domain.OrdersByDay;

import java.util.List;

@Client("/orders_by_day")
public interface OrdersByDayClient {

    @Get
    List<OrdersByDay> getOrdersByDay();

    @Get("/id/{id}")
    OrdersByDay getOrdersByDayById(@PathVariable Long id);

    @Get("/day/{day}")
    List<OrdersByDay> getOrdersByDayByDay(@PathVariable String day);

    @Get("/product/{productId}")
    List<OrdersByDay> getOrdersByDayByProduct(@PathVariable Long productId);

    @Get("/product/{productId}/day/{day}")
    HttpResponse<OrdersByDay> getOrdersByDayByProductAndDay(@PathVariable Long productId, @PathVariable String day);

}
