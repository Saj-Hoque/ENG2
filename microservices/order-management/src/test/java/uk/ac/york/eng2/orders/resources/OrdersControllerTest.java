package uk.ac.york.eng2.orders.resources;

import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ac.york.cs.eng2.products.api.PricingApi;
import uk.ac.york.cs.eng2.products.api.ProductsApi;
import uk.ac.york.cs.eng2.products.model.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@MicronautTest(transactional = false)
public class OrdersControllerTest {

    private static final String PRODUCT_NAME_1 = "Product 1";
    private static final String PRODUCT_NAME_2 = "Product 2";
    private static final String PRODUCT_NAME_3 = "Product 3";

    private static final Float UNIT_PRICE_A = 1.00F;
    private static final Float UNIT_PRICE_B = 2.50F;
    private static final Float UNIT_PRICE_C = 5.00F;

    @Inject private PricingApi pricingApi;
    @Inject private ProductsApi productsApi;


    @Test
    public void testtest(){
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setName(PRODUCT_NAME_1);
        dto.setUnitPrice(UNIT_PRICE_A);

        HttpResponse<Void> response = productsApi.createProduct(dto);
        assertEquals(HttpStatus.CREATED, response.getStatus());

        Long productId = Long.valueOf(response.header(HttpHeaders.LOCATION).split("/")[2]);
        Product fetchedProduct = productsApi.getProduct(productId).body();
        assertEquals(dto.getName(), fetchedProduct.getName());
    }

    //    @Test
//    public void productDoesNotExist() {
//
//        List<PricingRequestDTOProductOrder> basket = new ArrayList<>();
//        PricingRequestDTOProductOrder order = new PricingRequestDTOProductOrder();
//        order.setProductId(1L);
//        order.setQuantity(1);
//        basket.add(order);
//
//        PricingRequestDTO request = new PricingRequestDTO();
//        request.setOrder(basket);
//
//        HttpResponse<PricingResponseDTO> response = pricingApi.priceCalculator(request);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatus());
//    }

//    @Test
//    public void acquireOrderTotal() {
//
//
//        List<PricingRequestDTOProductOrder> orderList = new ArrayList<>();
//
//
//
//        PricingRequestDTOProductOrder orderItem1 = new PricingRequestDTOProductOrder();
//        // Example: orderItem1.setProductId("prod-001");
//        // Example: orderItem1.setQuantity(2);or
//        // Fill in other fields as needed
//
//        PricingRequestDTOProductOrder orderItem2 = new PricingRequestDTOProductOrder();
//        // Example: orderItem2.setProductId("prod-002");
//        // Example: orderItem2.setQuantity(1);
//        // Fill in other fields as needed
//
//        orderList.add(orderItem1);
//        orderList.add(orderItem2);
//
//        PricingRequestDTO pricingRequest = new PricingRequestDTO();
//        pricingRequest.setOrder(orderList);
//
//
//    }
    @MockBean (ProductsApi.class)
    public ProductsApi getProductsApi() {
        ProductsApi mock = mock(ProductsApi.class);

        Product productExample = new Product();
        productExample.setName(PRODUCT_NAME_1);
        productExample.setUnitPrice(UNIT_PRICE_A);
        MutableHttpResponse<Product> mockGetResponse = HttpResponse.ok(productExample);

        MutableHttpResponse<Void> mockCreateResponse = HttpResponse.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/products/1")
                .body(null);

        when(mock.createProduct(any())).thenReturn(mockCreateResponse);
        when(mock.getProduct(any())).thenReturn((mockGetResponse));

        return mock;
    }

}
