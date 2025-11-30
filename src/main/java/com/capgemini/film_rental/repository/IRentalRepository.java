package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRentalRepository extends JpaRepository<Rental,Integer>{
}
