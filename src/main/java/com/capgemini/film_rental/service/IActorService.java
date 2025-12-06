package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Actor;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IActorService {
    Actor registerActor(Actor actor);
    Actor getActorById(int id);
    List<Actor> getAllActors();
    // New: paged actor DTO response to support frontend pagination
    Page<com.capgemini.film_rental.dto.ActorDTO> findAll(org.springframework.data.domain.Pageable pageable);
    List<Integer> filmsOfActor(int actorId);
    List<FilmDTO> getFilmsOfActor(int actorId);
    Actor updateFirstName(int actorId, String firstName);
    Actor updateLastName(int actorId, String lastName);
    List<ActorWithFilmCountDTO> findTop10ByFilmCount();
    List<Actor> findByFirstName(String firstName);
    List<FilmDTO> assignFilmsToActor(int actorId, List<Integer> filmIds);
    String createActor(ActorCreateDTO dto);
    List<Actor> findByLastName(String lastName);
}
