package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInventoryRepository extends JpaRepository<Inventory,Integer> {
}
