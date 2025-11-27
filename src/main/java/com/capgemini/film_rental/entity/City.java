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
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id",columnDefinition = "SMALLINT UNSIGNED")
    // @JsonIgnore
    private int cityId;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "country_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    private Country country;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Address> addresses=new ArrayList<>();

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }



    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses, city, cityId, country, lastUpdate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        City other = (City) obj;
        return Objects.equals(addresses, other.addresses) && Objects.equals(city, other.city)
                && Objects.equals(cityId, other.cityId) && Objects.equals(country, other.country)
                && Objects.equals(lastUpdate, other.lastUpdate);
    }

    @Override
    public String toString() {
        return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + ", lastUpdate=" + lastUpdate
                + "]";
    }

    // Getters, setters, equals, hashCode, toString


}
