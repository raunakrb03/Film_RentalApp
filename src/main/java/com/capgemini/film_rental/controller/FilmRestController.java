package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.service.IFilmService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/films")
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
}