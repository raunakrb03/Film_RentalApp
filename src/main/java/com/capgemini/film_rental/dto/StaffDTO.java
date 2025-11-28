package com.capgemini.film_rental.dto;



import java.time.LocalDateTime;

public class StaffDTO {

    private Long staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private String username;
    private String password; // Optional: usually excluded for security
    private Integer addressId;
    private Integer storeId;
    private LocalDateTime lastUpdate;

    public StaffDTO() {}

    public StaffDTO(Long staffId, String firstName, String lastName, String email,
                    Boolean active, String username, String password,
                    Integer addressId, Integer storeId, LocalDateTime lastUpdate) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.username = username;
        this.password = password;
        this.addressId = addressId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }

    // Getters and Setters
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getAddressId() { return addressId; }
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

    public Integer getStoreId() { return storeId; }
    public void setStoreId(Integer storeId) { this.storeId = storeId; }

    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
}
