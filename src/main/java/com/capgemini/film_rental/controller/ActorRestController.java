package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.mapper.ActorMapper;
import com.capgemini.film_rental.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Collections;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.capgemini.film_rental.dto.PageResponse;

@RestController
@CrossOrigin("*")

@RequestMapping("api/actors")
public class ActorRestController {

    @Autowired
    IActorService actorService;

    /**
     * POST /api/actors/post
     * Add new actor object in DB
     *
     * @param dto the actor creation DTO with firstName and lastName
     * @return success message "Record Created Successfully"
     */
    @PostMapping("/post")
    public String createActor(@Valid @RequestBody ActorCreateDTO dto) {
        return actorService.createActor(dto);
    }

    /**
     * GET /api/actors
     * Return all actors as ActorDTO for frontend consumption
     * Supports optional paging: ?page=&size=
     */
    @GetMapping("")
    public ResponseEntity<?> getAllActors(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            var actors = actorService.getAllActors();
            var dtos = actors.stream().map(ActorMapper::toDTO).toList();
            return ResponseEntity.ok(dtos);
        }
        int p = Math.max(0, page);
        int s = Math.max(1, size);
        Pageable pageable = PageRequest.of(p, s);
        Page<com.capgemini.film_rental.dto.ActorDTO> pageResult = actorService.findAll(pageable);
        var items = pageResult.getContent();
        long total = pageResult.getTotalElements();
        int totalPages = pageResult.getTotalPages();
        PageResponse<com.capgemini.film_rental.dto.ActorDTO> resp = new PageResponse<>(items, p, s, total, totalPages);
        return ResponseEntity.ok(resp);
    }

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
     * GET /api/actors/lastname/{ln}
     * Search actors by last name
     *
     * @param ln the last name to search for
     * @return collection of actors with matching last name as ActorDTO
     */
    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<ActorDTO>> getActorsByLastName(@PathVariable String ln) {
        var actors = actorService.findByLastName(ln);
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

    /**
     * PUT /api/actors/{id}/film/{filmId}
     * Assign a single film to an actor by film id
     *
     * @param id the actor ID
     * @param filmId the film ID to assign
     * @return the assigned film as FilmDTO or 404 if not assigned
     */
    @PutMapping("/{id}/film/{filmId}")
    public ResponseEntity<FilmDTO> assignFilmToActor(@PathVariable int id, @PathVariable int filmId) {
        List<FilmDTO> films = actorService.assignFilmsToActor(id, Collections.singletonList(filmId));
        if (films == null || films.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(films.get(0));
    }
    @PutMapping("/update/lastname/{id}")
    public ResponseEntity<ActorDTO> updateLastName(@PathVariable int id, @RequestParam String lastName) {
        var updated = actorService.updateLastName(id, lastName);
        return ResponseEntity.ok(ActorMapper.toDTO(updated));
    }
}
