package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInventoryRepository extends JpaRepository<Inventory,Integer> {
    @Query("select i.store.address.addressId, count(i) from Inventory i where i.film.filmId = :filmId and i.store.storeId = :storeId group by i.store.address.addressId")
    List<Object[]> inventoryFilmInStore(@Param("filmId") int filmId, @Param("storeId") int storeId);

    @Query("select i.film.title, count(i) from Inventory i where i.store.storeId = :storeId group by i.film.title order by i.film.title")
    List<Object[]> inventoryByStore(@Param("storeId") int storeId);
}
