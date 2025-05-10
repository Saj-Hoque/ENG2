package uk.ac.york.eng2.products.events;

import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.time.LocalDate;
import java.util.Optional;

@KafkaListener(
        groupId="order-created",
        threads = 3,
        offsetReset = OffsetReset.EARLIEST)
public class OrdersByDayConsumer {

    @Inject
    private OrdersByDayRepository ordersByDayRepository;

    @Inject
    private ProductRepository productRepository;

    @Transactional
    @Topic("order-created")
    public void updateOrdersByDay(@KafkaKey Long productId, String dayString) {
        LocalDate day = LocalDate.parse(dayString);
        // day isn't verified as this is generated

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            // Normally here, we would log that we received an order-created event with an unknown productId
            // This is likely not to trigger as productIds are verified previously through the use of the priceCalculator.
            return; // Hence for safety, we just skip processing
        }
        Product product = optionalProduct.get();

        OrdersByDay ordersByDay = ordersByDayRepository.findByProductIdAndDay(productId, day).orElse(new OrdersByDay(
                product, day, 0));
        ordersByDay.setCount(ordersByDay.getCount() + 1);
        ordersByDayRepository.save(ordersByDay);
    }
}