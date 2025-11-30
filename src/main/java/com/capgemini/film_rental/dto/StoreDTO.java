
package com.capgemini.film_rental.dto;

public class StoreDTO {
    private int storeId;
    private Integer managerStaffId;
    private Integer addressId;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int v) {
        this.storeId = v;
    }

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

    public static class ManagerDetails {
        public String firstName;
        public String lastName;
        public String email;
        public String phone;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }
    }

    public static class ManagerAndStoreView {
        public String managerFirstName;
        public String managerLastName;
        public String managerEmail;
        public String managerPhone;
        public String storeAddress;
        public String storeCity;
        public String storePhone;
    }
}
