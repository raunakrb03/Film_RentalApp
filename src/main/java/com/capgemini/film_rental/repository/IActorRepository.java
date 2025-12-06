package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface IActorRepository extends JpaRepository<Actor,Integer> {

    @Query("SELECT a.actorId as actorId, a.firstName as firstName, a.lastName as lastName, COUNT(f) as filmCount " +
           "FROM Actor a LEFT JOIN a.films f " +
           "GROUP BY a.actorId, a.firstName, a.lastName " +
           "ORDER BY filmCount DESC LIMIT 10")
    List<Map<String, Object>> findTop10ByFilmCount();

    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Actor> findByLastNameContainingIgnoreCase(String lastName);

    // Projection-based paged query to return only the fields needed by the UI and avoid loading entities
    @Query("SELECT new com.capgemini.film_rental.dto.ActorDTO(a.actorId, a.firstName, a.lastName) FROM Actor a")
    org.springframework.data.domain.Page<com.capgemini.film_rental.dto.ActorDTO> findAllProjected(org.springframework.data.domain.Pageable pageable);
}
