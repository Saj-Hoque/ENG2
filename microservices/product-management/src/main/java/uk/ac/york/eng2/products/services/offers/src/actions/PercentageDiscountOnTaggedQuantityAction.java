package uk.ac.york.eng2.products.services.offers.src.actions;

import io.micronaut.core.annotation.NonNull;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PercentageDiscountOnTaggedQuantityAction implements Action {


    private final ProductRepository productRepository;

    private final TagQuantity item;
    private final Float percentage;

    public PercentageDiscountOnTaggedQuantityAction(TagQuantity item , Float percentage, ProductRepository productRepository) {
        this.item = item;
        this.percentage = percentage;
        this.productRepository = productRepository;
    }

    @Override
    public PricedOrderDTO apply(PricedOrderDTO order) {

        if (item.process() == false){
            return order; // Can't find associated tags
        }

        Set<Product> potentialMatchingProducts = new HashSet<>();

        for (Tag tag : item.getTags()){

            List<Product> productsWithTag = productRepository.findByTagsId(tag.getId());
            Set<Product> productsWithTagSet = new HashSet<>(productsWithTag);
            if (potentialMatchingProducts.isEmpty()){
                potentialMatchingProducts = new HashSet<>(productsWithTagSet);
            } else {
                potentialMatchingProducts.retainAll(productsWithTagSet);
            }
        }

        for (OrderRequestDTO.ProductOrder productOrder : order.getOrder().getOrder()) {

            @NonNull Optional<Product> oProduct = productRepository.findById(productOrder.getProductId());
            if (oProduct.isEmpty()) {
                return order;
            }

            Product product = oProduct.get();

            if (potentialMatchingProducts.contains(product)){
                if (productOrder.getQuantity() >= item.getQuantity()){
                    // This action is applied as many times as mathematically possible per product
                    // e.g. "pizza" tag is present in pizza and large pizza, discount is applied once for pizza and once for large pizza
                    Float originalPrice = product.getUnitPrice() * item.getQuantity() ;
                    Float discount = originalPrice * (percentage / 100F);
                    order.setOrderTotal(order.getOrderTotal() - discount);
                    // repeat on all other eligible products

                    // If this was to be applied as many times as possible per product, we could do the following:
                    // Integer applicableTimes = productOrder.getQuantity() / item.getQuantity();
                    // Float originalPrice = product.getUnitPrice() * item.getQuantity() * applicableTimes;
                }
            }
        }
        return order;
    }
}
