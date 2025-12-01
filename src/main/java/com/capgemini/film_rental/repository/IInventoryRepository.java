package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInventoryRepository extends JpaRepository<Inventory,Integer> {
    @Query("select i.store.address.addressId, count(i) from Inventory i where i.film.filmId = :filmId and i.store.storeId = :storeId group by i.store.address.addressId")
    List<Object[]> inventoryFilmInStore(@Param("filmId") int filmId, @Param("storeId") int storeId);

    @Query("select i.film.filmId as filmId, i.film.title as filmTitle, i.store.storeId as storeId, count(i) as inventoryCount " +
           "from Inventory i " +
           "group by i.film.filmId, i.film.title, i.store.storeId " +
           "order by i.store.storeId, i.film.filmId")
    List<Object[]> getAllFilmsInventoryCounts();

    @Query("SELECT i.store.storeId, COUNT(i) FROM Inventory i WHERE i.film.filmId = :filmId GROUP BY i.store.storeId")
    List<Object[]> inventoryFilmAcrossStores(@Param("filmId") int filmId);

}
