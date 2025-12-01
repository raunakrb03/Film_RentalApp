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
}
