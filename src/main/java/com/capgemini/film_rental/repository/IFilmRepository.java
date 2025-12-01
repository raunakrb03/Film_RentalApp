package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.entity.enums.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface IFilmRepository extends JpaRepository<Film, Integer> {

    @Query("select f from Film f join f.categories c where lower(c.name) = lower(:category)")
    List<Film> findByCategoryName(@Param("category") String category);
    @Query("select f from Film f where f.releaseYear between :from and :to")
    List<Film> findByReleaseYearBetween(@Param("from") Integer from, @Param("to") Integer to);
    List<Film> findByLengthGreaterThan(Short length);
    @Query("select f from Film f where f.rentalRate > :rate")
    List<Film> findByRentalRateGreaterThan(@Param("rate") BigDecimal rate);

    List<Film> findByRatingLessThan(Rating rating);
    List<Film> findByRatingGreaterThan(Rating rating);
}