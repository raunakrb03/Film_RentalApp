// src/main/java/com/capgemini/film_rental/service/ActorServiceImpl.java
package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.mapper.FilmMapper;
import com.capgemini.film_rental.repository.IActorRepository;
import com.capgemini.film_rental.repository.IFilmRepository;
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

    @Autowired
    IFilmRepository filmRepository;

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

    @Override
    public List<Actor> findByFirstName(String firstName) {
        return actorRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Override
    public List<Actor> findByLastName(String lastName) {
        return actorRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    @Override
    public List<FilmDTO> assignFilmsToActor(int actorId, List<Integer> filmIds) {
        // Get the actor
        Actor actor = getActorById(actorId);

        // Get all films by IDs
        List<Film> films = filmRepository.findAllById(filmIds);

        // Validate all films exist
        if (films.size() != filmIds.size()) {
            throw new EntityNotFoundException("One or more films do not exist");
        }

        // Assign films to actor
        for (Film film : films) {
            if (!actor.getFilms().contains(film)) {
                actor.getFilms().add(film);
            }
        }

        // Save the actor with assigned films
        actorRepository.save(actor);

        // Return the films as DTOs
        return films.stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public String createActor(ActorCreateDTO dto) {
        Actor actor = new Actor();
        actor.setFirstName(dto.getFirstName());
        actor.setLastName(dto.getLastName());
        actorRepository.save(actor);
        return "Record Created Successfully";
    }
}
