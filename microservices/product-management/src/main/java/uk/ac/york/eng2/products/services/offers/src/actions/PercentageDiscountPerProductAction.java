package uk.ac.york.eng2.products.services.offers.src.actions;

import io.micronaut.core.annotation.NonNull;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.util.Optional;

public class PercentageDiscountPerProductAction implements Action{

    private final ProductRepository productRepository;

    private final String productName;
    private final Float percentage;

    public PercentageDiscountPerProductAction(String productName, Float percentage, ProductRepository productRepository){
        this.productName = productName;
        this.percentage = percentage;
        this.productRepository = productRepository;
    }

    @Override
    public PricedOrderDTO apply(PricedOrderDTO order){

        for (OrderRequestDTO.ProductOrder productOrder : order.getOrder().getOrder()) {

            @NonNull Optional<Product> oProduct = productRepository.findById(productOrder.getProductId());
            if (oProduct.isEmpty()) {
                return order;
            }
            Product product = oProduct.get();

            if (product.getName().equals(productName)) {
                Float originalPrice = product.getUnitPrice() * productOrder.getQuantity();
                Float discount = originalPrice * (percentage / 100F);
                order.setOrderTotal(order.getOrderTotal() - discount);
                break; // This Action is for only one product
            }
        }

        return order;
    }



}
