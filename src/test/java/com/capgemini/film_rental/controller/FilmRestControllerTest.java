package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmCreateDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.service.IFilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class FilmRestControllerTest {

    @Mock
    private IFilmService filmService;

    @InjectMocks
    private FilmRestController filmRestController;

    private FilmDTO sampleFilm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleFilm = new FilmDTO();
        sampleFilm.setFilmId(1);
        sampleFilm.setTitle("Test Film");
    }

    @Test
    void testCreateFilm() {
        FilmCreateDTO dto = new FilmCreateDTO();
        dto.setTitle("New Film");

        when(filmService.create(dto)).thenReturn("Record Created Successfully id=1");

        String response = filmRestController.create(dto);

        assertEquals("Record Created Successfully id=1", response);
        verify(filmService, times(1)).create(dto);
    }

    @Test
    void testUpdateTitle() {
        when(filmService.updateTitle(1, "Updated Title")).thenReturn(sampleFilm);

        FilmDTO response = filmRestController.updateTitle(1, "Updated Title");

        assertEquals("Test Film", response.getTitle());
        verify(filmService, times(1)).updateTitle(1, "Updated Title");
    }

    @Test
    void testUpdateRatingValid() {
        when(filmService.updateRating(1, "PG")).thenReturn(sampleFilm);

        ResponseEntity<?> response = filmRestController.updateRating(1, "PG");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof FilmDTO);
        verify(filmService, times(1)).updateRating(1, "PG");
    }

    @Test
    void testUpdateRatingInvalid() {
        ResponseEntity<?> response = filmRestController.updateRating(1, "INVALID");

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid rating value. Allowed: G, PG, PG-13, R, NC-17", response.getBody());
        verify(filmService, never()).updateRating(anyInt(), anyString());
    }

    @Test
    void testAddActor() {
        when(filmService.addActor(1, 2)).thenReturn(sampleFilm);

        ResponseEntity<FilmDTO> response = filmRestController.addActor(1, 2);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(sampleFilm, response.getBody());
        verify(filmService, times(1)).addActor(1, 2);
    }

    @Test
    void testCountFilmsByYear() {
        Map<Integer, Long> mockMap = Map.of(2020, 5L);
        when(filmService.countFilmsByYear()).thenReturn(mockMap);

        Map<Integer, Long> response = filmRestController.countFilmsByYear();

        assertEquals(5L, response.get(2020));
        verify(filmService, times(1)).countFilmsByYear();
    }

    @Test
    void testFindByCategory() {
        when(filmService.findByCategory("Action")).thenReturn(List.of(sampleFilm));

        List<FilmDTO> response = filmRestController.byCategory("Action");

        assertEquals(1, response.size());
        assertEquals("Test Film", response.get(0).getTitle());
        verify(filmService, times(1)).findByCategory("Action");
    }

    @Test
    void testUpdateRentalRate() {
        when(filmService.updateRentalRate(1, BigDecimal.valueOf(4.99))).thenReturn(sampleFilm);

        FilmDTO response = filmRestController.updateRentalRate(1, BigDecimal.valueOf(4.99));

        assertEquals(sampleFilm, response);
        verify(filmService, times(1)).updateRentalRate(1, BigDecimal.valueOf(4.99));
    }
}
