package uk.ac.york.eng2.orders.events;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;
import uk.ac.york.cs.eng2.products.model.OrderRequestDTO;

@KafkaClient
public interface OrdersProducer {

    @Topic(OrdersTopicFactory.TOPIC_ORDER_CREATED)
    void orderCreated(@KafkaKey String orderId, OrderRequestDTO orderRequest);
}
