package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IRentalRepository extends JpaRepository<Rental,Integer>{
    @Query("select r.inventory.film.filmId from Rental r where r.customer.customerId = :customerId")
    List<Integer> filmsRentedToCustomer(@Param("customerId") int customerId);


    @Query("SELECT r FROM Rental r WHERE r.inventory.store.storeId = :storeId AND r.returnDate IS NULL")
    List<Rental> findDueRentalsByStore(@Param("storeId") int storeId);

}

