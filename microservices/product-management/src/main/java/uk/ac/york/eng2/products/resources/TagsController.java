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
import uk.ac.york.eng2.products.dto.TagCreateDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@io.swagger.v3.oas.annotations.tags.Tag(name="tags")
@Controller(TagsController.PREFIX)
public class TagsController {
    public static final String PREFIX = "/tags";

    @Inject
    private TagRepository tagRepository;

    @Inject
    private ProductRepository productRepository;

    // List all tags
    @Get
    public List<Tag> getTags() {
        return tagRepository.findAll();
    }

    // Retrieve tag by id
    @Get("/{id}")
    public Tag getTag(@PathVariable Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    // List all products of tag (by id)
    @Get("/{id}/products")
    public List<Product> getProducts(@PathVariable Long id) {
        return productRepository.findByTagsId(id);
    }


    // Create a new tag
    @Post
    public HttpResponse<Void> createTag(@Body TagCreateDTO dto) {
        Tag tag = new Tag();
        tag.setName(dto.getName());
        tag = tagRepository.save(tag);
        return HttpResponse.created(URI.create(PREFIX + "/" + tag.getId()));
    }


    // Update a tag (by id)
    @Transactional
    @Put("/{id}")
    public void updateTag(@Body TagCreateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Tag> oTag = tagRepository.findById(id);
        if (oTag.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Tag not found");
        }
        Tag tag = oTag.get();
        tag.setName(dto.getName());
        tagRepository.save(tag);
    }

    // Delete a tag (by id)
    @Delete("/{id}")
    public void deleteTag(@PathVariable Long id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Tag not found");
        }
    }


}
