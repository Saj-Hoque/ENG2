package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.HttpStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Tag(name="orders_by_day")
@Controller(OrdersByDayController.PREFIX)
public class OrdersByDayController {
    public static final String PREFIX = "/orders_by_day";

    @Inject
    private OrdersByDayRepository ordersByDayRepository;


    // List all Orders By Day
    @Get
    public List<OrdersByDay> getOrdersByDay() { return ordersByDayRepository.findAll(); }

    // Retrieve OrderByDay by id
    @Get("/id/{id}")
    public OrdersByDay getOrdersByDayById(@PathVariable Long id) { return ordersByDayRepository.findById(id).orElse(null); }

    // Retrieve list of OrderByDay by day
    @Get("/day/{day}")
    public List<OrdersByDay> getOrdersByDayByDay(@PathVariable LocalDate day) {
        return ordersByDayRepository.findByDay(day); }

    // Retrieve list of OrderByDay by productId
    @Get("/product/{productId}")
    public List<OrdersByDay> getOrdersByDayByProduct(@PathVariable Long productId) {
        return ordersByDayRepository.findByProductId(productId); }

    // Retrieve OrderDay by (productId and day)
    @Get("/product/{productId}/day/{day}")
    public HttpResponse<OrdersByDay> getOrdersByDayByProductAndDay(@PathVariable Long productId, @PathVariable LocalDate day) {
        Optional<OrdersByDay> orderByProductByDay = ordersByDayRepository.findByProductIdAndDay(productId, day);
        if (orderByProductByDay.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Any orders related to such productId and Day were not found");
        }
        // If the order by product by day exists, return the orderByDay associated
        OrdersByDay o = orderByProductByDay.get();
        return HttpResponse.ok(o);
    }

    // Should not have endpoint to create an ordersByDay - This will be done during order creation.
    // "Orders are final once confirmed, and the only option is to cancel them", Henceforth:
    // Should not have endpoint to update an ordersByDay
    // Should not have endpoint to delete an ordersByDay - This is not within the specification.


}

