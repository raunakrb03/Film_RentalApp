package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.mapper.ActorMapper;
import com.capgemini.film_rental.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/actors")
public class ActorRestController {

    @Autowired
    IActorService actorService;

    @GetMapping("/{id}")
    public ResponseEntity<ActorDTO> getActorById(@PathVariable int id){
       return  ResponseEntity.ok(ActorMapper.toDTO(actorService.getActorById(id)));
    }
    @PostMapping
    public ResponseEntity<ActorDTO> createActor(@Valid @RequestBody ActorCreateDTO actor){
      var saved = actorService.registerActor(ActorMapper.toEntity(actor));
      return ResponseEntity.ok(ActorMapper.toDTO(saved));
    }
    @GetMapping("/all")
    public ResponseEntity<List<ActorDTO>> getAll(){
        return ResponseEntity.ok(actorService.getAllActors().stream().map(ActorMapper::toDTO).toList());
    }
}
