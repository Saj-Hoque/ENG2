package uk.ac.york.eng2.orders.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class OrderUpdateDTO {

    private Boolean paid;
    private Boolean delivered;

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }

    public Boolean getDelivered() { return delivered; }
    public void setDelivered(Boolean delivered) { this.delivered = delivered; }
}
