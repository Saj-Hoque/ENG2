package uk.ac.york.eng2.orders.dto;

import io.micronaut.serde.annotation.Serdeable;
import uk.ac.york.cs.eng2.products.model.OrderRequestDTO;

@Serdeable
public class OrderCreateDTO {

    private Long customerId;
    private String address;
    private OrderRequestDTO orderRequest;
    // `DateCreated` and `totalAmount` to be calculated during order creation - not set by User
    // `Paid` and `Delivered` (default: false) are to be set when updating the order

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address;}

    public OrderRequestDTO getOrderRequest() { return orderRequest; }
    public void setOrderRequest(OrderRequestDTO orderRequest) { this.orderRequest = orderRequest; }
}
