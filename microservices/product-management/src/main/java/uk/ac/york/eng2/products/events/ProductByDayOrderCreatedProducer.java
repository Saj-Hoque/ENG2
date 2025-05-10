package uk.ac.york.eng2.products.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.time.LocalDate;

@KafkaClient
public interface ProductByDayOrderCreatedProducer {

    @Topic(ProductByDayOrderCreatedFactory.TOPIC_PRODUCT_BY_DAY_ORDER_CREATED)
    void orderCreated(@KafkaKey Long productId, LocalDate day);
}
