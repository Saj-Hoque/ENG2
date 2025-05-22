package uk.ac.york.eng2.products.services.offers;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.ac.york.eng2.products.domain.OrdersByDay;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.domain.Tag;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.OrdersByDayRepository;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.repository.TagRepository;
import uk.ac.york.eng2.products.services.offers.src.actions.*;
import uk.ac.york.eng2.products.services.offers.src.conditions.DateCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.MinimumOrderAmountCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductOrderQuantityCondition;
import uk.ac.york.eng2.products.services.offers.src.conditions.ProductTagCondition;
import uk.ac.york.eng2.products.services.offers.src.rules.Rule;
import uk.ac.york.eng2.products.services.offers.src.triggers.TriggerType;
import uk.ac.york.eng2.products.services.offers.src_gen.OffersGenerator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class OffersGeneratorTest {

    @Test
    public void testFindMissingRule() {
        Rule rule = OffersGenerator.find(Collections.emptyList(), "Test Rule");
        assertNull(rule);
    }

    // The following tests are to validate the rules/offers requested.

    @Test
    public void testBakewellTartsFirst10Orders() {
        // Rule: 10% off from the first 10 orders of Bakewell Tarts in the day

        ProductRepository productRepository = mock(ProductRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        // Create the rule directly
        Rule bakewellRule = new Rule("Bakewell Tarts First 10 Orders");
        bakewellRule.addCondition(new ProductOrderQuantityCondition("Bakewell Tarts", 10, productRepository, ordersByDayRepository));
        bakewellRule.addAction(new PercentageDiscountPerProductAction("Bakewell Tarts", 10.0F, productRepository));

        // Setup test product
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Bakewell Tarts");
        product.setUnitPrice(10.0F);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(ordersByDayRepository.findByProductIdAndDay(eq(productId), eq(LocalDate.now()))).thenReturn(Optional.of(new OrdersByDay(product, LocalDate.now(), 5)));

        // Setup order
        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(orderRequest);
        order.setOrderTotal(10.0F);

        // Apply the rule directly
        PricedOrderDTO result = bakewellRule.checkAndApply(order);

        // Expect 10% off 1 Bakewell Tart → 9.0F
        assertEquals(9.0F, result.getOrderTotal());

    }

    @Test
    public void testPizzaAndIceCreamBundle() {
        // Rule: 2 large pizzas for 10 pounds, with one free large ice cream
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Rule bundleRule = new Rule("Pizza And Ice Cream Bundle");
        bundleRule.addCondition(new ProductTagCondition(List.of("Large", "Pizza"), 2, productRepository, tagRepository));
        bundleRule.addCondition(new ProductTagCondition(List.of("Large", "Ice Cream"), 1, productRepository, tagRepository));
        bundleRule.addAction(new BundlePriceAction(
                List.of(
                        new TagQuantity(List.of("Ice Cream", "Large"), 1, tagRepository),
                        new TagQuantity(List.of("Large", "Pizza"), 2, tagRepository)
                ), 10.0F, productRepository));

        Product pizza = new Product();
        pizza.setId(1L);
        pizza.setName("Large Pizza");
        pizza.setUnitPrice(8.0F);

        Product iceCream = new Product();
        iceCream.setId(2L);
        iceCream.setName("Large Ice Cream");
        iceCream.setUnitPrice(4.0F);

        Tag largeTag = new Tag();
        largeTag.setId(1L);
        largeTag.setName("Large");

        Tag pizzaTag = new Tag();
        pizzaTag.setId(2L);
        pizzaTag.setName("Pizza");

        Tag iceCreamTag = new Tag();
        iceCreamTag.setId(3L);
        iceCreamTag.setName("Ice Cream");

        when(tagRepository.findByName("Large")).thenReturn(Optional.of(largeTag));
        when(tagRepository.findByName("Pizza")).thenReturn(Optional.of(pizzaTag));
        when(tagRepository.findByName("Ice Cream")).thenReturn(Optional.of(iceCreamTag));

        when(productRepository.findByTagsId(1L)).thenReturn(List.of(pizza, iceCream));
        when(productRepository.findByTagsId(2L)).thenReturn(List.of(pizza));
        when(productRepository.findByTagsId(3L)).thenReturn(List.of(iceCream));
        when(productRepository.findById(1L)).thenReturn(Optional.of(pizza));
        when(productRepository.findById(2L)).thenReturn(Optional.of(iceCream));

        OrderRequestDTO.ProductOrder productOrder1 = new OrderRequestDTO.ProductOrder(); productOrder1.setProductId(1L); productOrder1.setQuantity(2);
        OrderRequestDTO.ProductOrder productOrder2 = new OrderRequestDTO.ProductOrder(); productOrder2.setProductId(2L); productOrder2.setQuantity(1);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(List.of(productOrder1, productOrder2));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(request);
        order.setOrderTotal(20.0F);

        PricedOrderDTO result = bundleRule.checkAndApply(order);
        assertEquals(10.0F, result.getOrderTotal()); // Replaces total with bundle price ((8+8+4) -> 10)
    }

    @Test
    public void testTwoForOnePizza() {
        // Rule: 2 pizzas for the price of 1, but only if the above offer was not already applied
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Rule rule = new Rule("2 For 1 Pizza");
        rule.addCondition(new ProductTagCondition(List.of("Pizza"), 2, productRepository, tagRepository));
        rule.addAction(new PercentageDiscountOnTaggedQuantityAction(new TagQuantity(List.of("Pizza"), 2, tagRepository), 50.0F, productRepository));

        Product pizza = new Product();
        pizza.setId(1L);
        pizza.setName("Pizza");
        pizza.setUnitPrice(10.0F);

        Tag pizzaTag = new Tag();
        pizzaTag.setId(1L);
        pizzaTag.setName("Pizza");

        when(tagRepository.findByName("Pizza")).thenReturn(Optional.of(pizzaTag));
        when(productRepository.findByTagsId(1L)).thenReturn(List.of(pizza));
        when(productRepository.findById(1L)).thenReturn(Optional.of(pizza));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder(); productOrder.setProductId(1L); productOrder.setQuantity(2);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(request);
        order.setOrderTotal(20.0F);

        PricedOrderDTO result = rule.checkAndApply(order);
        assertEquals(10.0F, result.getOrderTotal()); // 50% off 2x10 = 10 off
    }

    @Test
    public void testChristmasDayDiscount() {
        // Rule: 10% discount on Christmas Day (60 GBP minimum order)
        Rule rule = new Rule("Christmas Day Discount");
        rule.addCondition(new MinimumOrderAmountCondition(60.0F));
        rule.addCondition(new DateCondition(12, 25));
        rule.addAction(new PercentageDiscountTotalAction(10.0F));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrderTotal(100.0F);
        order.setOrder(new OrderRequestDTO());

        PricedOrderDTO result = rule.checkAndApply(order);

        if (LocalDate.now().getMonthValue() == 12 && LocalDate.now().getDayOfMonth() == 25) {
            assertEquals(90.0F, result.getOrderTotal());
        } else {
            assertEquals(100.0F, result.getOrderTotal());
        }
    }

    @Test
    public void testFiveOffChocolateCake() {
        // Rule: 5 pounds off on orders with chocolate cake (50 GBP minimum order),
        // but only if the above Christmas Day offer does not apply
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);

        Rule rule = new Rule("5 Off Orders With Chocolate Cake");
        rule.addCondition(new MinimumOrderAmountCondition(50.0F));
        rule.addCondition(new ProductTagCondition(List.of("Chocolate", "Cake"), 1, productRepository, tagRepository));
        rule.addAction(new FixedDiscountAction(5.0F));

        Product cake = new Product();
        cake.setId(1L);
        cake.setName("Chocolate Cake");
        cake.setUnitPrice(50.0F);

        Tag chocolateTag = new Tag();
        chocolateTag.setId(1L);
        chocolateTag.setName("Chocolate");

        Tag cakeTag = new Tag();
        cakeTag.setId(2L);
        cakeTag.setName("Cake");

        when(tagRepository.findByName("Chocolate")).thenReturn(Optional.of(chocolateTag));
        when(tagRepository.findByName("Cake")).thenReturn(Optional.of(cakeTag));
        when(productRepository.findByTagsId(1L)).thenReturn(List.of(cake));
        when(productRepository.findByTagsId(2L)).thenReturn(List.of(cake));
        when(productRepository.findById(1L)).thenReturn(Optional.of(cake));

        OrderRequestDTO.ProductOrder productOrder = new OrderRequestDTO.ProductOrder();
        productOrder.setProductId(1L);
        productOrder.setQuantity(1);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(List.of(productOrder));

        PricedOrderDTO order = new PricedOrderDTO();
        order.setOrder(request);
        order.setOrderTotal(60.0F);

        PricedOrderDTO result = rule.checkAndApply(order);
        assertEquals(55.0F, result.getOrderTotal()); // 5 off
    }

    @Test
    public void testFullRuleSetScenario() {
        ProductRepository productRepository = mock(ProductRepository.class);
        TagRepository tagRepository = mock(TagRepository.class);
        OrdersByDayRepository ordersByDayRepository = mock(OrdersByDayRepository.class);

        // Products and Tags setup
        Product bakewell = new Product();
        bakewell.setId(1L);
        bakewell.setName("Bakewell Tarts");
        bakewell.setUnitPrice(10.0F);

        Product pizza = new Product();
        pizza.setId(2L);
        pizza.setName("Large Pizza");
        pizza.setUnitPrice(8.0F);

        Product iceCream = new Product();
        iceCream.setId(3L);
        iceCream.setName("Large Ice Cream");
        iceCream.setUnitPrice(4.0F);

        Product cake = new Product();
        cake.setId(4L);
        cake.setName("Chocolate Cake");
        cake.setUnitPrice(50.0F);

        Tag large = new Tag();
        large.setId(1L);
        large.setName("Large");

        Tag pizzaTag = new Tag();
        pizzaTag.setId(2L);
        pizzaTag.setName("Pizza");

        Tag iceCreamTag = new Tag();
        iceCreamTag.setId(3L);
        iceCreamTag.setName("Ice Cream");

        Tag chocolate = new Tag();
        chocolate.setId(4L);
        chocolate.setName("Chocolate");

        Tag cakeTag = new Tag();
        cakeTag.setId(5L);
        cakeTag.setName("Cake");

        when(tagRepository.findByName("Large")).thenReturn(Optional.of(large));
        when(tagRepository.findByName("Pizza")).thenReturn(Optional.of(pizzaTag));
        when(tagRepository.findByName("Ice Cream")).thenReturn(Optional.of(iceCreamTag));
        when(tagRepository.findByName("Chocolate")).thenReturn(Optional.of(chocolate));
        when(tagRepository.findByName("Cake")).thenReturn(Optional.of(cakeTag));

        when(productRepository.findByTagsId(1L)).thenReturn(List.of(pizza, iceCream)); // Large
        when(productRepository.findByTagsId(2L)).thenReturn(List.of(pizza));           // Pizza
        when(productRepository.findByTagsId(3L)).thenReturn(List.of(iceCream));        // Ice Cream
        when(productRepository.findByTagsId(4L)).thenReturn(List.of(cake));            // Chocolate
        when(productRepository.findByTagsId(5L)).thenReturn(List.of(cake));            // Cake

        when(productRepository.findById(1L)).thenReturn(Optional.of(bakewell));
        when(productRepository.findById(2L)).thenReturn(Optional.of(pizza));
        when(productRepository.findById(3L)).thenReturn(Optional.of(iceCream));
        when(productRepository.findById(4L)).thenReturn(Optional.of(cake));

        when(ordersByDayRepository.findByProductIdAndDay(eq(1L), any())).thenReturn(Optional.of(new OrdersByDay(bakewell, LocalDate.now(), 5)));

        // Rule creation
        Rule bakewellRule = new Rule("Bakewell");
        bakewellRule.addCondition(new ProductOrderQuantityCondition("Bakewell Tarts", 10, productRepository, ordersByDayRepository));
        bakewellRule.addAction(new PercentageDiscountPerProductAction("Bakewell Tarts", 10.0F, productRepository));

        Rule bundleRule = new Rule("PizzaAndIceCream");
        bundleRule.addCondition(new ProductTagCondition(List.of("Large", "Pizza"), 2, productRepository, tagRepository));
        bundleRule.addCondition(new ProductTagCondition(List.of("Large", "Ice Cream"), 1, productRepository, tagRepository));
        bundleRule.addAction(new BundlePriceAction(
                List.of(
                        new TagQuantity(List.of("Ice Cream", "Large"), 1, tagRepository),
                        new TagQuantity(List.of("Large", "Pizza"), 2, tagRepository)
                ), 10.0F, productRepository));

        Rule twoForOnePizza = new Rule("2For1Pizza");
        twoForOnePizza.addCondition(new ProductTagCondition(List.of("Pizza"), 2, productRepository, tagRepository));
        twoForOnePizza.addAction(new PercentageDiscountOnTaggedQuantityAction(
                new TagQuantity(List.of("Pizza"), 2, tagRepository), 50.0F, productRepository));

        Rule christmasRule = new Rule("Christmas");
        christmasRule.addCondition(new MinimumOrderAmountCondition(60.0F));
        christmasRule.addCondition(new DateCondition(12, 25));
        christmasRule.addAction(new PercentageDiscountTotalAction(10.0F));

        Rule chocCakeRule = new Rule("ChocCake");
        chocCakeRule.addCondition(new MinimumOrderAmountCondition(50.0F));
        chocCakeRule.addCondition(new ProductTagCondition(List.of("Chocolate", "Cake"), 1, productRepository, tagRepository));
        chocCakeRule.addAction(new FixedDiscountAction(5.0F));

        // Triggers setup
        bakewellRule.addTrigger(TriggerType.ALWAYS, bundleRule);
        bundleRule.addTrigger(TriggerType.IF_MATCH, christmasRule);
        bundleRule.addTrigger(TriggerType.IF_NOT_MATCH, twoForOnePizza);
        twoForOnePizza.addTrigger(TriggerType.ALWAYS, christmasRule);
        christmasRule.addTrigger(TriggerType.IF_NOT_MATCH, chocCakeRule);

        // Order setup
        OrderRequestDTO.ProductOrder productOrder1 = new OrderRequestDTO.ProductOrder(); productOrder1.setProductId(1L); productOrder1.setQuantity(1); // bakewell
        OrderRequestDTO.ProductOrder productOrder2 = new OrderRequestDTO.ProductOrder(); productOrder2.setProductId(2L); productOrder2.setQuantity(2); // pizza
        OrderRequestDTO.ProductOrder productOrder3 = new OrderRequestDTO.ProductOrder(); productOrder3.setProductId(3L); productOrder3.setQuantity(1); // ice cream
        OrderRequestDTO.ProductOrder productOrder4 = new OrderRequestDTO.ProductOrder(); productOrder4.setProductId(4L); productOrder4.setQuantity(1); // chocolate cake

        OrderRequestDTO request = new OrderRequestDTO(); request.setOrder(List.of(productOrder1, productOrder2, productOrder3, productOrder4));
        PricedOrderDTO order = new PricedOrderDTO(); order.setOrder(request); order.setOrderTotal(72.0F);

        PricedOrderDTO result = bakewellRule.checkAndApply(order);

        if (LocalDate.now().getMonthValue() == 12 && LocalDate.now().getDayOfMonth() == 25) {
            assertEquals(54.9F, result.getOrderTotal());
        } else {
            assertEquals(56.0F, result.getOrderTotal());
        }
    }

}
