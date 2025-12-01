package com.capgemini.film_rental.mapper;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Film;
import java.util.stream.Collectors;

public class FilmMapper {

    public static FilmDTO toDTO(Film film) {
        FilmDTO dto = new FilmDTO();

        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());

        dto.setLanguageId(film.getLanguage() != null ? film.getLanguage().getLanguageId() : null);
        dto.setOriginalLanguageId(film.getOriginalLanguage() != null ? film.getOriginalLanguage().getLanguageId() : null);

        dto.setRentalDuration(film.getRentalDuration());
        dto.setRentalRate(film.getRentalRate());
        dto.setLength(film.getLength());
        dto.setReplacementCost(film.getReplacementCost());

        dto.setRating(film.getRating() != null ? film.getRating().name().replace("_", "-") : null);
        dto.setSpecialFeatures(film.getSpecialFeatures());
        dto.setLastUpdate(film.getLastUpdate());

        dto.setCategoryIds(film.getCategories() == null ? null :
                film.getCategories().stream()
                        .map(c -> c.getCategoryId())
                        .collect(Collectors.toList()));

        dto.setActorIds(film.getActors() == null ? null :
                film.getActors().stream()
                        .map(a -> a.getActorId())
                        .collect(Collectors.toList()));

        return dto;
    }
}
