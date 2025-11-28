package com.capgemini.film_rental.entity;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id",columnDefinition = "TINYINT UNSIGNED")
    // @JsonIgnore
    private int staffId;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    @JsonIgnore
    private Address address;

    @Lob
    @Column(name = "picture",columnDefinition = "BLOB",nullable = true)
    private byte[] picture;

    @Column(name = "email", length = 50,nullable = true)
    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    @JsonIgnore
    private Store store;

    @Column(name = "active", nullable = false,columnDefinition = "TINYINT(1) DEFAULT 1")
    private byte active=1;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @Column(name = "password", length = 40,nullable = true)
    private String password;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "staff")
    @JsonIgnore
    @Column(name="payment_id",nullable = false,columnDefinition = "TINYINT UNSIGNED")
    private List<Payment> payment;

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(active, address, email, firstName, lastName, lastUpdate, password, staffId, store,
                username);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Staff other = (Staff) obj;
        return active == other.active && Objects.equals(address, other.address) && Objects.equals(email, other.email)
                && Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
                && Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(password, other.password)
                && Objects.equals(staffId, other.staffId) && Objects.equals(store, other.store)
                && Objects.equals(username, other.username);
    }

    @Override
    public String toString() {
        return "Staff [staffId=" + staffId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
                + address + ", email=" + email + ", store=" + store + ", active=" + active + ", username=" + username
                + ", password=" + password + ", lastUpdate=" + lastUpdate + "]";
    }
    public Staff() {
        // TODO Auto-generated constructor stub
    }

    public Staff(int staffId, String firstName, String lastName, Address address, String email, Store store,
                 byte active, String username, String password, LocalDateTime lastUpdate,List<Payment> payment) {
        super();
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.store = store;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
        this.payment=payment;
    }

    public Staff(int staff_id) {
        // TODO Auto-generated constructor stub
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }



}






















