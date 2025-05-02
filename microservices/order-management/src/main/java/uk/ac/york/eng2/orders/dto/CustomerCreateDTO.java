package uk.ac.york.eng2.orders.dto;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class CustomerCreateDTO {
    private String email;
    private String firstName;
    private String familyName;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
}
