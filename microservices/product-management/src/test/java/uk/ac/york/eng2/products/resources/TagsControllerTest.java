package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.TagCreateDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@MicronautTest(transactional = false)
public class TagsControllerTest {

    @Inject
    private TagsClient tagsClient;

    @Inject
    private TagRepository tagRepository;

    @Inject
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        tagRepository.deleteAll();
        productRepository.deleteAll();
    }

    // Helper method - creates a tag and returns generated tag id
    private Long createTag(TagCreateDTO b) {
        HttpResponse<Void> createResponse = tagsClient.createTag(b);
        Long tagId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
        return tagId;
    }

    // Test that tagRepository is empty
    @Test
    public void noTags() {
        assertEquals(0, tagsClient.getTags().size());
    }

    // Test creating a tag and list them to verify that tagRepository has been updated accordingly
    @Test
    public void createAndListTags() {
        TagCreateDTO t = new TagCreateDTO();
        t.setName("test");

        tagsClient.createTag(t);
        assertEquals(1, tagsClient.getTags().size());
    }

    // Test retrieving a tag by ID
    @Test
    public void fetchTagById() {
        TagCreateDTO t = new TagCreateDTO();
        t.setName("test");

        Long tagId = createTag(t);
        Tag fetchedTag = tagsClient.getTag(tagId);
        assertEquals(t.getName(), fetchedTag.getName());
    }

    // Test retrieving a tag with a missing ID
    @Test
    public void fetchMissingTag() {
        assertNull(tagsClient.getTag(123L));
    }

    // Test updating a tags details and verifying these values have changed accordingly
    @Test
    public void updateTagDetails() {
        TagCreateDTO t = new TagCreateDTO();
        t.setName("test");
        Long tagId = createTag(t);

        t.setName("test2");
        tagsClient.updateTag(t, tagId);

        Tag updatedTag = tagsClient.getTag(tagId);
        assertEquals(t.getName(), updatedTag.getName());
    }

    // Test updating a non-existent tag returns 404 Not Found
    @Test
    public void updateMissingTag() {
        TagCreateDTO t = new TagCreateDTO();
        t.setName("test");

        HttpResponse response = tagsClient.updateTag(t, 123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test deleting a tag and verify that tagRepository has been updated accordingly
    @Test
    public void deleteTagById() {
        TagCreateDTO t = new TagCreateDTO();
        t.setName("test");

        Long tagId = createTag(t);
        tagsClient.deleteTag(tagId);
        assertEquals(0, tagsClient.getTags().size());
    }

    // Test deleting a non-existent tag returns 404 Not Found
    @Test
    public void deleteMissingTag() {
        HttpResponse response = tagsClient.deleteTag(123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test retrieving and listing products of a tag
    @Test
    public void listTagProducts() {
        Tag tag = new Tag();
        tag.setName("test");
        tag = tagRepository.save(tag);

        assertEquals(0, tagsClient.getTagProducts(tag.getId()).body().size());

        Product product = new Product();
        product.setName("test");
        product.setUnitPrice(1.23F);
        product.getTags().add(tag);
        product = productRepository.save(product);

        HttpResponse<List<Product>> response = tagsClient.getTagProducts(tag.getId());
        List<Product> products = response.body();
        assertEquals(1, products.size());
        assertEquals(product.getName(), products.get(0).getName());
    }

    // Test listing products of non-existing tag
    @Test
    public void listTagProductsMissingTag() {
        HttpResponse<List<Product>> products = tagsClient.getTagProducts(123L);
        assertEquals(HttpStatus.NOT_FOUND, products.getStatus());
    }

}
