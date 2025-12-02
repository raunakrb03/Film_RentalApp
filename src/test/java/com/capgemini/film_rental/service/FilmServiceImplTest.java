package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.entity.enums.Rating;
import com.capgemini.film_rental.mapper.FilmMapper;
import com.capgemini.film_rental.repository.IFilmRepository;
import com.capgemini.film_rental.repository.ICategoryRepository;
import com.capgemini.film_rental.repository.ILanguageRepository;
import com.capgemini.film_rental.repository.IActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FilmServiceImplTest {

    private IFilmRepository filmRepo;
    private ICategoryRepository categoryRepo;
    private ILanguageRepository languageRepo;
    private FilmServiceImpl service;
    private IActorRepository actorRepo;

    @BeforeEach
    public void setUp() {
        filmRepo = mock(IFilmRepository.class);
        categoryRepo = mock(ICategoryRepository.class);
        languageRepo = mock(ILanguageRepository.class);
        actorRepo = mock(IActorRepository.class);
        service = new FilmServiceImpl(filmRepo, categoryRepo, languageRepo, actorRepo);
    }

    @Test
    public void updateRating_setsRatingAndSaves() {
        Film f = new Film();
        f.setFilmId(1);
        f.setTitle("Test");
        when(filmRepo.findById(1)).thenReturn(Optional.of(f));
        when(filmRepo.save(any(Film.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FilmDTO dto = service.updateRating(1, "PG-13");

        ArgumentCaptor<Film> captor = ArgumentCaptor.forClass(Film.class);
        verify(filmRepo).save(captor.capture());
        Film saved = captor.getValue();
        assertEquals(Rating.PG_13, saved.getRating());
        assertNotNull(dto);
        assertEquals("PG-13", dto.getRating());
    }
}
