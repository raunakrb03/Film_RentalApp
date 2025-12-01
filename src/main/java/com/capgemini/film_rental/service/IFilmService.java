package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;

import java.util.List;

public interface IFilmService {
    String create(FilmCreateDTO dto);
    FilmDTO updateCategory(int filmId, int categoryId);

    FilmDTO updateLanguage(int filmId, int languageId);

    FilmDTO updateReleaseYear(int filmId, int year);

    FilmDTO updateTitle(int filmId, String title);

    List<FilmDTO> findByCategory(String category);

    List<Integer> findActorsOfFilm(int filmId);

    List<FilmDTO> findByRatingLessThan(String rating);

    List<FilmDTO> findByReleaseYearBetween(int from, int to);

    List<FilmDTO> findByLengthGreaterThan(short length);

    List<FilmDTO> findByRentalRateGreaterThan(java.math.BigDecimal rate);

    FilmDTO addActorToFilm(int filmId, int actorId);
}
