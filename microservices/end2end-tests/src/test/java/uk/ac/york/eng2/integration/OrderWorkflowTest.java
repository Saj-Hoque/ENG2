package uk.ac.york.eng2.integration;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import org.junit.jupiter.api.Test;
import uk.ac.york.cs.eng2.orders.api.CustomersApi;
import uk.ac.york.cs.eng2.orders.api.OrderItemApi;
import uk.ac.york.cs.eng2.orders.api.OrdersApi;
import uk.ac.york.cs.eng2.orders.model.*;
import uk.ac.york.cs.eng2.products.api.OrdersByDayApi;
import uk.ac.york.cs.eng2.products.api.PricingApi;
import uk.ac.york.cs.eng2.products.api.ProductsApi;
import uk.ac.york.cs.eng2.products.model.OrderResponseDTO;
import uk.ac.york.cs.eng2.products.model.OrdersByDay;
import uk.ac.york.cs.eng2.products.model.ProductCreateDTO;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class OrderWorkflowTest {

    @Inject
    private ProductsApi productsApi;

    @Inject
    private CustomersApi customersApi;

    @Inject
    private OrdersApi ordersApi;

    @Inject
    private OrdersByDayApi ordersByDayApi;

    @Inject
    private OrderItemApi orderItemApi;
    @Inject
    private PricingApi pricingApi;


    @Test
    public void createOrder() {

        // Create a Product
        ProductCreateDTO product = new ProductCreateDTO();
        product.setName("Test Product");
        product.setUnitPrice(1.23F);

        HttpResponse<Void> productCreatedResponse = productsApi.createProduct(product);
        assertEquals(HttpStatus.CREATED, productCreatedResponse.getStatus());
        Long productId = Long.valueOf(productCreatedResponse.header(HttpHeaders.LOCATION).split("/")[2]);


        // Create an order request based on the product above

        List<OrderRequestDTOProductOrder> basket = new ArrayList<>();

        OrderRequestDTOProductOrder productOrder = new OrderRequestDTOProductOrder();
        productOrder.setProductId(productId);
        productOrder.setQuantity(1);
        basket.add(productOrder);

        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setOrder(basket);


        // Create a Customer
        CustomerCreateDTO customer = new CustomerCreateDTO();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");

        HttpResponse<Void> customerCreatedResponse = customersApi.createCustomer(customer);
        assertEquals(HttpStatus.CREATED, customerCreatedResponse.getStatus());
        Long customerId = Long.valueOf(customerCreatedResponse.header(HttpHeaders.LOCATION).split("/")[2]);


        // Set up an Order and set its order request and associate it with the customer
        OrderCreateDTO order = new OrderCreateDTO();
        order.setAddress("Test Address");
        order.setCustomerId(customerId);
        order.setOrderRequest(orderRequest);

        // Create the order
        HttpResponse<Void> orderCreatedResponse = ordersApi.createOrder(order);
        assertEquals(HttpStatus.CREATED, orderCreatedResponse.getStatus());
        Long orderId = Long.valueOf(orderCreatedResponse.header(HttpHeaders.LOCATION).split("/")[2]);

        // Check that the Kafka consumer has obtained the order details to update `Orders by Day`
        await().atMost(Duration.ofSeconds(20)).until(OrdersByDayIsUpdated());

        OrdersByDay ordersByDay = ordersByDayApi.getOrdersByDay().body().get(0);
        assertEquals(LocalDate.now(), ordersByDay.getDay());
        assertEquals(1, ordersByDay.getCount());
        assertEquals(productId, ordersByDay.getProduct().getId());

        // Check that the OrderItems are updated once the order is created
        HttpResponse<List<OrderItem>> orderItemsResponse = orderItemApi.getOrderItems();
        assertEquals(HttpStatus.OK, orderItemsResponse.getStatus());

        OrderItem orderItem = orderItemsResponse.body().get(0);
        assertEquals(productId, orderItem.getProductId());
        assertEquals(productOrder.getQuantity(), orderItem.getQuantity());
        assertEquals(product.getUnitPrice(), orderItem.getUnitPrice());

        // Verify order details against orderItem order details
        HttpResponse<Order> orderItemsOrderResponse = orderItemApi.getOrderItemOrder(orderItem.getId());
        assertEquals(HttpStatus.OK, orderItemsOrderResponse.getStatus());
        Order orderItemsOrderDetails = orderItemsOrderResponse.body();

        HttpResponse<Order> orderResponse = ordersApi.getOrder(orderId);
        assertEquals(HttpStatus.OK, orderResponse.getStatus());
        Order orderDetails = orderResponse.body();

        assertEquals(orderItemsOrderDetails.getId(), orderDetails.getId());

        // Verify order details against priceCalculator calculated values
        HttpResponse<@Valid OrderResponseDTO> orderResponseDTO = pricingApi.priceCalculator(orderRequest);
        assertEquals(HttpStatus.OK, orderResponse.getStatus());
        OrderResponseDTO priceCalculatorResponse = orderResponseDTO.body();

        assertEquals(priceCalculatorResponse.getOrderTotalPrice(), orderDetails.getTotalAmount() );

    }


    protected Callable<Boolean> OrdersByDayIsUpdated() {
        return () -> {
            HttpResponse<List<OrdersByDay>> ordersByDays = ordersByDayApi.getOrdersByDay();
            if (ordersByDays.getBody().isPresent()) {
                return ordersByDays.body().size() == 1;
            }
            return false;
        };
    }



}
