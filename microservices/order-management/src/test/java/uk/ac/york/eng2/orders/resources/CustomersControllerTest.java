package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.dto.CustomerCreateDTO;
import uk.ac.york.eng2.orders.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.*;


@MicronautTest(rebuildContext = false)
public class CustomersControllerTest {

    @Inject
    private CustomersClient customersClient;

    @Inject
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setup() {
        customerRepository.deleteAll();
    }

    // Helper method - creates a customer and returns generated customer id
    private Long createCustomer(CustomerCreateDTO b) {
        HttpResponse<Void> createResponse = customersClient.createCustomer(b);
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
    }

    // Test retrieving a customer with a missing ID
    @Test
    public void fetchMissingCustomer() {
        assertNull(customersClient.getCustomer(123L));
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

    // Test updating a customers details and verifying that customerRepository has been updated accordingly
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

}
