package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IFilmRepository extends JpaRepository<Film,Integer> {
}
