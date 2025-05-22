package uk.ac.york.eng2.products.services.offers.src.conditions;


import io.micronaut.core.annotation.NonNull;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.time.LocalDate;
import java.util.Optional;

public class ProductOrderQuantityCondition implements Condition {


    private final ProductRepository productRepository;
    private final OrdersByDayRepository ordersByDayRepository;

    private final String productName;
    private final Integer dailyOrders;

    public ProductOrderQuantityCondition(String productName, Integer dailyOrders, ProductRepository productRepository, OrdersByDayRepository ordersByDayRepository) {
        this.productName = productName;
        this.dailyOrders = dailyOrders;
        this.productRepository = productRepository;
        this.ordersByDayRepository = ordersByDayRepository;
    }

    @Override
    public boolean check(PricedOrderDTO order) {

        LocalDate date = LocalDate.now();

        for (OrderRequestDTO.ProductOrder productOrder : order.getOrder().getOrder()) {

            @NonNull Optional<Product> oProduct = productRepository.findById(productOrder.getProductId());
            if (oProduct.isEmpty()) {
                return false;
            }
            Product product = oProduct.get();

            if (product.getName().equals(productName)) {

                @NonNull Optional<OrdersByDay> oOrdersByDay = ordersByDayRepository.findByProductIdAndDay(productOrder.getProductId(), date);
                if (oOrdersByDay.isEmpty()) {
                    return false; // No order data for this product today, not eligible
                }
                Integer ordersCountToday = oOrdersByDay.get().getCount();

                return ordersCountToday <= dailyOrders;
            }
        }

        return false;

    }


}
