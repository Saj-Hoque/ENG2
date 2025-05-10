package uk.ac.york.eng2.products.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.time.LocalDate;

@KafkaListener(
        groupId="order-created",
        threads = 3,
        offsetReset = OffsetReset.EARLIEST)
public class ProductByDayOrderCreatedConsumer {

    @Inject
    private OrdersByDayRepository ordersByDayRepository;

    @Inject
    private ProductByDayOrderCreatedProducer producer;
    @Inject
    private ProductRepository productRepository;

    @Topic("order-created")
    public void orderCreatedEvent(@KafkaKey Long orderId, OrderRequestDTO orderRequest, LocalDate day) {
        // we funnel all order-created events into a product-day topic
        for (OrderRequestDTO.ProductOrder productOrder : orderRequest.getOrder()) {
            producer.orderCreated(productOrder.getProductId(), day);
        }
    }

    @Transactional
    @Topic(ProductByDayOrderCreatedFactory.TOPIC_PRODUCT_BY_DAY_ORDER_CREATED)
    public void updateOrdersByDay(@KafkaKey Long productId, LocalDate day) {
        Product product = productRepository.findById(productId).orElse(null);
        OrdersByDay ordersByDay = ordersByDayRepository.findByProductIdAndDay(productId, day).orElse(new OrdersByDay(
                product, day, 0));
        ordersByDay.setCount(ordersByDay.getCount() + 1);
        ordersByDayRepository.save(ordersByDay);
    }

}