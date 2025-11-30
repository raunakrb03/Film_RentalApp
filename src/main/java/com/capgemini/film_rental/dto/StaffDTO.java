
package com.capgemini.film_rental.dto;

public class StaffDTO {
    private int staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer storeId;
    private Integer addressId;
    private String username;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int v) {
        this.staffId = v;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer v) {
        this.storeId = v;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer v) {
        this.addressId = v;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String v) {
        this.username = v;
    }
}
