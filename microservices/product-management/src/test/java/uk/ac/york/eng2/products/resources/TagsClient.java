package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.TagCreateDTO;

import java.util.List;

@Client("/tags")
public interface TagsClient {

    @Get
    List<Tag> getTags();

    @Get("/{id}")
    Tag getTag(@PathVariable Long id);

    @Get("/{id}/products")
    List<Product> getProducts(@PathVariable Long id);

    @Post
    HttpResponse<Void> createTag(@Body TagCreateDTO dto);

    @Put("/{id}")
    HttpResponse<Void> updateTag(@Body TagCreateDTO dto, @PathVariable Long id);

    @Delete("/{id}")
    HttpResponse<Void> deleteTag(@PathVariable Long id);

}