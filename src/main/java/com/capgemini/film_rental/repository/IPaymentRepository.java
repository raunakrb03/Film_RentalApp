package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPaymentRepository extends JpaRepository<Payment,Integer> {
    @Query("select r.inventory.film.filmId, sum(p.amount) from Payment p join p.rental r where r.staff.store.storeId = :storeId group by r.inventory.film.filmId order by r.inventory.film.filmId")
    List<Object[]> revenueFilmsByStore(@Param("storeId") int storeId);

    @Query("select r.staff.store.storeId, cast(p.paymentDate as date), sum(p.amount) from Payment p join p.rental r group by r.staff.store.storeId, cast(p.paymentDate as date) order by r.staff.store.storeId, cast(p.paymentDate as date)")
    List<Object[]> revenueAllStoresDatewise();

    @Query("select r.inventory.film.filmId, sum(p.amount) from Payment p join p.rental r group by r.inventory.film.filmId order by r.inventory.film.filmId")
    List<Object[]> revenueAllFilmsAcrossStores();

    @Query("select cast(p.paymentDate as date), sum(p.amount) from Payment p join p.rental r where r.staff.store.storeId = :storeId group by cast(p.paymentDate as date) order by cast(p.paymentDate as date)")
    List<Object[]> revenueByDateForStore(@Param("storeId") int storeId);
}
