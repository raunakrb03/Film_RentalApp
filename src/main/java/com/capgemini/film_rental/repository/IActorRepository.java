package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor,Integer> {
}
