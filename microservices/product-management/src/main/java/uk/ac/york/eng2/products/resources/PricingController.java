package uk.ac.york.eng2.products.resources;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.exceptions.HttpStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.inject.Inject;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.PricingRequestDTO;
import uk.ac.york.eng2.products.dto.PricingResponseDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name="pricing")
@Controller(PricingController.PREFIX)
public class PricingController {
    public static final String PREFIX = "/pricing";

    @Inject
    private ProductRepository productRepository;


    public PricingResponseDTO calculatePricing(PricingRequestDTO request) {

        PricingResponseDTO response = new PricingResponseDTO();
        List<PricingResponseDTO.ProductPrice> productPrices = new ArrayList<>();
        Float orderTotalPrice = 0f;

        // Iterate through every Product in the Order Pricing Request
        for (PricingRequestDTO.ProductOrder productOrder : request.getOrder()){
            Long productId = productOrder.getProductId();
            Integer quantity = productOrder.getQuantity();

            // Fetch the unit price from the database
            @NonNull Optional<Product> oProduct = productRepository.findById(productId);
            if (oProduct.isEmpty()) {
                throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
            Product product = oProduct.get();
            Float unitPrice = product.getUnitPrice();

            // Calculate the total price for the current product
            Float productTotalPrice = unitPrice * quantity;

            // Create a ProductPrice object to add to the response
            PricingResponseDTO.ProductPrice productPrice = new PricingResponseDTO.ProductPrice();
            productPrice.setProductId(productId);
            productPrice.setQuantity(quantity);
            productPrice.setUnitPrice(unitPrice);

            // Add current product price to list of product prices
            productPrices.add(productPrice);

            // Add product total to overall total price
            orderTotalPrice += productTotalPrice;

        }

        response.setOrderTotalPrice(orderTotalPrice);
        response.setProductPrices(productPrices);

        return response;
    }




    // Calculate Pricing TODO:Finish this comment
    @Post
    public HttpResponse<PricingResponseDTO> priceCalculator(@Body PricingRequestDTO request) {

        PricingResponseDTO response = calculatePricing(request);

        return HttpResponse.ok(response);
    }

}

