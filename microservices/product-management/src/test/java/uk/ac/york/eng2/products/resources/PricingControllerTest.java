package uk.ac.york.eng2.products.resources;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.eng2.products.domain.Product;
import uk.ac.york.eng2.products.dto.OrderRequestDTO;
import uk.ac.york.eng2.products.dto.OrderResponseDTO;
import uk.ac.york.eng2.products.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@MicronautTest(transactional = false)
public class PricingControllerTest {

    @Inject
    private PricingClient pricingClient;

    @Inject
    private ProductRepository productRepository;  // Micronaut injects a real repo, but we'll replace with a mock


    @BeforeEach
    public void setup() {
        productRepository.deleteAll();
    }


    @Test
    void calculatePricingValid() {

        List<OrderRequestDTO.ProductOrder> basket = new ArrayList<>();

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setUnitPrice(1.00F);
        product1 = productRepository.save(product1);

        OrderRequestDTO.ProductOrder order1 = new OrderRequestDTO.ProductOrder();
        order1.setProductId(product1.getId());
        order1.setQuantity(1);
        basket.add(order1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setUnitPrice(2.50F);
        product2 = productRepository.save(product2);

        OrderRequestDTO.ProductOrder order2 = new OrderRequestDTO.ProductOrder();
        order2.setProductId(product2.getId());
        order2.setQuantity(2);
        basket.add(order2);

        Product product3 = new Product();
        product3.setName("Product 3");
        product3.setUnitPrice(5.00F);
        product3 = productRepository.save(product3);

        OrderRequestDTO.ProductOrder order3 = new OrderRequestDTO.ProductOrder();
        order3.setProductId(product3.getId());
        order3.setQuantity(3);
        basket.add(order3);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(basket);

        HttpResponse<OrderResponseDTO> response = pricingClient.priceCalculator(request);

        OrderResponseDTO.ProductPrice productPrice1 = response.body().getProductPrices().get(0);
        assertEquals(product1.getId(), productPrice1.getProductId());
        assertEquals(1, productPrice1.getQuantity());
        assertEquals(product1.getUnitPrice(), productPrice1.getUnitPrice());

        OrderResponseDTO.ProductPrice productPrice2 = response.body().getProductPrices().get(1);
        assertEquals(product2.getId(), productPrice2.getProductId());
        assertEquals(2, productPrice2.getQuantity());
        assertEquals(product2.getUnitPrice(), productPrice2.getUnitPrice());

        OrderResponseDTO.ProductPrice productPrice3 = response.body().getProductPrices().get(2);
        assertEquals(product3.getId(), productPrice3.getProductId());
        assertEquals(3, productPrice3.getQuantity());
        assertEquals(product3.getUnitPrice(), productPrice3.getUnitPrice());

        assertEquals(21.00F, response.body().getOrderTotalPrice());

    }

    // Testing to calculate the price of an order with a non-existing product
    @Test
    void calculatePricingInvalid() {
        List<OrderRequestDTO.ProductOrder> basket = new ArrayList<>();

        OrderRequestDTO.ProductOrder order = new OrderRequestDTO.ProductOrder();
        order.setProductId(1L);
        order.setQuantity(1);
        basket.add(order);

        OrderRequestDTO request = new OrderRequestDTO();
        request.setOrder(basket);

        HttpResponse<OrderResponseDTO> response = pricingClient.priceCalculator(request);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
    }
}