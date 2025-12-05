package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.enums.Rating;
import com.capgemini.film_rental.service.IFilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")

@RequestMapping("/api/films")

@SuppressWarnings("unused")
public class FilmRestController {
    private final IFilmService service;

    public FilmRestController(IFilmService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public String create(@RequestBody FilmCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/update/category/{id}")
    public FilmDTO updateCategory(@PathVariable int id, @RequestParam int categoryId) {
        return service.updateCategory(id, categoryId);
    }

    @PutMapping("/update/language/{id}")
    public FilmDTO updateLanguage(@PathVariable int id, @RequestParam int languageId) {
        return service.updateLanguage(id, languageId);
    }

    @PutMapping("/update/releaseyear/{id}")
    public FilmDTO updateReleaseYear(@PathVariable int id, @RequestParam int year) {
        return service.updateReleaseYear(id, year);
    }

    @PutMapping("/update/title/{id}")
    public FilmDTO updateTitle(@PathVariable int id, @RequestParam String title) {
        return service.updateTitle(id, title);
    }

    @PutMapping("/update/rating/{id}")
    public ResponseEntity<?> updateRating(@PathVariable int id, @RequestParam String rating) {
        try {
            // validate rating string maps to enum
            Rating.valueOf(rating.replace("-", "_"));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid rating value. Allowed: G, PG, PG-13, R, NC-17");
        }
        FilmDTO updated = service.updateRating(id, rating);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/update/rentaldurtion/{id}")
    public FilmDTO updateRentalDuration(@PathVariable int id, @RequestParam int rentalDuration) {
        return service.updateRentalDuration(id, rentalDuration);
    }

    @PutMapping("/{id}/actor/{actorId}")
    public ResponseEntity<FilmDTO> addActor(@PathVariable int id, @PathVariable int actorId) {
        FilmDTO updated = service.addActor(id, actorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }
    @GetMapping("/duration/gt/{rd}")
    public ResponseEntity<List<FilmDTO>> getFilmsWithRentalDurationGreaterThan(@PathVariable byte rd) {
        return ResponseEntity.ok(service.findByRentalDurationGreaterThan(rd));
    }
    @GetMapping("/length/lt/{length}")
    public ResponseEntity<List<FilmDTO>> getFilmsWithLengthLessThan(@PathVariable short length) {
        return ResponseEntity.ok(service.findByLengthLessThan(length));
    }


    @GetMapping("/countbyyear")
    public Map<Integer, Long> countFilmsByYear() {
        return service.countFilmsByYear();
    }






    @GetMapping("/category/{category}")
    public List<FilmDTO> byCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @GetMapping("/{id}/actors")
    public List<Integer> actorsOfFilm(@PathVariable int id) {
        return service.findActorsOfFilm(id);
    }


    @GetMapping("/rating/lt/{rating}")
    public List<FilmDTO> ratingLT(@PathVariable String rating) {
        return service.findByRatingLessThan(rating);
    }

    @GetMapping("/rating/gt/{rating}")
    public List<FilmDTO> ratingGT(@PathVariable String rating) {
        return service.findByRatingGreaterThan(rating);
    }

    @GetMapping("/betweenyear/{from}/{to}")
    public List<FilmDTO> betweenYears(@PathVariable int from, @PathVariable int to) {
        return service.findByReleaseYearBetween(from, to);
    }

    @GetMapping("/length/gt/{length}")
    public List<FilmDTO> lengthGT(@PathVariable short length) {
        return service.findByLengthGreaterThan(length);
    }

    @GetMapping("/rate/gt/{rate}")
    public List<FilmDTO> rateGT(@PathVariable BigDecimal rate) {
        return service.findByRentalRateGreaterThan(rate);
    }

    @GetMapping("/duration/lt/{rd}")
    public List<FilmDTO> durationLT(@PathVariable int rd) {
        return service.findByRentalDurationLessThan(rd);
    }

    @GetMapping("/title/{title}")
    public List<FilmDTO> findByTitle(@PathVariable String title) {
        return service.findByTitle(title);
    }

    @GetMapping("/language/{lang}")
    public List<FilmDTO> byLanguage(@PathVariable String lang){
        return service.findByLanguage(lang);
    }

    @GetMapping("/rate/lt/{rate}")
    public List<FilmDTO> rateLT(@PathVariable BigDecimal rate){
        return service.findByRentalRateLessThan(rate);
    }

    @GetMapping("/year/{year}")
    public List<FilmDTO> findByYear(@PathVariable int year) {
        return service.findByYear(year);
    }

    @PutMapping("/update/rentalrate/{id}")
    public FilmDTO updateRentalRate(@PathVariable int id, @RequestParam BigDecimal rate){
        return service.updateRentalRate(id, rate);
    }

    @GetMapping("")
    public ResponseEntity<List<FilmDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }


}