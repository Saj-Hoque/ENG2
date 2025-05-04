package uk.ac.york.eng2.products.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Serdeable
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Float unitPrice;

    @JsonIgnore
    @ManyToMany
    private Set<Tag> tags = new HashSet<>();


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Float unitPrice) { this.unitPrice = unitPrice; }

    public Set<Tag> getTags() { return tags; }
//    public void setTags(Set<Tag> tags) { this.tags = tags; }
//  Tags are added or removed individually rather than replacing the entire set
}
