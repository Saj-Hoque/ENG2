package uk.ac.york.eng2.products.services.offers.conditions;


import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;
import uk.ac.york.eng2.products.services.offers.src.conditions.DateCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.MinimumOrderAmountCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductOrderQuantityCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductTagCondition;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConditionTest {


    // DateCondition
    @Test
    public void testDateConditionValid(){
        LocalDate date = LocalDate.now();
        DateCondition condition = new DateCondition(date.getMonthValue(), date.getDayOfMonth());

        PricedOrderDTO order = new PricedOrderDTO();
        assertTrue(condition.check(order));
    }

    @Test
    public void testDateConditionInvalid(){
        LocalDate date = LocalDate.now();
        PricedOrderDTO order = new PricedOrderDTO();

        DateCondition condition = new DateCondition(date.getMonthValue(), date.getDayOfMonth() + 1);
        assertFalse(condition.check(order));

        DateCondition condition2 = new DateCondition(date.getMonthValue() + 1, date.getDayOfMonth());
        assertFalse(condition2.check(order));
    }

    // MinimumOrderAmountCondition
    @Test
    public void testMinimumOrderAmountConditionValid(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);

        MinimumOrderAmountCondition condition = new MinimumOrderAmountCondition(50.0F);

        assertTrue(condition.check(order));
    }

    @Test
    public void testMinimumOrderAmountConditionInvalid(){
        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(49.0F);

        MinimumOrderAmountCondition condition = new MinimumOrderAmountCondition(50.0F);

        assertFalse(condition.check(order));
    }

    // ProductOrderQuantityCondition
    @Test
    public void testProductOrderQuantityConditionValid(){

        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        Long productId = 1L;
        String productName = "Test";
        Integer maxOrders = 5;


        Product product = new Product();
        product.setId(productId);
        product.setName(productName);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setCount(3);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(ordersByDayRepository.findByProductIdAndDay(productId, LocalDate.now())).thenReturn(Optional.of(ordersByDay));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductOrderQuantityCondition condition = new ProductOrderQuantityCondition(productName, maxOrders, productRepository, ordersByDayRepository);

        assertTrue(condition.check(order));
    }

    @Test
    public void testProductOrderQuantityConditionInvalid() {

        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        Long productId = 1L;
        String productName = "Test";
        Integer maxOrders = 5;

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);

        OrdersByDay ordersByDay = new OrdersByDay();
        ordersByDay.setCount(10); // over the limit

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(ordersByDayRepository.findByProductIdAndDay(productId, LocalDate.now())).thenReturn(Optional.of(ordersByDay));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductOrderQuantityCondition condition = new ProductOrderQuantityCondition(productName, maxOrders, productRepository, ordersByDayRepository);

        assertFalse(condition.check(order));
    }

    @Test
    public void testProductOrderQuantityConditionInvalidProductMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        Long productId = 1L;
        String productName = "Test";

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductOrderQuantityCondition condition = new ProductOrderQuantityCondition(productName, 5, productRepository, ordersByDayRepository);

        assertFalse(condition.check(order));
    }

    @Test
    public void testProductOrderQuantityConditionInvalidOrdersByDayMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        Long productId = 1L;
        String productName = "Test";

        Product product = new Product();
        product.setId(productId);
        product.setName(productName);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(ordersByDayRepository.findByProductIdAndDay(productId, LocalDate.now())).thenReturn(Optional.empty());

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductOrderQuantityCondition condition = new ProductOrderQuantityCondition(productName, 5, productRepository, ordersByDayRepository);

        assertFalse(condition.check(order));
    }

    @Test
    public void testProductOrderQuantityConditionInvalidProductName() {
        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository OrdersByDayRepository = mock(OrdersByDayRepository.class);

        Long productId = 1L;
        String correctProductName = "Test";
        String incorrectProductName = "Test Invalid";

        Product product = new Product();
        product.setId(productId);
        product.setName(incorrectProductName);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductOrderQuantityCondition condition = new ProductOrderQuantityCondition(correctProductName, 5, productRepository, OrdersByDayRepository);

        assertFalse(condition.check(order));
    }

    @Test
    public void testProductTagConditionValid() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Long productId = 1L;
        String tagName1 = "Tag 1";
        String tagName2 = "Tag 2";
        List<String> tagNames = List.of(tagName1, tagName2);
        Integer minQuantity = 2;

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName(tagName1);

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName(tagName2);

        Product product = new Product(); product.setId(productId); product.setName("Test");

        when(tagRepository.findByName(tagName1)).thenReturn(Optional.of(tag1));
        when(tagRepository.findByName(tagName2)).thenReturn(Optional.of(tag2));
        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of(product));
        when(productRepository.findByTagsId(tag2.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(minQuantity);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductTagCondition condition = new ProductTagCondition(tagNames, minQuantity, productRepository, tagRepository);
        assertTrue(condition.check(order));
    }

    @Test
    public void testProductTagConditionInvalidNoProductForTag() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Long productId = 1L;
        String tagName1 = "Tag 1";
        String tagName2 = "Tag 2";
        List<String> tagNames = List.of(tagName1, tagName2);
        int minQuantity = 1;

        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setName(tagName1);

        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setName(tagName2);

        Product product = new Product(); product.setId(productId);

        when(tagRepository.findByName(tagName1)).thenReturn(Optional.of(tag1));
        when(tagRepository.findByName(tagName2)).thenReturn(Optional.of(tag2));
        when(productRepository.findByTagsId(tag1.getId())).thenReturn(List.of(product));
        when(productRepository.findByTagsId(tag2.getId())).thenReturn(List.of()); // Missing this tag
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductTagCondition condition = new ProductTagCondition(tagNames, minQuantity, productRepository, tagRepository);
        assertFalse(condition.check(order));
    }

    @Test
    public void testProductTagConditionInvalidQuantity() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Long productId = 1L;
        String tagName = "Tag";
        int minQuantity = 5;

        Tag tag = new Tag(); tag.setId(1L); tag.setName(tagName);
        Product product = new Product(); product.setId(productId);

        when(tagRepository.findByName(tagName)).thenReturn(Optional.of(tag));
        when(productRepository.findByTagsId(tag.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1); // Not enough

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);

        ProductTagCondition condition = new ProductTagCondition(List.of(tagName), minQuantity, productRepository, tagRepository);
        assertFalse(condition.check(order));
    }

    @Test
    public void testProductTagConditionInvalidTagMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        String tagName = "Tag";
        when(tagRepository.findByName(tagName)).thenReturn(Optional.empty());

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(new OrderRequestDTO());

        ProductTagCondition condition = new ProductTagCondition(List.of(tagName), 1, productRepository, tagRepository);
        assertFalse(condition.check(order));
    }

    @Test
    public void testProductTagConditionInvalidProductMissing() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Long productId = 1L;
        String tagName = "Tag";
        Integer minQuantity = 1;

        Tag tag = new Tag();
        tag.setId(1L);
        tag.setName(tagName);

        Product product = new Product();
        product.setId(productId);

        when(tagRepository.findByName(tagName)).thenReturn(Optional.of(tag));
        when(productRepository.findByTagsId(tag.getId())).thenReturn(List.of(product));
        when(productRepository.findById(productId)).thenReturn(Optional.empty()); // <-- key line

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId); productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO(); order.setOrder(orderRequest);

        ProductTagCondition condition = new ProductTagCondition(List.of(tagName), minQuantity, productRepository, tagRepository);
        assertFalse(condition.check(order));
    }
}
