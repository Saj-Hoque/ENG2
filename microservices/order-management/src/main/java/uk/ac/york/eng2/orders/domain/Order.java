package uk.ac.york.eng2.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Serdeable
@Entity(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate dateCreated;

    @Column
    private String address;

    @Column
    private Boolean paid;

    @Column
    private Boolean delivered;

    @Column
    private Float totalAmount;

    @JsonIgnore
    @ManyToOne
    private Customer customer;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateCreated() { return dateCreated; }
    public void setDateCreated(LocalDate dateCreated) { this.dateCreated = dateCreated; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Boolean getPaid() { return paid; }
    public void setPaid(Boolean paid) { this.paid = paid; }

    public Boolean getDelivered() { return delivered; }
    public void setDelivered(Boolean delivered) { this.delivered = delivered; }

    public Float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Float totalAmount) { this.totalAmount = totalAmount; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer)  { this.customer = customer; }
}
