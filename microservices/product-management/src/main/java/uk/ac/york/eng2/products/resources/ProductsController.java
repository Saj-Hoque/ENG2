package uk.ac.york.eng2.products.resources;


import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.ProductCreateDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.net.URI;
import java.util.*;

@io.swagger.v3.oas.annotations.tags.Tag(name="products")
@Controller(ProductsController.PREFIX)
public class ProductsController {
    public static final String PREFIX = "/products";

    @Inject
    private ProductRepository productRepository;

    @Inject
    private TagRepository tagRepository;

    private record ProductTag (Tag tag, Product product) {}

    private ProductTag getProductTag(Long productId, Long tagId) {
        @NonNull Optional<Tag> oTag = tagRepository.findById(tagId);
        if (oTag.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Tag not found");
        }
        Tag tag = oTag.get();

        @NonNull Optional<Product> oProduct = productRepository.findById(productId);
        if (oProduct.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = oProduct.get();

        return new ProductTag(tag, product);
    }






    // List all products
    @Get
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Retrieve product by id
    @Get("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // List all tags of product (by id)
    @Get("/{id}/tags")
    public List<Tag> getProductTags(@PathVariable Long id) {
        return tagRepository.findByProductsId(id);
    }

    // Create a new product
    @Post
    public HttpResponse<Void> createProduct(@Body ProductCreateDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        product = productRepository.save(product);
        return HttpResponse.created(URI.create(PREFIX + "/" + product.getId()));
    }

    // Update a product (by id)
    @Transactional
    @Put("/{id}")
    public void updateProduct(@Body ProductCreateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Product> oProduct = productRepository.findById(id);
        if (oProduct.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = oProduct.get();
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        productRepository.save(product);
    }

    // Add tag to product (by id)
    @Transactional
    @Put ("/{id}/tags/{tagId}")
    public void addProductTag(@PathVariable Long id, @PathVariable Long tagId) {
        ProductTag result = getProductTag(id, tagId);
        result.product.getTags().add(result.tag);
        productRepository.save(result.product);
    }


    // Delete a product (by id)
    @Delete("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }

    // Delete tag from product (by id)
    @Delete("/{id}/tags/{tagId}")
    public void removeProductTag(@PathVariable Long id, @PathVariable Long tagId) {
        ProductTag result = getProductTag(id, tagId);
        result.product.getTags().remove(result.tag);
        productRepository.save(result.product);
    }


}
