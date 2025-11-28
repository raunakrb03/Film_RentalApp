package com.capgemini.film_rental.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;


    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;



    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    public Film getFilm() { return film; }





    public Inventory() {}

    public Inventory(Film film, Store store, LocalDateTime lastUpdate) {
        this.film = film;
        this.store = store;
        this.lastUpdate = lastUpdate;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
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

//    @Override
//    public String toString() {
//        return "Inventory [inventoryId=" + inventoryId + ", film=" + film.getTitle() +
//               ", store=" + store.getStoreId() + ", lastUpdate=" + lastUpdate + "]";
//    }
}