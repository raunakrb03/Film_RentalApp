package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.mapper.ActorMapper;
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

    // ...existing code...
    @PutMapping("/update/firstname/{id}")
    public ResponseEntity<ActorDTO> updateFirstName(@PathVariable int id, @RequestParam String firstName) {
        var updated = actorService.updateFirstName(id, firstName);
        return ResponseEntity.ok(ActorMapper.toDTO(updated));
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

    /**
     * PUT /api/actors/{id}/film
     * Assign films to an actor
     *
     * @param id the actor ID
     * @param filmIds list of film IDs to assign
     * @return collection of assigned films as FilmDTO objects
     */
    @PutMapping("/{id}/film")
    public ResponseEntity<List<FilmDTO>> assignFilmsToActor(@PathVariable int id, @RequestBody List<Integer> filmIds) {
        List<FilmDTO> films = actorService.assignFilmsToActor(id, filmIds);
        return ResponseEntity.ok(films);
    }
}



