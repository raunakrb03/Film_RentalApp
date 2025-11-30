package com.capgemini.film_rental.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id",columnDefinition = "SMALLINT UNSIGNED")

    private int customerId;


    //store
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    @JsonIgnore
    private Store store;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 60, nullable = true)
    private String password;


    //address
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    private Address address;



    @Column(name = "active", nullable = false,columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active=true;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "last_update",nullable = true,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;




    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, address, createDate, customerId, email, firstName, lastName, lastUpdate, store);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        return Objects.equals(active, other.active) && Objects.equals(address, other.address)
                && Objects.equals(createDate, other.createDate) && Objects.equals(customerId, other.customerId)
                && Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
                && Objects.equals(lastName, other.lastName) && Objects.equals(lastUpdate, other.lastUpdate)
                && Objects.equals(store, other.store);
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", store=" + store + ", firstName=" + firstName + ", lastName="
                + lastName + ", email=" + email + ", address=" + address + ", active=" + active + ", createDate="
                + createDate + ", lastUpdate=" + lastUpdate + "]";
    }

    public Customer(Integer customerId, Store store, String firstName, String lastName, String email, Address address,
                    Boolean active, LocalDateTime createDate, LocalDateTime lastUpdate) {
        super();
        this.customerId = customerId;
        this.store = store;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
    }
    public Customer() {

    }

    public Customer(Integer customerId) {
        this.customerId=customerId;
    }


    public String getPassword() {
        return this.password;
    }

    // Add setter for password so registration can persist hashed password
    public void setPassword(String password) {
        this.password = password;
    }

}
