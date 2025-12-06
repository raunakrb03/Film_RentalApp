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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

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
    @CacheEvict(value = "films", allEntries = true)
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
    @CacheEvict(value = "films", allEntries = true)
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
    @CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateLanguage(int filmId, int languageId) {
        Film f = getFilm(filmId);
        Language l = languageRepo.findById(languageId).orElseThrow(() -> new NotFoundException("Language not found"));
        f.setLanguage(l);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    @CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateReleaseYear(int filmId, int year) {
        Film f = getFilm(filmId);
        f.setReleaseYear(year);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    @CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateTitle(int filmId, String title) {
        Film f = getFilm(filmId);
        f.setTitle(title);
        return FilmMapper.toDTO(filmRepo.save(f));
    }
//praphul's
    @Override
    @CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateRating(int filmId, String rating) {
        Film f = getFilm(filmId);
        Rating r = Rating.valueOf(rating.replace("-", "_"));
        f.setRating(r);
        return FilmMapper.toDTO(filmRepo.save(f));
    }
    @Override
    @CacheEvict(value = "films", allEntries = true)
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
    @CacheEvict(value = "films", allEntries = true)
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
    public List<FilmDTO> findByRentalDurationLessThan(int rentalDuration) {
        return filmRepo.findByRentalDurationLessThan(rentalDuration).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<FilmDTO> findByTitle(String title) {
        return filmRepo.findByTitle(title).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }


    @Override
    @CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateRentalRate(int filmId, BigDecimal rate) {
        Film f = getFilm(filmId);
        f.setRentalRate(rate);
        return FilmMapper.toDTO(filmRepo.save(f));
    }

    @Override
    public List<FilmDTO> findByLanguage(String lang) {
        return filmRepo.findByLanguageName(lang)
                .stream()
                .map(FilmMapper::toDTO)
                .toList();
    }

    @Override
    public List<FilmDTO> findByRentalRateLessThan(BigDecimal rate) {
        return filmRepo.findByRentalRateLessThan(rate).stream().map(FilmMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public java.util.List<FilmDTO> findByYear(int year){
        return filmRepo.findByReleaseYear(year).stream().map(FilmMapper::toDTO).toList();
    }

    @Override
    public List<FilmDTO> findAll() {
        return filmRepo.findAll().stream().map(FilmMapper::toDTO).collect(java.util.stream.Collectors.toList());
    }

    @Override
    @Cacheable(value = "films", key = "#pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<FilmDTO> findAll(Pageable pageable) {
        return filmRepo.findAll(pageable).map(FilmMapper::toDTO);
    }

    @Override
    public FilmDTO findById(int filmId) {
        Film f = getFilm(filmId);
        return FilmMapper.toDTO(f);
    }

    @Override
    @org.springframework.cache.annotation.CacheEvict(value = "films", allEntries = true)
    public FilmDTO updateWhole(int filmId, com.capgemini.film_rental.dto.FilmCreateDTO dto) {
        Film film = getFilm(filmId);

        // Update scalar fields if provided (null means untouched)
        if (dto.getTitle() != null) film.setTitle(dto.getTitle());
        if (dto.getDescription() != null) film.setDescription(dto.getDescription());
        if (dto.getReleaseYear() != null) film.setReleaseYear(dto.getReleaseYear());
        if (dto.getRentalDuration() != null) film.setRentalDuration(dto.getRentalDuration());
        if (dto.getRentalRate() != null) film.setRentalRate(dto.getRentalRate());
        if (dto.getLength() != null) film.setLength(dto.getLength());
        if (dto.getReplacementCost() != null) film.setReplacementCost(dto.getReplacementCost());
        if (dto.getRating() != null) {
            film.setRating(com.capgemini.film_rental.entity.enums.Rating.valueOf(dto.getRating().replace("-","_")));
        }
        if (dto.getSpecialFeatures() != null) film.setSpecialFeatures(dto.getSpecialFeatures());

        // Update language associations
        if (dto.getLanguageId() != null) {
            Language l = languageRepo.findById(dto.getLanguageId()).orElseThrow(() -> new NotFoundException("Language not found"));
            film.setLanguage(l);
        }
        if (dto.getOriginalLanguageId() != null) {
            Language ol = languageRepo.findById(dto.getOriginalLanguageId()).orElseThrow(() -> new NotFoundException("Original language not found"));
            film.setOriginalLanguage(ol);
        }

        // Replace categories if a list was provided (empty list will clear categories)
        if (dto.getCategoryIds() != null) {
            film.setCategories(categoryRepo.findAllById(dto.getCategoryIds()));
        }

        // Replace actors if a list was provided
        if (dto.getActorIds() != null) {
            film.setActors(actorRepo.findAllById(dto.getActorIds()));
        }

        Film saved = filmRepo.save(film);
        return FilmMapper.toDTO(saved);
    }

}