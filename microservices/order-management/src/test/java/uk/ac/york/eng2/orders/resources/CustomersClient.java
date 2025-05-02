package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.orders.domain.Customer;
import uk.ac.york.eng2.orders.dto.CustomerCreateDTO;

import java.util.List;

@Client("/customers")
public interface CustomersClient {

    @Get
    List<Customer> getCustomers();

    @Get("/{id}")
    Customer getCustomer(@PathVariable Long id);

    @Post
    HttpResponse<Void> createCustomer(@Body CustomerCreateDTO dto);

    @Put("/{id}")
    HttpResponse<Void> updateCustomer(@Body CustomerCreateDTO dto, @PathVariable Long id);

    @Delete("/{id}")
    HttpResponse<Void> deleteCustomer(@PathVariable Long id);

}
