package uk.ac.york.eng2.products.services.offers.actions;

import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;
import uk.ac.york.eng2.products.services.offers.src.actions.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ActionTest {

    // FixedDiscountAction
    @Test
    public void testFixedDiscountActionValid(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        FixedDiscountAction action = new FixedDiscountAction(10.0F);
        PricedOrderDTO result = action.apply(order);

        assertEquals(90.0F, result.getOrderTotal());
    }

    @Test
    public void testFixedDiscountActionNegative(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        FixedDiscountAction action = new FixedDiscountAction(110.0F);
        PricedOrderDTO result = action.apply(order);

        assertEquals(0F, result.getOrderTotal());
    }

    // PercentageDiscountTotalAction
    @Test
    public void testPercentageDiscountTotalActionValid(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(200.0F);

        PercentageDiscountTotalAction action = new PercentageDiscountTotalAction(25.0F); // 25% of 200 = 50
        PricedOrderDTO result = action.apply(order);

        assertEquals(150.0F, result.getOrderTotal());
    }

    @Test
    public void testPercentageDiscountTotalActionZero(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(0.0F);

        PercentageDiscountTotalAction action = new PercentageDiscountTotalAction(50.0F); // 50% of 0 = 0
        PricedOrderDTO result = action.apply(order);

        assertEquals(0.0F, result.getOrderTotal());
    }

    // PercentageDiscountPerProductAction
    @Test
    public void testPercentageDiscountPerProductActionValid() {
        ProductRepository productRepository = mock(ProductRepository.class);

        Long productId = 1L;
        String productName = "Test";

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setUnitPrice(100.0F);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(2);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(200.0F);
        order.setOrder(orderRequest);

        PercentageDiscountPerProductAction action = new PercentageDiscountPerProductAction(productName, 25.0F, productRepository); // 25% off 200 = 50

        PricedOrderDTO result = action.apply(order);

        assertEquals(150.0F, result.getOrderTotal());
    }

    @Test
    public void testPercentageDiscountPerProductActionProductMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);

        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);
        order.setOrder(orderRequest);

        PercentageDiscountPerProductAction action = new PercentageDiscountPerProductAction("Test", 50.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(100.0F, result.getOrderTotal()); // no discount applied
    }

    @Test
    public void testPercentageDiscountPerProductActionProductNameInvalid() {
        ProductRepository productRepository = mock(ProductRepository.class);

        Long productId = 1L;

        Product product = new Product();
        product.setId(productId);
        product.setName("Tag Incorrect"); // Doesn't match "Tag Correct"
        product.setUnitPrice(100.0F);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);
        order.setOrder(orderRequest);

        PercentageDiscountPerProductAction action = new PercentageDiscountPerProductAction("Tag Correct", 50.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(100.0F, result.getOrderTotal()); // no discount applied
    }

    // PercentageDiscountOnTaggedQuantityAction
    @Test
    public void testPercentageDiscountOnTaggedQuantityActionValid(){
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Test2");

        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag1, tag2));
        when(tagQuantity.getQuantity()).thenReturn(2);

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");
        product.setUnitPrice(10.0F);

        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of(product));
        when(productRepository.findByTagsId(tag2.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(2);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(30.0F);

        // 25% off 2 × 10 = 20 → discount = 5
        PercentageDiscountOnTaggedQuantityAction action = new PercentageDiscountOnTaggedQuantityAction(tagQuantity, 25.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(25.0F, result.getOrderTotal());
    }

    @Test
    public void testPercentageDiscountOnTaggedQuantityActionInvalidNoMatchingTags(){
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag1));

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");
        product.setUnitPrice(10.0F);

        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of()); // product does not have tag
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(30.0F);

        PercentageDiscountOnTaggedQuantityAction action = new PercentageDiscountOnTaggedQuantityAction(tagQuantity, 25.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(30.0F, result.getOrderTotal());
    }

    @Test
    public void testPercentageDiscountOnTaggedQuantityActionInvalidNNotEnoughProductQuantity() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag1));
        when(tagQuantity.getQuantity()).thenReturn(2);

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");
        product.setUnitPrice(10.0F);

        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1); // Not enough product quantity to meet requirement

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(30.0F);

        PercentageDiscountOnTaggedQuantityAction action = new PercentageDiscountOnTaggedQuantityAction(tagQuantity, 25.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(30.0F, result.getOrderTotal());
    }

    @Test
    public void testPercentageDiscountOnTaggedQuantityActionInvalidTagQuantity(){
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        when(tagQuantity.process()).thenReturn(false); // tags invalid

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(50.0F);

        PercentageDiscountOnTaggedQuantityAction action = new PercentageDiscountOnTaggedQuantityAction(tagQuantity, 25.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(50.0F, result.getOrderTotal()); // no discount
    }

    @Test
    public void testPercentageDiscountOnTaggedQuantityActionInvalidProductMissing(){
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag = new Tag();
        tag.setId(1L);

        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag));
        when(tagQuantity.getQuantity()).thenReturn(2);

        when(productRepository.findByTagsId(tag.getId())).thenReturn(List.of());
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(123L);
        productOrder.setQuantity(2);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(100.0F);

        PercentageDiscountOnTaggedQuantityAction action = new PercentageDiscountOnTaggedQuantityAction(tagQuantity, 50.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(100.0F, result.getOrderTotal()); // no discount
    }

    // BundlePriceAction
    @Test
    public void testBundlePriceActionValid() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity1 = mock(TagQuantity.class);
        TagQuantity tagQuantity2 = mock(TagQuantity.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Test2");

        Tag tag3 = new Tag();
        tag3.setId(3L);
        tag3.setName("Test3");

        when(tagQuantity1.process()).thenReturn(true);
        when(tagQuantity1.getTags()).thenReturn(List.of(tag1, tag3));
        when(tagQuantity1.getQuantity()).thenReturn(2);

        when(tagQuantity2.process()).thenReturn(true);
        when(tagQuantity2.getTags()).thenReturn(List.of(tag2));
        when(tagQuantity2.getQuantity()).thenReturn(1);

        Long productId1 = 1L;
        Product product1 = new Product();
        product1.setId(productId1);
        product1.setName("Test");
        product1.setUnitPrice(10.0F); // 2 × 10 = 20

        Long productId2 = 2L;
        Product product2 = new Product();
        product2.setId(productId2);
        product2.setName("Test 2");
        product2.setUnitPrice(5.0F); // 1 × 5 = 5

        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of(product1));
        when(productRepository.findByTagsId(tag3.getId())).thenReturn(List.of(product1));
        when(productRepository.findByTagsId(tag2.getId())).thenReturn(List.of(product2));
        when(productRepository.findById(productId1)).thenReturn(Optional.of(product1));
        when(productRepository.findById(productId2)).thenReturn(Optional.of(product2));

        // Order contains enough quantity
        OrderRequestDTO.ProductOrder productOrder1 = new OrderRequestDTO.ProductOrder();
        productOrder1.setProductId(productId1);
        productOrder1.setQuantity(2);

        OrderRequestDTO.ProductOrder productOrder2 = new OrderRequestDTO.ProductOrder();
        productOrder2.setProductId(productId2);
        productOrder2.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder1, productOrder2));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(30.0F);

        // Bundle price is 15, expected: 30 - 20 - 5 + 15 = 20
        BundlePriceAction action = new BundlePriceAction(List.of(tagQuantity1, tagQuantity2), 15.0F, productRepository);
        PricedOrderDTO result = action.apply(order);

        assertEquals(20.0F, result.getOrderTotal());
    }

    @Test
    public void testBundlePriceActionInvalidNoMatchingTags() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity1 = mock(TagQuantity.class);
        TagQuantity tagQuantity2 = mock(TagQuantity.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        when(tagQuantity1.process()).thenReturn(true);
        when(tagQuantity1.getTags()).thenReturn(List.of(tag1));
        when(tagQuantity1.getQuantity()).thenReturn(2);

        Long productId1 = 1L;
        Product product1 = new Product();
        product1.setId(productId1);
        product1.setName("Test");
        product1.setUnitPrice(10.0F);

        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of()); // no matching tags for product
        when(productRepository.findById(productId1)).thenReturn(Optional.of(product1));

        OrderRequestDTO.ProductOrder productOrder1 = new OrderRequestDTO.ProductOrder();
        productOrder1.setProductId(productId1);
        productOrder1.setQuantity(2);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder1));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(30.0F);

        BundlePriceAction action = new BundlePriceAction(List.of(tagQuantity1, tagQuantity2), 15.0F, productRepository);
        PricedOrderDTO result = action.apply(order);

        assertEquals(30.0F, result.getOrderTotal()); // No discount
    }

    @Test
    public void testBundlePriceProcessInvalidTagQuantity() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        when(tagQuantity.process()).thenReturn(false); // invalid tagQuantity

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        BundlePriceAction action = new BundlePriceAction(List.of(tagQuantity), 10.0F, productRepository);
        PricedOrderDTO result = action.apply(order);

        assertEquals(100.0F, result.getOrderTotal()); // No Discount
    }

    @Test
    public void testBundlePriceProcessInvalidProductMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag = new Tag();
        tag.setId(1L);

        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag));
        when(tagQuantity.getQuantity()).thenReturn(2);

        when(productRepository.findByTagsId(tag.getId())).thenReturn(List.of());
        when(productRepository.findById(any())).thenReturn(Optional.empty());   // Invalid product

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(123L);
        productOrder.setQuantity(2);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(100.0F);

        BundlePriceAction action = new BundlePriceAction(List.of(tagQuantity), 10.0F, productRepository);

        PricedOrderDTO result = action.apply(order);

        assertEquals(100.0F, result.getOrderTotal()); // no discount
    }

    @Test
    public void testBundlePriceNotAppliedIfQuantityTooLow() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagQuantity tagQuantity = mock(TagQuantity.class);

        Tag tag = new Tag(); tag.setId(1L);
        when(tagQuantity.process()).thenReturn(true);
        when(tagQuantity.getTags()).thenReturn(List.of(tag));
        when(tagQuantity.getQuantity()).thenReturn(3); // wants 3

        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test");
        product.setUnitPrice(10.0F);

        when(productRepository.findByTagsId(tag.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(2); // not enough

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(50.0F);

        BundlePriceAction action = new BundlePriceAction(List.of(tagQuantity), 10.0F, productRepository);
        PricedOrderDTO result = action.apply(order);

        assertEquals(50.0F, result.getOrderTotal()); // no discount
    }

    // TagQuantity
    @Test
    public void testProcessReturnsTrueWhenAllTagsExist() {
        TagRepository tagRepository = mock(TagRepository.class);

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName("Test");

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName("Test2");

        when(tagRepository.findByName("Test")).thenReturn(Optional.of(tag1));
        when(tagRepository.findByName("Test2")).thenReturn(Optional.of(tag2));

        TagQuantity tagQuantity = new TagQuantity(List.of("Test", "Test2"), 2, tagRepository);
        boolean result = tagQuantity.process();

        assertTrue(result);
        assertEquals(2, tagQuantity.getTags().size());
        assertEquals("Test", tagQuantity.getTags().get(0).getName());
        assertEquals("Test2", tagQuantity.getTags().get(1).getName());
        assertEquals(2, tagQuantity.getQuantity());
        assertEquals(List.of("Test", "Test2"), tagQuantity.getTagNames());
    }

    @Test
    public void testProcessReturnsFalseWhenTagMissing() {
        TagRepository tagRepository = mock(TagRepository.class);

        when(tagRepository.findByName("Test")).thenReturn(Optional.of(new Tag()));
        when(tagRepository.findByName("Test2")).thenReturn(Optional.empty()); // Missing tag

        TagQuantity tagQuantity = new TagQuantity(List.of("Test", "Test2"), 1, tagRepository);
        boolean result = tagQuantity.process();

        assertFalse(result);
    }



}
