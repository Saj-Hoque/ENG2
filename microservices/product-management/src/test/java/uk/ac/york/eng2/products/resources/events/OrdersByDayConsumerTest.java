package uk.ac.york.eng2.products.resources.events;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.events.OrdersByDayConsumer;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@MicronautTest(transactional = false)
public class OrdersByDayConsumerTest {

    @Inject
    private OrdersByDayConsumer consumer;


    @Inject
    private OrdersByDayRepository repository;

    @Inject
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {

        productRepository.deleteAll();
        repository.deleteAll();
    }


    @Test
    public void productDoesNotExist(){
        consumer.updateOrdersByDay(999L, LocalDate.now().toString());
        assertEquals(0, repository.findAll().size());
    }

    @Test
    public void orderCountsUpdated(){
        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        consumer.updateOrdersByDay(product.getId(), LocalDate.now().toString());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    public void orderCountsUpdatedByProduct() {
        LocalDate day = LocalDate.now();
        String dayString = day.toString();

        Product product1 = new Product();
        product1.setName("Test");
        product1.setUnitPrice(1.0F);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Test");
        product2.setUnitPrice(1.0F);
        productRepository.save(product2);


        consumer.updateOrdersByDay(product1.getId(), dayString);
        consumer.updateOrdersByDay(product1.getId(), dayString);
        consumer.updateOrdersByDay(product2.getId(), dayString);

        OrdersByDay product1Day1Orders = repository.findByProductIdAndDay(product1.getId(), day).orElse(null);
        OrdersByDay product2Day1Orders = repository.findByProductIdAndDay(product2.getId(), day).orElse(null);

        assertEquals(2, product1Day1Orders.getCount());
        assertEquals(1, product2Day1Orders.getCount());
    }

    @Test
    public void orderCountsUpdatedByDay() {
        LocalDate day1 = LocalDate.ofYearDay(2021, 1);
        String day1String = day1.toString();

        LocalDate day2 = LocalDate.ofYearDay(2021, 2);
        String day2String = day2.toString();

        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        productRepository.save(product);

        consumer.updateOrdersByDay(product.getId(), day1String);
        consumer.updateOrdersByDay(product.getId(), day1String);
        consumer.updateOrdersByDay(product.getId(), day2String);

        OrdersByDay product1Day1Orders = repository.findByProductIdAndDay(product.getId(), day1).orElse(null);
        OrdersByDay product1Day2Orders = repository.findByProductIdAndDay(product.getId(), day2).orElse(null);

        assertEquals(2, product1Day1Orders.getCount());
        assertEquals(1, product1Day2Orders.getCount());
    }

}
