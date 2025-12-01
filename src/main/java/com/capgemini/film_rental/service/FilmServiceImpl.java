package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Category;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.entity.Language;
import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.entity.enums.Rating;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.mapper.FilmMapper;
import com.capgemini.film_rental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class FilmServiceImpl implements IFilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final IFilmRepository filmRepo;
    private final ICategoryRepository categoryRepo;
    private final ILanguageRepository languageRepo;
    private final IActorRepository actorRepo;

    public FilmServiceImpl(IFilmRepository filmRepo, ICategoryRepository categoryRepo, ILanguageRepository languageRepo, IActorRepository actorRepo) {
        this.filmRepo = filmRepo;
        this.categoryRepo = categoryRepo;
        this.languageRepo = languageRepo;
        this.actorRepo = actorRepo;
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
            // Fetch actors for provided IDs and attach; missing IDs are ignored by findAllById
            List<Actor> actors = actorRepo.findAllById(dto.getActorIds());
            f.setActors(actors);
        }
        if (dto.getRentalRate() != null) f.setRentalRate(dto.getRentalRate());
        if (dto.getLength() != null) f.setLength(dto.getLength());
        // Use saveAndFlush so the insert is executed immediately (helps debugging and visibility in DB)
        Film saved = filmRepo.saveAndFlush(f);
        // log id and return it so caller can verify where the record was persisted
        logger.info("Saved Film with id={} title={}", saved.getFilmId(), saved.getTitle());
        return "Record Created Successfully id=" + saved.getFilmId();
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
//praphul's
    @Override
    public FilmDTO updateRating(int filmId, String rating) {
        Film f = getFilm(filmId);
        Rating r = Rating.valueOf(rating.replace("-", "_"));
        f.setRating(r);
        return FilmMapper.toDTO(filmRepo.save(f));
    }
    @Override
    public FilmDTO addActor(int filmId, int actorId) {
        Film f = getFilm(filmId);
        Actor a = actorRepo.findById(actorId).orElseThrow(() -> new NotFoundException("Actor not found"));
        var list = Optional.ofNullable(f.getActors()).orElseGet(java.util.ArrayList::new);
        if (!list.contains(a)) {
            list.add(a);
            f.setActors(list);
        }
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public FilmDTO addActorToFilm(int filmId, int actorId) {
        // alias for addActor, keep behavior consistent
        return addActor(filmId, actorId);
    }

    @Override
    public Map<Integer, Long> countFilmsByYear() {
        return filmRepo.findAll().stream()
                .filter(f -> f.getReleaseYear() != null)
                .collect(Collectors.groupingBy(Film::getReleaseYear, Collectors.counting()));
    }


    @Override
    public List<FilmDTO> findByLengthLessThan(short length) {
        return filmRepo.findByLengthLessThan(length)
                .stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRentalDurationGreaterThan(byte rd) {
        return filmRepo.findByRentalDurationGreaterThan(rd)
                .stream()
                .map(FilmMapper::toDTO)
                .collect(Collectors.toList());
    }

    //end
    @Override
    public FilmDTO updateRentalDuration(int filmId, int rentalDuration) {
        Film f = getFilm(filmId);
        f.setRentalDuration(rentalDuration);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public List<FilmDTO> findByCategory(String category) {
        return filmRepo.findByCategoryName(category).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Integer> findActorsOfFilm(int filmId) {
        return getFilm(filmId).getActors().stream().map(Actor::getActorId).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRatingLessThan(String rating) {
        Rating r = Rating.valueOf(rating.replace("-", "_"));
        return filmRepo.findByRatingLessThan(r).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRatingGreaterThan(String rating) {
        Rating r = Rating.valueOf(rating.replace("-", "_"));
        return filmRepo.findByRatingGreaterThan(r).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
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

    @Override
    public List<FilmDTO> findByRentalRateLessThan(BigDecimal rate) {
        return filmRepo.findByRentalRateLessThan(rate).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByRentalDurationLessThan(int rentalDuration) {
        return filmRepo.findByRentalDurationLessThan(rentalDuration).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByTitle(String title) {
        return filmRepo.findByTitle(title).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public FilmDTO updateRentalRate(int filmId, BigDecimal rentalRate) {
        Film f = getFilm(filmId);
        f.setRentalRate(rentalRate);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public List<FilmDTO> findByLanguage(String languageName) {
        return filmRepo.findByLanguageName(languageName).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByReleaseYear(Integer year) {
        return filmRepo.findByReleaseYear(year).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

}