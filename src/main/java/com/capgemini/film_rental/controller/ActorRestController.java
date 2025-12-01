package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.mapper.ActorMapper;
import com.capgemini.film_rental.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/post")
    public ResponseEntity<Map<String, String>> addNewActor(@Valid @RequestBody ActorCreateDTO actor) {
        try {
            var saved = actorService.registerActor(ActorMapper.toEntity(actor));
            Map<String, String> response = new HashMap<>();
            response.put("message", "Record Created Successfully");
            response.put("actorId", String.valueOf(saved.getActorId()));
            response.put("firstName", saved.getFirstName());
            response.put("lastName", saved.getLastName());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to create actor: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ActorDTO>> getAll(){
        return ResponseEntity.ok(actorService.getAllActors().stream().map(ActorMapper::toDTO).toList());
    }

    // Added endpoint to return list of film IDs for a given actor
    @GetMapping("/{id}/films")
    public ResponseEntity<List<Integer>> getFilmsOfActor(@PathVariable("id") int actorId) {
        return ResponseEntity.ok(actorService.filmsOfActor(actorId));
    }

    // ...existing code...
    @PutMapping("/update/firstname/{id}")
    public ResponseEntity<ActorDTO> updateFirstName(@PathVariable int id, @RequestParam String firstName) {
        var updated = actorService.updateFirstName(id, firstName);
        return ResponseEntity.ok(ActorMapper.toDTO(updated));
    }

    @PutMapping("/{id}/film")
    public ResponseEntity<List<FilmDTO>> assignFilmToActor(@PathVariable int id, @RequestParam int filmId) {
        actorService.assignFilmToActor(id, filmId);
        List<FilmDTO> films = actorService.getActorFilmsAsDTO(id);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<ActorDTO>> getActorsByLastName(@PathVariable String ln) {
        return ResponseEntity.ok(actorService.getActorsByLastName(ln));
    }
    @GetMapping("/toptenbyfilmcount")
    public ResponseEntity<List<ActorWithFilmCountDTO>> getTop10ByFilmCount() {
        return ResponseEntity.ok(actorService.findTop10ByFilmCount());
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<ActorDTO>> getActorsByFirstName(@PathVariable String fn) {
        var actors = actorService.findByFirstName(fn);
        return ResponseEntity.ok(actors.stream()
                .map(ActorMapper::toDTO)
                .toList());
    }

}
