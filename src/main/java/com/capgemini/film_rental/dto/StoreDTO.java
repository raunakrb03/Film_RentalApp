package com.capgemini.film_rental.dto;




import java.time.LocalDateTime;

public class StoreDTO {

    private Long storeId;
    private Long managerStaffId;
    private Integer addressId;
    private LocalDateTime lastUpdate;

    public StoreDTO() {}

    public StoreDTO(Long storeId, Long managerStaffId, Integer addressId, LocalDateTime lastUpdate) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;
    }

    // Getters and Setters
    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public Long getManagerStaffId() { return managerStaffId; }
    public void setManagerStaffId(Long managerStaffId) { this.managerStaffId = managerStaffId; }

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
}
