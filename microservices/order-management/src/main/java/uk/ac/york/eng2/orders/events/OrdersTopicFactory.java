package uk.ac.york.eng2.orders.events;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

@Requires(bean= AdminClient.class)
@Factory
public class OrdersTopicFactory {

    public static final String TOPIC_ORDER_CREATED = "order-created";

    @Bean
    public NewTopic ordersTopic() {
        return new NewTopic(TOPIC_ORDER_CREATED, 3, (short) 1);
    }
}
