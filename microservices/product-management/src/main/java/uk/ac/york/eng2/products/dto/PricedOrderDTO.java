package uk.ac.york.eng2.products.dto;

public class PricedOrderDTO {
    private OrderRequestDTO order;
    private Float orderTotal;

    public OrderRequestDTO getOrder() { return order; }
    public void setOrder(OrderRequestDTO order) { this.order = order; }

    public Float getOrderTotal() { return orderTotal; }
    public void setOrderTotal(Float orderTotal) { this.orderTotal = orderTotal; }
}
