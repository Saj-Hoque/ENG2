package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(transactional = false)
public class OrdersByDayControllerTest {

    @Inject
    private OrdersByDayClient ordersByDayClient;

    @Inject
    private OrdersByDayRepository ordersByDayRepository;

    @Inject
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        ordersByDayRepository.deleteAll();
        productRepository.deleteAll();
    }

    // Test that ordersByDayRepository is empty
    @Test
    public void noOrdersByDay() {
        assertEquals(0, ordersByDayClient.getOrdersByDay().size());
    }

    // Test retrieving all ordersByDay
    @Test
    public void fetchOrdersByDay() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        assertEquals(1, ordersByDayClient.getOrdersByDay().size());

    }

    // Test retrieving all ordersByDay by ID
    @Test
    public void fetchOrdersByDayById() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        OrdersByDay fetchedOrdersByDay = ordersByDayClient.getOrdersByDayById(ordersByDay.getId());
        assertEquals(ordersByDay.getId(), fetchedOrdersByDay.getId());
        assertEquals(ordersByDay.getDay(), fetchedOrdersByDay.getDay());
        assertEquals(ordersByDay.getCount(), fetchedOrdersByDay.getCount());
        assertEquals(ordersByDay.getProduct().getId(), fetchedOrdersByDay.getProduct().getId());

    }

    // Test retrieving all ordersByDay by day
    @Test
    public void fetchOrdersByDayByDay() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        List<OrdersByDay> fetchedListOfOrdersByDay = ordersByDayClient.getOrdersByDayByDay(ordersByDay.getDay().toString());
        OrdersByDay fetchedOrdersByDay = fetchedListOfOrdersByDay.get(0);

        assertEquals(ordersByDay.getId(), fetchedOrdersByDay.getId());
        assertEquals(ordersByDay.getDay(), fetchedOrdersByDay.getDay());
        assertEquals(ordersByDay.getCount(), fetchedOrdersByDay.getCount());
        assertEquals(ordersByDay.getProduct().getId(), fetchedOrdersByDay.getProduct().getId());

    }

    // Test retrieving all ordersByDay by productId
    @Test
    public void fetchOrdersByDayByProductId() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        List<OrdersByDay> fetchedListOfOrdersByDay = ordersByDayClient.getOrdersByDayByProduct(product.getId());
        OrdersByDay fetchedOrdersByDay = fetchedListOfOrdersByDay.get(0);

        assertEquals(ordersByDay.getId(), fetchedOrdersByDay.getId());
        assertEquals(ordersByDay.getDay(), fetchedOrdersByDay.getDay());
        assertEquals(ordersByDay.getCount(), fetchedOrdersByDay.getCount());
        assertEquals(ordersByDay.getProduct().getId(), fetchedOrdersByDay.getProduct().getId());

    }

    // Test retrieving all ordersByDay by productId
    @Test
    public void fetchOrdersByDayByProductIdAndDay() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        HttpResponse<OrdersByDay> response = ordersByDayClient.getOrdersByDayByProductAndDay(product.getId(), ordersByDay.getDay().toString());
        assertEquals(HttpStatus.OK, response.getStatus());

        OrdersByDay fetchedOrdersByDay = response.body();

        assertEquals(ordersByDay.getId(), fetchedOrdersByDay.getId());
        assertEquals(ordersByDay.getDay(), fetchedOrdersByDay.getDay());
        assertEquals(ordersByDay.getCount(), fetchedOrdersByDay.getCount());
        assertEquals(ordersByDay.getProduct().getId(), fetchedOrdersByDay.getProduct().getId());

    }

    // Test retrieving all ordersByDay by productId
    @Test
    public void fetchOrdersByDayByProductIdAndDayMissing() {

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDayRepository.save(ordersByDay);

        // Missing productId
        HttpResponse<OrdersByDay> response = ordersByDayClient.getOrdersByDayByProductAndDay(123L, ordersByDay.getDay().toString());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());

        // Missing day
        HttpResponse<OrdersByDay> response2 = ordersByDayClient.getOrdersByDayByProductAndDay(product.getId(), LocalDate.now().plusDays(1).toString() );
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatus());


    }






}
