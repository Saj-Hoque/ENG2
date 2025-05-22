package uk.ac.york.eng2.products.services.offers.src.conditions;

import io.micronaut.core.annotation.NonNull;

import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProductTagCondition implements Condition {


    private final ProductRepository productRepository;
    private final TagRepository tagRepository;

    private final List<String> tagNames;
    private final Integer minQuantity;

    public ProductTagCondition(List<String> tagNames, Integer minQuantity, ProductRepository productRepository, TagRepository tagRepository) {
        this.tagNames = tagNames;
        this.minQuantity = minQuantity;
        this.productRepository = productRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public boolean check(PricedOrderDTO order) {

        Set<Product> potentialMatchingProducts = new HashSet<>();

        for (String tagName : tagNames) {
            @NonNull Optional<Tag> oTag = tagRepository.findByName(tagName);
            if (oTag.isEmpty()) {
                return false;
            }
            Tag tag = oTag.get();
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
                return false;
            }

            Product product = oProduct.get();

            if (potentialMatchingProducts.contains(product)){
                if (productOrder.getQuantity() >= minQuantity) {
                    // e.g. if the product with the correct tags is more than the minimum required quantity, it is eligible for the offer
                    return true;
                }
            }
        }

        return false;
    }
}
