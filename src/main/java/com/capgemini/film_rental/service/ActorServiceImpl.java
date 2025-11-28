package com.capgemini.film_rental.service;

import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.repository.IActorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
