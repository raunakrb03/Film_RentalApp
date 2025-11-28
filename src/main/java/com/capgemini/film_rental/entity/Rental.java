package com.capgemini.film_rental.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental",indexes = {
        @Index(name = "idx_rental_date", columnList = "rental_date")
})
public class Rental
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id",columnDefinition = "INT")
    // @JsonIgnore
    private int rentalId;

    @Column(name = "rental_date", nullable = false,columnDefinition = "DATETIME")
    private LocalDateTime rentalDate;



    //Multiple Rental -> 1 inventory
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "inventory_id", nullable = false,columnDefinition = "MEDIUMINT UNSIGNED")
    private Inventory inventory;



    //Multiple Rental -> 1 customer
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    private Customer customer;

    @Column(name = "return_date",columnDefinition = "DATETIME")
    private LocalDateTime returnDate;



    //Multiple Rental -> 1 staff
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "staff_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    private Staff staff;


    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, inventory, lastUpdate, rentalDate, rentalId, returnDate, staff);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rental other = (Rental) obj;
        return Objects.equals(customer, other.customer) && Objects.equals(inventory, other.inventory)
                && Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(rentalDate, other.rentalDate)
                && rentalId == other.rentalId && Objects.equals(returnDate, other.returnDate)
                && Objects.equals(staff, other.staff);
    }

    @Override
    public String toString() {
        return "Rental [rentalId=" + rentalId + ", rentalDate=" + rentalDate + ", inventory=" + inventory
                + ", customer=" + customer + ", returnDate=" + returnDate + ", staff=" + staff + ", lastUpdate="
                + lastUpdate + "]";
    }

    public Rental(int rentalId, LocalDateTime rentalDate, Inventory inventory, Customer customer,
                  LocalDateTime returnDate, Staff staff, LocalDateTime lastUpdate) {
        super();
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventory = inventory;
        this.customer = customer;
        this.returnDate = returnDate;
        this.staff = staff;
        this.lastUpdate = lastUpdate;
    }

    public Rental() {
        // TODO Auto-generated constructor stub
    }

}