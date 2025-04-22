package uk.ac.york.eng2.products.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class ProductCreateDTO {
    private String name;
    private Float unitPrice;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(Float unitPrice) { this.unitPrice = unitPrice; }

}
