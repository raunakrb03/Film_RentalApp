package com.capgemini.film_rental.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id",columnDefinition = "MEDIUMINT UNSIGNED")
   // @JsonIgnore
    private Integer inventoryId;

    //film
    
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false,columnDefinition = "SMALLINT UNSIGNED")
    @JsonIgnore
    private Film film;
    
    //store
    
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false,columnDefinition = "TINYINT UNSIGNED")
    @JsonIgnore
    private Store store;

    @Column(name = "last_update", nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdate;

	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(film, inventoryId, lastUpdate, store);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(film, other.film) && Objects.equals(inventoryId, other.inventoryId)
				&& Objects.equals(lastUpdate, other.lastUpdate) && Objects.equals(store, other.store);
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", film=" + film + ", store=" + store + ", lastUpdate="
				+ lastUpdate + "]";
	}

    // Getters, setters, equals, hashCode, toString
	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	public Inventory(Integer inventoryId) {
		// TODO Auto-generated constructor stub
	}
	
    
    
}

