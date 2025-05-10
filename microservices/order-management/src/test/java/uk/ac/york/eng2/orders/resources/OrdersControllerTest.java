package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.cs.eng2.products.api.PricingApi;
import uk.ac.york.cs.eng2.products.model.*;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.dto.OrderCreateDTO;
import uk.ac.york.eng2.orders.dto.OrderUpdateDTO;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@MicronautTest(transactional = false)
public class OrdersControllerTest {

    @Inject
    private OrdersClient ordersClient;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private CustomerRepository customerRepository;


    @BeforeEach
    public void setup() {
        orderRepository.deleteAll();
        customerRepository.deleteAll();
    }

    // Helper method - creates a order and returns generated order id
    private Long createOrder(OrderCreateDTO dto) {
        HttpResponse<Void> createResponse = ordersClient.createOrder(dto);
        Long orderId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
        return orderId;
    }

    private OrderRequestDTO templateOrderRequest() {
        List<OrderRequestDTOProductOrder> basket = new ArrayList<>();

        OrderRequestDTOProductOrder orderItem1 = new OrderRequestDTOProductOrder();
        orderItem1.setProductId(1L);
        orderItem1.setQuantity(1);
        basket.add(orderItem1);


        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(basket);

        return request;
    }

    private OrderRequestDTO templateMultipleOrderRequest() {
        List<OrderRequestDTOProductOrder> basket = new ArrayList<>();

        OrderRequestDTOProductOrder orderItem1 = new OrderRequestDTOProductOrder();
        orderItem1.setProductId(1L);
        orderItem1.setQuantity(1);
        basket.add(orderItem1);

        OrderRequestDTOProductOrder orderItem2 = new OrderRequestDTOProductOrder();
        orderItem2.setProductId(2L);
        orderItem2.setQuantity(2);
        basket.add(orderItem2);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(basket);

        return request;
    }

    // Test that orderRepository is empty.
    @Test
    public void noOrders() {
        assertEquals(0, ordersClient.getOrders().size());
    }

    // Test creating an order and list them to verify that orderRepository has been updated accordingly
    @Test
    public void createAndListOrders() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());

        ordersClient.createOrder(o);
        assertEquals(1, ordersClient.getOrders().size());
    }

    // Test creating an order with a missing customer
    @Test
    public void createOrderMissingCustomer() {
        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(1L);
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());

        HttpResponse response = ordersClient.createOrder(o);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test creating an order with no customer id present
    @Test
    public void createOrderNullCustomer() {
        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(null);
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());

        try {
            ordersClient.createOrder(o);
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }

    // Test creating an order where there is an error invoking pricingApi
    @Test
    public void createOrderErrorApi() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(null);

        try {
            ordersClient.createOrder(o);
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, e.getStatus());
        }
    }

    // Test retrieving an order by ID
    @Test
    public void fetchOrderById() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());

        Long orderId = createOrder(o);
        Order fetchedOrder = ordersClient.getOrder(orderId);

        assertEquals(o.getAddress(), fetchedOrder.getAddress());
        // Customer ID not checked here because @JsonIgnore
        assertEquals(LocalDate.now(), fetchedOrder.getDateCreated());
        assertEquals(1F, fetchedOrder.getTotalAmount());
    }

    // Test retrieving an order with a missing ID
    @Test
    public void fetchMissingOrder() {
        assertNull(ordersClient.getOrder(123L));
    }

    // Test updating an orders details and verifying these values have changed accordingly
    @Test
    public void updateOrderDetails() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());
        Long orderId = createOrder(o);

        // Update the order with paid and delivered set to true
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setPaid(true);
        update.setDelivered(true);

        ordersClient.updateOrder(update, orderId);

        Order updatedOrder = ordersClient.getOrder(orderId);
        assertTrue(updatedOrder.getPaid());
        assertTrue(updatedOrder.getDelivered());
    }

    // Test updating a non-existent order returns 404 Not Found
    @Test
    public void updateMissingOrder() {

        // Update the order with paid and delivered set to true
        OrderUpdateDTO update = new OrderUpdateDTO();
        update.setPaid(true);
        update.setDelivered(true);

        HttpResponse response = ordersClient.updateOrder(update, 123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test deleting an order and verify that orderRepository has been updated accordingly
    @Test
    public void deleteOrderById() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());
        Long orderId = createOrder(o);

        ordersClient.deleteOrder(orderId);
        assertEquals(0, ordersClient.getOrders().size());
    }

    // Test deleting a non-existent order returns 404 Not Found
    @Test
    public void deleteMissingOrder() {
        HttpResponse response = ordersClient.deleteOrder(123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test retrieving and listing customers of an order
    @Test
    public void listOrderCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        OrderCreateDTO o = new OrderCreateDTO();
        o.setCustomerId(customer.getId());
        o.setAddress("Test");
        o.setOrderRequest(templateOrderRequest());
        Long orderId = createOrder(o);


        HttpResponse<Customer> response = ordersClient.getOrderCustomer(orderId);
        assertEquals(HttpStatus.OK, response.getStatus());

        Customer retrievedCustomer = response.body();
        assertNotNull(retrievedCustomer);
        assertEquals(customer.getId(), retrievedCustomer.getId());
        assertEquals(customer.getEmail(), retrievedCustomer.getEmail());
        assertEquals(customer.getFirstName(), retrievedCustomer.getFirstName());
        assertEquals(customer.getFamilyName(), retrievedCustomer.getFamilyName());

    }

    // Test listing customer of non-existing order
    @Test
    public void listOrderCustomerMissingOrder() {
        HttpResponse<Customer> customer = ordersClient.getOrderCustomer(123L);
        assertEquals(HttpStatus.NOT_FOUND, customer.getStatus());
    }

    @MockBean (PricingApi.class)
    public PricingApi getPricingApi() {
        PricingApi mock = mock(PricingApi.class);

        // Create mock orderResponseDTO
        OrderResponseDTO orderResponse = new OrderResponseDTO();
        List<OrderResponseDTOProductPrice> productPrices = new ArrayList<>(); //TODO: when orderItem is done
        orderResponse.setProductPrices(productPrices);
        orderResponse.setOrderTotalPrice(1F);
        HttpResponse<OrderResponseDTO> mockOrderResponse = HttpResponse.ok(orderResponse);

        // Create mock orderResponseDTO with multiple product orders
        OrderResponseDTO multipleOrderResponse = new OrderResponseDTO();
        List<OrderResponseDTOProductPrice> multipleProductPrices = new ArrayList<>(); //TODO: when orderItem is done
        multipleOrderResponse.setProductPrices(multipleProductPrices);
        multipleOrderResponse.setOrderTotalPrice(6F);
        HttpResponse<OrderResponseDTO> mockMultipleOrderResponse = HttpResponse.ok(multipleOrderResponse);


        when(mock.priceCalculator(templateOrderRequest())).thenReturn(mockOrderResponse);
        when(mock.priceCalculator(templateMultipleOrderRequest())).thenReturn(mockMultipleOrderResponse);
        when(mock.priceCalculator(null)).thenReturn(HttpResponse.status(HttpStatus.INTERNAL_SERVER_ERROR));

        return mock;
    }

}
