package uk.ac.york.eng2.products.services.offers.src.actions;

import io.micronaut.core.annotation.NonNull;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.util.*;

public class BundlePriceAction implements Action {


    private final ProductRepository productRepository;

    private final List<TagQuantity> items;
    private final Float bundlePrice;

    public BundlePriceAction(List<TagQuantity> items, Float bundlePrice, ProductRepository productRepository) {
        this.items = items;
        this.bundlePrice = bundlePrice;
        this.productRepository = productRepository;
    }

    @Override
    public PricedOrderDTO apply(PricedOrderDTO order) {

        for (TagQuantity item : items) {
            if (item.process() == false) {
                return order; // Can't find associated tags
            }
        }

        // Find eligible products

        Map<Set<Product>, Integer> productIteration = new HashMap<>();
        Set<Product> potentialMatchingProducts = new HashSet<>();

        for (TagQuantity item : items) {
            for (Tag tag : item.getTags()) {
                List<Product> productsWithTag = productRepository.findByTagsId(tag.getId());
                Set<Product> productsWithTagSet = new HashSet<>(productsWithTag);
                if (potentialMatchingProducts.isEmpty()) {
                    potentialMatchingProducts = new HashSet<>(productsWithTagSet);
                } else {
                    potentialMatchingProducts.retainAll(productsWithTagSet);
                }
            }
            productIteration.put(new HashSet<>(potentialMatchingProducts), item.getQuantity());
            potentialMatchingProducts.clear();
        }

        Map<Product, Integer> bundleProducts = new HashMap<>();

        for (Map.Entry<Set<Product>, Integer> entry : productIteration.entrySet()) {
            Set<Product> matchingProducts = entry.getKey();
            Integer quantity = entry.getValue();

            for (OrderRequestDTO.ProductOrder productOrder : order.getOrder().getOrder()) {

                @NonNull Optional<Product> oProduct = productRepository.findById(productOrder.getProductId());
                if (oProduct.isEmpty()) {
                    return order;
                }

                Product product = oProduct.get();

                // Check for one occasion of bundle match
                if (matchingProducts.contains(product) && productOrder.getQuantity() >= quantity){
                    bundleProducts.put(product, quantity);
                    break;
                }
            }
        }


        if (!bundleProducts.isEmpty()) {
            Float originalPrice = 0F;
            // We can assume the correct number of products are in the order based on the conditions passing
            for (Map.Entry<Product, Integer> entry : bundleProducts.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                originalPrice += product.getUnitPrice() * quantity;
            }

            // To make this more user-friendly, we could sort the products by most expensive.
            order.setOrderTotal(order.getOrderTotal() - originalPrice + bundlePrice);
        }

        return order;
    }

}
