package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("select r.inventory.film.filmId, r.inventory.film.title, sum(p.amount) from Payment p join p.rental r where r.staff.store.storeId = :storeId group by r.inventory.film.filmId, r.inventory.film.title order by r.inventory.film.filmId")
    List<Object[]> revenueFilmsByStore(@Param("storeId") int storeId);

    @Query("select r.staff.store.storeId, function('date', p.paymentDate), sum(p.amount) from Payment p join p.rental r group by r.staff.store.storeId, function('date', p.paymentDate) order by r.staff.store.storeId, function('date', p.paymentDate)")
    List<Object[]> revenueAllStoresDatewise();

    @Query("select r.inventory.film.filmId, r.inventory.film.title, sum(p.amount) from Payment p join p.rental r group by r.inventory.film.filmId, r.inventory.film.title order by r.inventory.film.filmId")
    List<Object[]> revenueAllFilmsAcrossStores();
}
