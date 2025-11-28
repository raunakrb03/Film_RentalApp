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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id",columnDefinition = "TINYINT UNSIGNED")
    //@JsonIgnore
    private int storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id",columnDefinition = "TINYINT UNSIGNED")
    private Staff managerStaff;

    @ManyToOne
    @JoinColumn(name = "address_id",columnDefinition = "SMALLINT UNSIGNED")
    //@JsonIgnore
    private Address address;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Staff> staff;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Customer> customers;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Inventory> inventories=new ArrayList<>();

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Staff getManagerStaff() {
		return managerStaff;
	}

	public void setManagerStaff(Staff managerStaff) {
		this.managerStaff = managerStaff;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, customers, inventories, lastUpdate, managerStaff, staff, storeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(address, other.address) && Objects.equals(customers, other.customers)
				&& Objects.equals(inventories, other.inventories) && Objects.equals(lastUpdate, other.lastUpdate)
				&& Objects.equals(managerStaff, other.managerStaff) && Objects.equals(staff, other.staff)
				&& Objects.equals(storeId, other.storeId);
	}

	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", managerStaff=" + managerStaff + ", address=" + address + ", lastUpdate="
				+ lastUpdate + ", staff=" + staff + ", customers=" + customers + ", inventories=" + inventories + "]";
	}

    // Getters, setters, equals, hashCode, toString
}