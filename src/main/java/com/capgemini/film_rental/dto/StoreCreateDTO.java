
package com.capgemini.film_rental.dto;

public class StoreCreateDTO {
    private Integer managerStaffId;
    private Integer addressId;

    public Integer getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(Integer v) {
        this.managerStaffId = v;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer v) {
        this.addressId = v;
    }
}
