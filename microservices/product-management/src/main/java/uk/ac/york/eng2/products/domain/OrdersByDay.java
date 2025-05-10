package uk.ac.york.eng2.products.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.time.LocalDate;

@Serdeable
@Entity
public class OrdersByDay {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Product product;

    @Column
    private LocalDate day;

    @Column
    private Integer count;

    public OrdersByDay() {
        // no-arg constructor
    }
    public OrdersByDay(Product product, LocalDate day, Integer count) {
        this.product = product;
        this.day = day;
        this.count = count;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public LocalDate getDay() { return day; }
    public void setDay(LocalDate day) { this.day = day; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
}
