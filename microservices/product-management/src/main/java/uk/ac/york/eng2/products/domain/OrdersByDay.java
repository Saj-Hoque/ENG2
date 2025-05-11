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

    // Without the Product context, OrdersByDay is incomplete — the count only makes sense attached to which product it’s about
    // Hence no JsonIgnore
    @ManyToOne
    private Product product;

    @Column
    private LocalDate day;

    @Column
    private Integer count;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public LocalDate getDay() { return day; }
    public void setDay(LocalDate day) { this.day = day; }

    public Integer getCount() { return count; }
    public void setCount(Integer count) { this.count = count; }
}
