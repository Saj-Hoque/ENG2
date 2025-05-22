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
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.OrderResponseDTO;
import uk.ac.york.eng2.products.dto.PricedOrderDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;
import uk.ac.york.eng2.products.services.offers.src_gen.OffersGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Tag(name="pricing")
@Controller(PricingController.PREFIX)
public class PricingController {
    public static final String PREFIX = "/pricing";

    @Inject
    private ProductRepository productRepository;

    @Inject
    private OffersGenerator offersGenerator;


    public OrderResponseDTO calculatePricing(OrderRequestDTO request) {

        OrderResponseDTO response = new OrderResponseDTO();
        List<OrderResponseDTO.ProductPrice> productPrices = new ArrayList<>();
        Float baseOrderTotalPrice = 0f;

        // Iterate through every Product in the Order Pricing Request
        for (OrderRequestDTO.ProductOrder productOrder : request.getOrder()){
            Long productId = productOrder.getProductId();
            Integer quantity = productOrder.getQuantity();

            // Fetch the unit price from the database
            @NonNull Optional<Product> oProduct = productRepository.findById(productId);
            if (oProduct.isEmpty()) {
                throw new HttpStatusException(HttpStatus.NOT_FOUND, "Product not found");
            }
            Product product = oProduct.get();
            Float unitPrice = product.getUnitPrice();

            // Calculate the base total price for the current product
            Float productTotalPrice = unitPrice * quantity;

            // Create a ProductPrice object to add to the response
            OrderResponseDTO.ProductPrice productPrice = new OrderResponseDTO.ProductPrice();
            productPrice.setProductId(productId);
            productPrice.setQuantity(quantity);
            productPrice.setUnitPrice(unitPrice);

            // Add current product price to list of product prices
            productPrices.add(productPrice);

            // Add product total to overall total price
            baseOrderTotalPrice += productTotalPrice;

        }

        // Setup order details before applying offers
        PricedOrderDTO orderDetails = new PricedOrderDTO();
        orderDetails.setOrder(request);
        orderDetails.setOrderTotal(baseOrderTotalPrice);

        // Apply offers using generated logic in services/offers
        PricedOrderDTO updatedOrderDetails = offersGenerator.applyOffers(orderDetails);

        response.setOrderTotalPrice(updatedOrderDetails.getOrderTotal());
        response.setProductPrices(productPrices);

        return response;
    }




    // Calculate Pricing - Used for testing/debugging the pricing calculator - NOT FOR CUSTOMER USE
    @Post
    public HttpResponse<OrderResponseDTO> priceCalculator(@Body OrderRequestDTO request) {

        OrderResponseDTO response = calculatePricing(request);

        return HttpResponse.ok(response);
    }

}

