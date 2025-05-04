package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.ProductCreateDTO;

import java.util.List;

@Client("/products")
public interface ProductsClient {

    @Get
    List<Product> getProducts();

    @Get("/{id}")
    Product getProduct(@PathVariable Long id);

    @Get("/{id}/tags")
    HttpResponse<List<Tag>> getProductTags(@PathVariable Long id);

    @Post
    HttpResponse<Void> createProduct(@Body ProductCreateDTO dto);

    @Put("/{id}")
    HttpResponse<Void> updateProduct(@Body ProductCreateDTO dto, @PathVariable Long id);

    @Put("/{id}/tags/{tagId}")
    HttpResponse<Void> addProductTag(@PathVariable Long id, @PathVariable Long tagId);

    @Delete("/{id}")
    HttpResponse<Void> deleteProduct(@PathVariable Long id);

    @Delete("/{id}/tags/{tagId}")
    HttpResponse<Void> removeProductTag(@PathVariable Long id, @PathVariable Long tagId);

}
