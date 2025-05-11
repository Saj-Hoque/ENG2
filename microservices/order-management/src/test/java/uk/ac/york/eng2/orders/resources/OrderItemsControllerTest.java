package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.domain.OrderItem;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderItemRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest(transactional = false)
public class OrderItemsControllerTest {

    @Inject
    private OrderItemsClient orderItemsClient;

    @Inject
    private OrderItemRepository orderItemRepository;
    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderItemRepository.deleteAll();
        customerRepository.deleteAll();
        orderRepository.deleteAll();
    }

    // Test that orderItemsRepository is empty.
    @Test
    public void noOrderItems() {
        assertEquals(0, orderItemsClient.getOrderItems().size());
    }

    // Test retrieving all orderItems
    @Test
    public void fetchOrderItems() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setAddress("Test");
        order.setPaid(false);
        order.setDelivered(false);
        order.setTotalAmount(100F);
        order.setCustomer(customer);
        order = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setOrder(order);
        orderItem.setQuantity(1);
        orderItem.setUnitPrice(1.0F);
        orderItem = orderItemRepository.save(orderItem);

        assertEquals(1, orderItemsClient.getOrderItems().size());
    }

    // Test retrieving an orderItem by ID
    @Test
    public void fetchOrderItemsById() {

        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setAddress("Test");
        order.setPaid(false);
        order.setDelivered(false);
        order.setTotalAmount(100F);
        order.setCustomer(customer);
        order = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setOrder(order);
        orderItem.setQuantity(1);
        orderItem.setUnitPrice(1.0F);
        orderItem = orderItemRepository.save(orderItem);

        OrderItem fetchedOrderItem = orderItemsClient.getOrderItem(orderItem.getId());
        assertEquals(orderItem.getId(), fetchedOrderItem.getId());
        assertEquals(orderItem.getProductId(), fetchedOrderItem.getProductId());
        assertEquals(orderItem.getQuantity(), fetchedOrderItem.getQuantity());
        assertEquals(orderItem.getUnitPrice(), fetchedOrderItem.getUnitPrice());
    }

    // Test retrieving and listing order of an order item
    @Test
    public void listOrderItemsOrder() {

        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setAddress("Test");
        order.setPaid(false);
        order.setDelivered(false);
        order.setTotalAmount(100F);
        order.setCustomer(customer);
        order = orderRepository.save(order);

        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(1L);
        orderItem.setOrder(order);
        orderItem.setQuantity(1);
        orderItem.setUnitPrice(1.0F);
        orderItem = orderItemRepository.save(orderItem);

        HttpResponse<Order> response = orderItemsClient.getOrderItemOrder(orderItem.getId());
        assertEquals(HttpStatus.OK, response.getStatus());

        Order retrievedOrder = response.body();
        assertNotNull(retrievedOrder);
        assertEquals(order.getId(), retrievedOrder.getId());
        assertEquals(order.getDateCreated(), retrievedOrder.getDateCreated());
        assertEquals(order.getAddress(), retrievedOrder.getAddress());
        assertEquals(order.getPaid(), retrievedOrder.getPaid());
        assertEquals(order.getDelivered(), retrievedOrder.getDelivered());
        assertEquals(order.getTotalAmount(), retrievedOrder.getTotalAmount());

    }

    // Test listing order of non-existing order item
    @Test
    public void listOrderItemOrderMissingOrderItem() {
        HttpResponse<Order> order = orderItemsClient.getOrderItemOrder(123L);
        assertEquals(HttpStatus.NOT_FOUND, order.getStatus());
    }

}
