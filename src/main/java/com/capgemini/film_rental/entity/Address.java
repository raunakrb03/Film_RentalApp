package com.capgemini.film_rental.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id",columnDefinition = "SMALLINT UNSIGNED")
    @JsonIgnore
    private int addressId;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "address2", length = 50)
    private String address2;

    @Column(name = "district", nullable = false, length = 20)
    private String district;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    @JsonIgnore
    private City city;

    @Column(name = "postal_code", length = 10)
    private String postalCode;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

//    @Column(name = "country")
//    private String country;


//    @Column(name = "location", columnDefinition = "GEOMETRY NOT NULL")
//    @JdbcTypeCode(SqlTypes.GEOMETRY)
//    private Geometry location;  // Correct type for spatial data

//    public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Customer> customers=new ArrayList<>();

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Staff> staff=new ArrayList<>();

    @OneToMany(mappedBy = "address")
    @JsonIgnore
    private List<Store> stores=new ArrayList<>();

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, address2, addressId, city, customers, district, lastUpdate, phone, postalCode,
                staff, stores);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        return Objects.equals(address, other.address) && Objects.equals(address2, other.address2)
                && Objects.equals(addressId, other.addressId) && Objects.equals(city, other.city)
                && Objects.equals(customers, other.customers) && Objects.equals(district, other.district)
                && Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(phone, other.phone)
                && Objects.equals(postalCode, other.postalCode) && Objects.equals(staff, other.staff)
                && Objects.equals(stores, other.stores);
    }

    @Override
    public String toString() {
        return "Address [addressId=" + addressId + ", address=" + address + ", address2=" + address2 + ", district="
                + district + ", city=" + city + ", postalCode=" + postalCode + ", phone=" + phone + ", lastUpdate="
                + lastUpdate + ", customers=" + customers + ", staff=" + staff + ", stores=" + stores + "]";
    }

    // Getters, setters, equals, hashCode, toString


}
