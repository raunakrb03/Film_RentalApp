
package com.capgemini.film_rental.dto;

public class CustomerDTO {
    private int customerId;
    private Integer storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;
    private Boolean active;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int v) {
        this.customerId = v;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer v) {
        this.storeId = v;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String v) {
        this.firstName = v;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String v) {
        this.lastName = v;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String v) {
        this.email = v;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer v) {
        this.addressId = v;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean v) {
        this.active = v;
    }
}
