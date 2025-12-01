package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;

import java.util.List;
import java.util.Map;

public interface IFilmService {
    String create(FilmCreateDTO dto);
    FilmDTO updateCategory(int filmId, int categoryId);

    FilmDTO updateLanguage(int filmId, int languageId);

    FilmDTO updateReleaseYear(int filmId, int year);

    FilmDTO updateTitle(int filmId, String title);

    FilmDTO updateRating(int filmId, String rating);

    FilmDTO updateRentalDuration(int filmId, int rentalDuration);

    FilmDTO addActor(int filmId, int actorId);

    List<FilmDTO> findByCategory(String category);

    List<Integer> findActorsOfFilm(int filmId);

    List<FilmDTO> findByRatingLessThan(String rating);

    List<FilmDTO> findByRatingGreaterThan(String rating);

    List<FilmDTO> findByReleaseYearBetween(int from, int to);

    List<FilmDTO> findByLengthGreaterThan(short length);

    List<FilmDTO> findByRentalRateGreaterThan(java.math.BigDecimal rate);

    List<FilmDTO> findByRentalRateLessThan(java.math.BigDecimal rate);

    List<FilmDTO> findByRentalDurationLessThan(int rentalDuration);

    List<FilmDTO> findByTitle(String title);

    FilmDTO addActorToFilm(int filmId, int actorId);

    Map<Integer, Long> countFilmsByYear();

    List<FilmDTO> findByLengthLessThan(short length);

    List<FilmDTO> findByRentalDurationGreaterThan(byte rd);

    FilmDTO updateRentalRate(int filmId, java.math.BigDecimal rentalRate);

    List<FilmDTO> findByLanguage(String languageName);

    List<FilmDTO> findByReleaseYear(Integer year);

}
