package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Actor;

import java.util.List;

public interface IActorService {
    Actor registerActor(Actor actor);
    Actor getActorById(int id);
    List<Actor> getAllActors();
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
