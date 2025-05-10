package uk.ac.york.eng2.orders.events;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.apache.kafka.clients.admin.NewTopic;

@Factory
public class OrdersTopicFactory {

    public static final String TOPIC_ORDER_CREATED = "order-created";

    @Bean
    public NewTopic ordersTopic() {
        return new NewTopic(TOPIC_ORDER_CREATED, 3, (short) 1);
    }
}
