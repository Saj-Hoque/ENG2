package uk.ac.york.eng2.products.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class PricingResponseDTO {
    private Float orderTotalPrice;
    private List<ProductPrice> productPrices;

    @Serdeable
    public static class ProductPrice {
        private Long productId;
        private Float unitPrice;
        private Integer quantity;

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public Float getUnitPrice() { return unitPrice; }
        public void setUnitPrice(Float unitPrice) { this.unitPrice = unitPrice; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    public Float getOrderTotalPrice() { return orderTotalPrice; }
    public void setOrderTotalPrice(Float orderTotalPrice) { this.orderTotalPrice = orderTotalPrice; }

    public List<ProductPrice> getProductPrices() { return productPrices; }
    public void setProductPrices(List<ProductPrice> productPrices) { this.productPrices = productPrices; }
}
