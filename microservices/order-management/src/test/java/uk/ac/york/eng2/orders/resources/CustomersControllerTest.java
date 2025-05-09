package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.dto.CustomerCreateDTO;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


@MicronautTest(transactional = false)
public class CustomersControllerTest {

    @Inject
    private CustomersClient customersClient;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
        orderRepository.deleteAll();
    }

    // Helper method - creates a customer and returns generated customer id
    private Long createCustomer(CustomerCreateDTO dto) {
        HttpResponse<Void> createResponse = customersClient.createCustomer(dto);
        Long customerId = Long.valueOf(createResponse.header(HttpHeaders.LOCATION).split("/")[2]);
        return customerId;
    }


    // Test that customerRepository is empty.
    @Test
    public void noCustomers() {
        assertEquals(0, customersClient.getCustomers().size());
    }

    // Test creating a customer and list them to verify that customerRepository has been updated accordingly
    @Test
    public void createAndListCustomers() {
        CustomerCreateDTO c = new CustomerCreateDTO();
        c.setEmail("test@test.com");
        c.setFirstName("Test");
        c.setFamilyName("Test");

        customersClient.createCustomer(c);
        assertEquals(1, customersClient.getCustomers().size());
    }

    // Test retrieving a customer by ID
    @Test
    public void fetchCustomerById() {
        CustomerCreateDTO c = new CustomerCreateDTO();
        c.setEmail("test@test.com");
        c.setFirstName("Test");
        c.setFamilyName("Test");

        Long customerId = createCustomer(c);
        Customer fetchedCustomer = customersClient.getCustomer(customerId);
        assertEquals(c.getEmail(), fetchedCustomer.getEmail());
        assertEquals(c.getFirstName(), fetchedCustomer.getFirstName());
        assertEquals(c.getFamilyName(), fetchedCustomer.getFamilyName());
    }

    // Test retrieving a customer with a missing ID
    @Test
    public void fetchMissingCustomer() {
        assertNull(customersClient.getCustomer(123L));
    }

    // Test retrieving a customer with an email that already exists
    @Test
    public void fetchCustomerWithExistingEmail() {
        CustomerCreateDTO customer1 = new CustomerCreateDTO();
        customer1.setEmail("test@test.com");
        customer1.setFirstName("Test 1");
        customer1.setFamilyName("Test 1");
        customersClient.createCustomer(customer1);

        CustomerCreateDTO customer2 = new CustomerCreateDTO();
        customer2.setEmail("test@test.com");
        customer2.setFirstName("Test 2");
        customer2.setFamilyName("Test 2");

        try {
            customersClient.createCustomer(customer2);
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.CONFLICT, e.getStatus());
        }

    }

    // Test updating a customers details and verifying these values have changed accordingly
    @Test
    public void updateCustomerDetails() {
        CustomerCreateDTO c = new CustomerCreateDTO();
        c.setEmail("test@test.com");
        c.setFirstName("Test");
        c.setFamilyName("Test");
        Long customerId = createCustomer(c);

        c.setEmail("updated@test.com");
        c.setFirstName("Updated");
        c.setFamilyName("Updated");
        customersClient.updateCustomer(c, customerId);

        Customer updatedCustomer = customersClient.getCustomer(customerId);
        assertEquals(c.getEmail(), updatedCustomer.getEmail());
        assertEquals(c.getFirstName(), updatedCustomer.getFirstName());
        assertEquals(c.getFamilyName(), updatedCustomer.getFamilyName());
    }

    // Test updating a non-existent customer returns 404 Not Found
    @Test
    public void updateMissingCustomer() {
        CustomerCreateDTO c = new CustomerCreateDTO();
        c.setEmail("test@test.com");
        c.setFirstName("Test");
        c.setFamilyName("Test");

        HttpResponse response = customersClient.updateCustomer(c, 123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }

    // Test updating a customer with an email that already exists
    @Test
    public void updateCustomerWithExistingEmail() {
        CustomerCreateDTO customer1 = new CustomerCreateDTO();
        customer1.setEmail("test@test.com");
        customer1.setFirstName("Test 1");
        customer1.setFamilyName("Test 1");
        Long customerId1 = createCustomer(customer1);

        CustomerCreateDTO customer2 = new CustomerCreateDTO();
        customer2.setEmail("test2@test.com");
        customer2.setFirstName("Test 2");
        customer2.setFamilyName("Test 2");
        Long customerId2 = createCustomer(customer2);

        // Updated Customer
        CustomerCreateDTO updatedCustomer = new CustomerCreateDTO();
        updatedCustomer.setFirstName("Updated");
        updatedCustomer.setFamilyName("Updated");
        updatedCustomer.setEmail("test@test.com");

        // Initially try updating with non-existing email
        HttpResponse response = customersClient.updateCustomer(updatedCustomer, customerId1);
        assertEquals(HttpStatus.OK, response.getStatus());

        // Secondly try updating with existing email

        try {
            customersClient.updateCustomer(updatedCustomer, customerId2);
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.CONFLICT, e.getStatus());
        }

    }

    // Test deleting a customer and verify that customerRepository has been updated accordingly
    @Test
    public void deleteCustomerById() {
        CustomerCreateDTO c = new CustomerCreateDTO();
        c.setEmail("test@test.com");
        c.setFirstName("Test");
        c.setFamilyName("Test");

        Long customerId = createCustomer(c);
        customersClient.deleteCustomer(customerId);
        assertEquals(0, customersClient.getCustomers().size());
    }

    // Test deleting a non-existent customer returns 404 Not Found
    @Test
    public void deleteMissingCustomer() {
        HttpResponse response = customersClient.deleteCustomer(123L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }


    // Test retrieving and listing orders of a customer
    @Test
    public void listCustomerOrders() {
        Customer customer = new Customer();
        customer.setEmail("test@test.com");
        customer.setFirstName("Test");
        customer.setFamilyName("Test");
        customer = customerRepository.save(customer);

        assertEquals(0, customersClient.getCustomerOrders(customer.getId()).body().size());

        Order order = new Order();
        order.setDateCreated(LocalDate.now());
        order.setAddress("Test");
        order.setPaid(false);
        order.setDelivered(false);
        order.setTotalAmount(100F);
        order.setCustomer(customer);
        order = orderRepository.save(order);

        HttpResponse<List<Order>> response = customersClient.getCustomerOrders(customer.getId());
        List<Order> orders = response.body();
        assertEquals(1, orders.size());
        assertEquals(order.getAddress(), orders.get(0).getAddress());

    }

    // Test listing orders of non-existing customer
    @Test
    public void listCustomerOrdersMissingCustomer() {
        HttpResponse<List<Order>> orders = customersClient.getCustomerOrders(123L);
        assertEquals(HttpStatus.NOT_FOUND, orders.getStatus());
    }

}
