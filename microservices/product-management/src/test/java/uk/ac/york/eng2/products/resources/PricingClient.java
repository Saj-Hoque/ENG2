package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.OrderResponseDTO;

@Client("/pricing")
public interface PricingClient {

    @Post
    HttpResponse<OrderResponseDTO> priceCalculator(@Body OrderRequestDTO request);

}
