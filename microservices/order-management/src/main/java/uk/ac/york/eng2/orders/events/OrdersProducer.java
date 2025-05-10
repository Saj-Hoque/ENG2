package uk.ac.york.eng2.orders.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.time.LocalDate;

@KafkaClient
public interface OrdersProducer {

    @Topic(OrdersTopicFactory.TOPIC_ORDER_CREATED)
    void orderCreated(@KafkaKey Long productId,  String day);
}
