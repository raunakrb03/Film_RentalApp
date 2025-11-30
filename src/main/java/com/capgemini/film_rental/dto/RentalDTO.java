
package com.capgemini.film_rental.dto;

import java.time.LocalDateTime;

public class RentalDTO {
    private int rentalId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Integer inventoryId;
    private Integer customerId;
    private Integer staffId;

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int v) {
        this.rentalId = v;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime v) {
        this.rentalDate = v;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime v) {
        this.returnDate = v;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer v) {
        this.inventoryId = v;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer v) {
        this.customerId = v;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer v) {
        this.staffId = v;
    }
}
