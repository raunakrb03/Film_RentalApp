package com.capgemini.film_rental.dto.auth;

public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer storeId;
    private Integer addressId;

    public String getFirstName() { return firstName; }
    public void setFirstName(String v) { firstName = v; }
    public String getLastName() { return lastName; }
    public void setLastName(String v) { lastName = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { email = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { password = v; }
    public Integer getStoreId() { return storeId; }
    public void setStoreId(Integer v) { storeId = v; }
    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer v) { addressId = v; }
}
