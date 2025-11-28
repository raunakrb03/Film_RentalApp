package com.capgemini.film_rental.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private int storeId;

    @OneToOne
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "store")
    private Set<Staff> staff = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Inventory> inventories = new HashSet<>();

    @PrePersist
    @PreUpdate
    public void touch() {
        this.lastUpdate = LocalDateTime.now();
    }

}