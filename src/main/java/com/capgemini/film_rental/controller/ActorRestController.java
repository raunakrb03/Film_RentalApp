package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/actors")
public class ActorRestController {

    @Autowired
    IActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable int id){
       return  ResponseEntity.ok(actorService.getActorById(id));
    }
    @PostMapping
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor){
      return ResponseEntity.ok(actorService.registerActor(actor));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Actor>> getAll(){
        return ResponseEntity.ok(actorService.getAllActors());
    }
}
