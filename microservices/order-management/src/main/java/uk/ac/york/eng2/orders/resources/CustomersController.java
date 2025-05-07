package uk.ac.york.eng2.orders.resources;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.exceptions.HttpStatusException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.domain.Order;
import uk.ac.york.eng2.orders.dto.CustomerCreateDTO;
import uk.ac.york.eng2.orders.repository.CustomerRepository;
import uk.ac.york.eng2.orders.repository.OrderRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@io.swagger.v3.oas.annotations.tags.Tag(name="customers")
@Controller(CustomersController.PREFIX)
public class CustomersController {
    public static final String PREFIX = "/customers";

    @Inject
    private CustomerRepository customerRepository;
    @Inject
    private OrderRepository orderRepository;

    // List all customers
    @Get
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    // Retrieve customer by id
    @Get("/{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    // Retrieve orders of customer
    @Get("/{id}/orders")
    public HttpResponse<List<Order>> getCustomerOrders(@PathVariable Long id) {

        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        // If the customer exists, return the orders associated with the customer
        List<Order> orders = orderRepository.findByCustomerId(id);;
        return HttpResponse.ok(orders);

    }

    // Create a new customer
    @Post
    public HttpResponse<Void> createCustomer(@Body CustomerCreateDTO dto) {

        @NonNull Optional<Customer> existingCustomer = customerRepository.findByEmail(dto.getEmail());
        if (existingCustomer.isPresent()) {
            throw new HttpStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setFirstName(dto.getFirstName());
        customer.setFamilyName(dto.getFamilyName());
        customer = customerRepository.save(customer);
        return HttpResponse.created(URI.create(PREFIX + "/" + customer.getId()));
    }

    // Update a customer (by id)
    @Transactional
    @Put("/{id}")
    public void updateCustomer(@Body CustomerCreateDTO dto, @PathVariable Long id) {
        @NonNull Optional<Customer> oCustomer = customerRepository.findById(id);
        if (oCustomer.isEmpty()) {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }

        @NonNull Optional<Customer> customerWithEmail = customerRepository.findByEmail(dto.getEmail());
        if (customerWithEmail.isPresent() && !customerWithEmail.get().getId().equals(id)) {
            throw new HttpStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Customer customer = oCustomer.get();
        customer.setEmail(dto.getEmail());
        customer.setFirstName(dto.getFirstName());
        customer.setFamilyName(dto.getFamilyName());
        customerRepository.save(customer);
    }

    // Delete a customer (by id)
    @Delete("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new HttpStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
    }


}
