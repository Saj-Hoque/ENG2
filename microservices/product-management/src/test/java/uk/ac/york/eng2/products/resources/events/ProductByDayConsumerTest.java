package uk.ac.york.eng2.products.resources.events;

import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.events.ProductByDayOrderCreatedConsumer;
import uk.ac.york.eng2.products.events.ProductByDayOrderCreatedProducer;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@MicronautTest(transactional = false)
public class ProductByDayConsumerTest {

    @Inject
    private ProductByDayOrderCreatedConsumer consumer;

    @Inject
    private ProductByDayOrderCreatedProducer producer;

    @Inject
    private OrdersByDayRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @MockBean(ProductByDayOrderCreatedProducer.class)
    public ProductByDayOrderCreatedProducer getProducer() {
        return mock(ProductByDayOrderCreatedProducer.class);
    }

    @Test
    public void eventIsRekeyed() {

        List<OrderRequestDTO.ProductOrder> basket = new ArrayList<>();

        OrderRequestDTO.ProductOrder order1 = new OrderRequestDTO.ProductOrder();
        order1.setProductId(1L);
        order1.setQuantity(1);
        basket.add(order1);

        OrderRequestDTO.ProductOrder order2 = new OrderRequestDTO.ProductOrder();
        order2.setProductId(2L);
        order2.setQuantity(2);
        basket.add(order2);

        OrderRequestDTO dto = new OrderRequestDTO();
        dto.setOrder(basket);

        LocalDate day = LocalDate.now();

        consumer.orderCreatedEvent(123L, dto, day);


        verify(producer).orderCreated(
                eq(order1.getProductId()),
                eq(day)
        );

        verify(producer).orderCreated(
                eq(order2.getProductId()),
                eq(day)
        );
    }


    @Test
    public void orderCountsUpdatedbyProduct() {
        LocalDate day = LocalDate.now();

        consumer.updateOrdersByDay(1L, day);
        consumer.updateOrdersByDay(1L, day);
        consumer.updateOrdersByDay(2L, day);

        OrdersByDay product1Day1Orders = repository.findByProductIdAndDay(1L, day).orElse(null);
        OrdersByDay product2Day1Orders = repository.findByProductIdAndDay(2L, day).orElse(null);

        assertEquals(2, product1Day1Orders.getCount());
        assertEquals(1, product2Day1Orders.getCount());
    }

    @Test
    public void orderCountsUpdatedbyDay() {
        LocalDate day1 = LocalDate.ofYearDay(2021, 1);
        LocalDate day2 = LocalDate.ofYearDay(2021, 2);

        consumer.updateOrdersByDay(1L, day1);
        consumer.updateOrdersByDay(1L, day1);
        consumer.updateOrdersByDay(1L, day2);

        OrdersByDay product1Day1Orders = repository.findByProductIdAndDay(1L, day1).orElse(null);
        OrdersByDay product1Day2Orders = repository.findByProductIdAndDay(1L, day2).orElse(null);

        assertEquals(2, product1Day1Orders.getCount());
        assertEquals(1, product1Day2Orders.getCount());
    }

}
