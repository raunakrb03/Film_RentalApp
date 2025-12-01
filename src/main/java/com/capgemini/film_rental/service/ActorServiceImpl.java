// src/main/java/com/capgemini/film_rental/service/ActorServiceImpl.java
package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.repository.IActorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements IActorService{
    @Autowired
    IActorRepository actorRepository;

    @Override
    public Actor registerActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor getActorById(int id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor does not exist with ID: " + id));
    }

    @Override
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @Override
    public java.util.List<Integer> filmsOfActor(int actorId){
        Actor actor = getActorById(actorId);
        return actor.getFilms().stream()
                .map(Film::getFilmId)
                .collect(Collectors.toList());
    }

    @Override
    public Actor updateFirstName(int actorId, String firstName) {
        Actor actor = getActorById(actorId);
        actor.setFirstName(firstName);
        return actorRepository.save(actor);
    }

    @Override
    public List<ActorWithFilmCountDTO> findTop10ByFilmCount() {
        List<Map<String, Object>> results = actorRepository.findTop10ByFilmCount();
        return results.stream().map(row -> new ActorWithFilmCountDTO(
            ((Number) row.get("actorId")).intValue(),
            (String) row.get("firstName"),
            (String) row.get("lastName"),
            ((Number) row.get("filmCount")).longValue()
        )).collect(Collectors.toList());
    }
}
