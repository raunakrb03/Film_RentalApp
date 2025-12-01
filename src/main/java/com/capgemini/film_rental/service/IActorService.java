package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.entity.Actor;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IActorService {
    public Actor registerActor(Actor actor);
    public Actor getActorById(int id);
    public List<Actor> getAllActors();
    List<Integer> filmsOfActor(int actorId);
    Actor updateFirstName(int actorId, String firstName);
    List<ActorWithFilmCountDTO> findTop10ByFilmCount();
   // public Actor updateActorByFirstName(Actor actor);
   List<Actor> findByFirstName(String firstName);
}
