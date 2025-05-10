package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.ProductCreateDTO;
import uk.ac.york.eng2.products.events.OrdersByDayConsumer;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@MicronautTest(transactional = false)
public class ProductsControllerTest {

    @Inject
    private ProductsClient productsClient;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private TagRepository tagRepository;

    @Inject
    private OrdersByDayRepository ordersByDayRepository;

    @BeforeEach
    public void setup() {
        ordersByDayRepository.deleteAll();
        productRepository.deleteAll();
        tagRepository.deleteAll();

    }

    // Helper method - creates a product and returns generated product id
    private Long createProduct(ProductCreateDTO b) {
        HttpResponse<Void> createResponse = productsClient.createProduct(b);
        Long productId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
        return productId;
    }


    // Test that productRepository is empty
    @Test
    public void noProducts() {
        assertEquals(0, productsClient.getProducts().size());
    }

    // Test creating a product and list them to verify that productRepository has been updated accordingly
    @Test
    public void createAndListProducts() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);

        productsClient.createProduct(p);
        assertEquals(1, productsClient.getProducts().size());
    }

    // Test retrieving a product by ID
    @Test
    public void fetchProductById() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);

        Long productId = createProduct(p);
        Product fetchedProduct = productsClient.getProduct(productId);
        assertEquals(p.getName(), fetchedProduct.getName());
    }

    // Test retrieving a product with a missing ID
    @Test
    public void fetchMissingProduct() {
        assertNull(productsClient.getProduct(123L));
    }

    // Test updating a products details and verifying these values have changed accordingly
    @Test
    public void updateProductDetails() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);
        Long productId = createProduct(p);

        p.setName("test2");
        p.setUnitPrice(4.56F);
        productsClient.updateProduct(p, productId);

        Product updatedProduct = productsClient.getProduct(productId);
        assertEquals(p.getName(), updatedProduct.getName());
        assertEquals(p.getUnitPrice(), updatedProduct.getUnitPrice());
    }

    // Test updating a non-existent product returns 404 Not Found
    @Test
    public void updateMissingProduct() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);

        HttpResponse response = productsClient.updateProduct(p, 123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test deleting a product and verify that productRepository has been updated accordingly
    @Test
    public void deleteProductById() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);

        Long productId = createProduct(p);
        productsClient.deleteProduct(productId);
        assertEquals(0, productsClient.getProducts().size());
    }

    // Test deleting a non-existent product returns 404 Not Found
    @Test
    public void deleteMissingProduct() {
        HttpResponse response = productsClient.deleteProduct(123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test adding and removing tags to a product, retrieve added tags to verify product is updated accordingly
    @Test
    public void listProductTags() {
        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);
        Long productId = createProduct(p);

        assertEquals(0, productsClient.getProductTags(productId).body().size());

        Tag tag = new Tag();
        tag.setName("test tag");
        tag = tagRepository.save(tag);
        productsClient.addProductTag(productId, tag.getId());

        HttpResponse<List<Tag>> response = productsClient.getProductTags(productId);
        List<Tag> tags = response.body();
        assertEquals(1, tags.size());
        assertEquals(tag.getId(), tags.get(0).getId());

        productsClient.removeProductTag(productId, tag.getId());
        assertEquals(0, productsClient.getProductTags(productId).body().size());

    }

    // Test listing tags of non-existing product
    @Test
    public void listProductTagsMissingProduct() {
        HttpResponse<List<Tag>> tags = productsClient.getProductTags(123L);
        assertEquals(HttpStatus.NOT_FOUND, tags.getStatus());
    }

    // Test adding or removing a non-existent tag to a product returns 404 Not Found
    @Test
    public void AddRemoveProductTagForMissingTag() {

        ProductCreateDTO p = new ProductCreateDTO();
        p.setName("test");
        p.setUnitPrice(1.23F);
        Long productId = createProduct(p);

        HttpResponse addResponse = productsClient.addProductTag(productId, 1L);
        assertEquals(HttpStatus.NOT_FOUND, addResponse.getStatus());

        HttpResponse removeResponse = productsClient.removeProductTag(productId, 1L);
        assertEquals(HttpStatus.NOT_FOUND, removeResponse.getStatus());
    }

    // Test adding or removing a tag to a non-existent product returns 404 Not Found
    @Test
    public void AddRemoveProductTagForMissingProduct() {

        Tag tag = new Tag();
        tag.setName("test tag");
        tag = tagRepository.save(tag);

        HttpResponse addResponse = productsClient.addProductTag(1L, tag.getId());
        assertEquals(HttpStatus.NOT_FOUND, addResponse.getStatus());

        HttpResponse removeResponse = productsClient.removeProductTag(1L, tag.getId());
        assertEquals(HttpStatus.NOT_FOUND, removeResponse.getStatus());
    }

    // Test listing ordersByDay of a product, retrieve ordersByDay to verify product is updated accordingly
    @Test
    public void listOrdersByDay() {
        Product product = new Product();
        product.setName("Test");
        product.setUnitPrice(1.0F);
        product = productRepository.save(product);

        assertEquals(0, productsClient.getProductOrdersByDay(product.getId()).body().size());

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setDay(LocalDate.now());
        ordersByDay.setCount(1);
        ordersByDay.setProduct(product);
        ordersByDay = ordersByDayRepository.save(ordersByDay);

        HttpResponse<List<OrdersByDay>> response = productsClient.getProductOrdersByDay(product.getId());
        List<OrdersByDay> listOfOrdersByDays = response.body();
        assertEquals(1, listOfOrdersByDays.size());
        assertEquals(ordersByDay.getId(), listOfOrdersByDays.get(0).getId());

    }

    // Test listing ordersByDay of non-existing product
    @Test
    public void listOrdersByDayMissingProduct() {
        HttpResponse<List<OrdersByDay>> response = productsClient.getProductOrdersByDay(123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }


}
