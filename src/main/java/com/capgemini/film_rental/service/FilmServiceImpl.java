package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Category;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.entity.Language;
import com.capgemini.film_rental.entity.enums.Rating;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.mapper.FilmMapper;
import com.capgemini.film_rental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilmServiceImpl implements IFilmService {

    private final IFilmRepository filmRepo;
    private final ICategoryRepository categoryRepo;
    private final ILanguageRepository languageRepo;

    public FilmServiceImpl(IFilmRepository filmRepo, ICategoryRepository categoryRepo, ILanguageRepository languageRepo) {
        this.filmRepo = filmRepo;
        this.categoryRepo = categoryRepo;
        this.languageRepo = languageRepo;
    }

    private Film getFilm(int id) {
        return filmRepo.findById(id).orElseThrow(() -> new NotFoundException("Film not found: " + id));
    }

    @Override
    public String create(FilmCreateDTO dto) {
        Film f = new Film();
        f.setTitle(dto.getTitle());
        f.setDescription(dto.getDescription());
        f.setReleaseYear(dto.getReleaseYear());
        if (dto.getLanguageId() != null) {
            Language l = languageRepo.findById(dto.getLanguageId()).orElseThrow(() -> new NotFoundException("Language not found"));
            f.setLanguage(l);
        }
        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            f.setCategories(categoryRepo.findAllById(dto.getCategoryIds()));
        }
        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            // actors relationship will be handled by existing actor ids; if actors not present it's okay
            // leave actors empty here as not needed for the endpoints beyond read
        }
        if (dto.getRentalRate() != null) f.setRentalRate(dto.getRentalRate());
        if (dto.getLength() != null) f.setLength(dto.getLength());
        filmRepo.save(f);
        return "Record Created Successfully";
    }

    @Override
    public FilmDTO updateCategory(int filmId, int categoryId) {
        Film f = getFilm(filmId);
        Category c = categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
        var set = Optional.ofNullable(f.getCategories()).orElseGet(java.util.ArrayList::new);
        if (!set.contains(c)) {
            set.add(c);
            f.setCategories(set);
        }
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public FilmDTO updateLanguage(int filmId, int languageId) {
        Film f = getFilm(filmId);
        Language l = languageRepo.findById(languageId).orElseThrow(() -> new NotFoundException("Language not found"));
        f.setLanguage(l);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public FilmDTO updateReleaseYear(int filmId, int year) {
        Film f = getFilm(filmId);
        f.setReleaseYear(year);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public FilmDTO updateTitle(int filmId, String title) {
        Film f = getFilm(filmId);
        f.setTitle(title);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public List<FilmDTO> findByCategory(String category) {
        return filmRepo.findByCategoryName(category).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Integer> findActorsOfFilm(int filmId) {
        return getFilm(filmId).getActors().stream().map(a -> a.getActorId()).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRatingLessThan(String rating) {
        Rating r = Rating.valueOf(rating.replace("-", "_"));
        return filmRepo.findByRatingLessThan(r).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByReleaseYearBetween(int from, int to) {
        return filmRepo.findByReleaseYearBetween(from, to).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByLengthGreaterThan(short length) {
        return filmRepo.findByLengthGreaterThan(length).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRentalRateGreaterThan(BigDecimal rate) {
        return filmRepo.findByRentalRateGreaterThan(rate).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }
}