package uk.ac.york.eng2.products.dto;

import io.micronaut.serde.annotation.Serdeable;

import java.util.List;

@Serdeable
public class OrderRequestDTO {
    private List<ProductOrder> order;

    @Serdeable
    public static class ProductOrder {
        private Long productId;
        private Integer quantity;

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }

        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    public List<ProductOrder> getOrder() { return order; }
    public void setOrder(List<ProductOrder> order) { this.order = order; }
}
