package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IActorRepository extends JpaRepository<Actor,Integer> {

    // Use native SQL to compute top 10 actors by film count (JPQL doesn't support LIMIT)
    @Query(value = "SELECT a.actor_id, a.first_name, a.last_name, COUNT(af.film_id) AS filmCount \n" +
            "FROM actor a LEFT JOIN film_actor af ON a.actor_id = af.actor_id \n" +
            "GROUP BY a.actor_id, a.first_name, a.last_name \n" +
            "ORDER BY filmCount DESC \n" +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findTop10ByFilmCountNative();

    List<Actor> findByFirstNameContainingIgnoreCase(String firstName);

    List<Actor> findByLastNameContainingIgnoreCase(String lastName);

    // Projection-based paged query to return only the fields needed by the UI and avoid loading entities
    @Query("SELECT new com.capgemini.film_rental.dto.ActorDTO(a.actorId, a.firstName, a.lastName) FROM Actor a")
    org.springframework.data.domain.Page<com.capgemini.film_rental.dto.ActorDTO> findAllProjected(org.springframework.data.domain.Pageable pageable);
}
