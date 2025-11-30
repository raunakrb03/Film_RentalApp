
package com.capgemini.film_rental.dto;

public class RentalCreateDTO {
    private Integer inventoryId;
    private Integer customerId;
    private Integer staffId;

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
