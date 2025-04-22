package uk.ac.york.eng2.products.resources;


import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.ProductCreateDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.net.URI;
import java.util.*;

@Controller("/products")
public class ProductsController {

    @Inject
    private ProductRepository repository;


    // List all products
    @Get
    public List<Product> getProducts() {
        return repository.findAll();
    }

    // Retrieve product by id
    @Get("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }


    // Create a new product

    @Post
    public HttpResponse<Void> createProduct(@Body ProductCreateDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        product = repository.save(product);
        return HttpResponse.created(URI.create("/products/" + product.getId()));
    }


    // Update a product (by id)

    @Transactional
    @Put("/{id}")
    public void updateProduct(@Body ProductCreateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Product> oProduct = repository.findById(id);
        if (oProduct.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        Product product = oProduct.get();
        product.setName(dto.getName());
        product.setUnitPrice(dto.getUnitPrice());
        repository.save(product);
    }

    // Delete a product (by id)

    @Delete("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }


}
