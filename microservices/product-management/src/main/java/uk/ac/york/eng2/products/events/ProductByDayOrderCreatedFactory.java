package uk.ac.york.eng2.products.events;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;

@Requires(bean= AdminClient.class)
@Factory
public class ProductByDayOrderCreatedFactory {

    public static final String TOPIC_PRODUCT_BY_DAY_ORDER_CREATED = "product-by-day-order-created";

    @Bean
    public NewTopic productByDayOrderCreatedTopic() {
        return new NewTopic(TOPIC_PRODUCT_BY_DAY_ORDER_CREATED, 3, (short) 1);
    }

}
