package com.capgemini.film_rental.controller;




import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.dto.ActorWithFilmCountDTO;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.service.IActorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ActorRestController.class)
public class ActorRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IActorService actorService;

    @Test
    void testCreateActor() throws Exception {
        ActorCreateDTO dto = new ActorCreateDTO();
        dto.setFirstName("Tom");
        dto.setLastName("Hanks");
        Mockito.when(actorService.createActor(Mockito.any())).thenReturn("Record Created Successfully");

        mockMvc.perform(post("/api/actors/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Tom\",\"lastName\":\"Hanks\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Created Successfully"));
    }

    @Test
    void testUpdateFirstName() throws Exception {
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setActorId(1);
        actorDTO.setFirstName("Tom");
        actorDTO.setLastName("Hanks");
        Mockito.when(actorService.updateFirstName(1, "Tommy")).thenReturn(new com.capgemini.film_rental.entity.Actor());

        mockMvc.perform(put("/api/actors/update/firstname/1")
                        .param("firstName", "Tommy"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTop10ByFilmCount() throws Exception {
        List<ActorWithFilmCountDTO> list = List.of(new ActorWithFilmCountDTO(1, "Tom", "Hanks", 50));
        Mockito.when(actorService.findTop10ByFilmCount()).thenReturn(list);

        mockMvc.perform(get("/api/actors/toptenbyfilmcount"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Tom"));
    }

    @Test
    void testGetActorsByFirstName() throws Exception {
        ActorDTO a = new ActorDTO();
        a.setActorId(1);
        a.setFirstName("Tom");
        a.setLastName("Hanks");
        List<ActorDTO> actors = List.of(a);
        Mockito.when(actorService.findByFirstName("Tom")).thenReturn(List.of());

        mockMvc.perform(get("/api/actors/firstname/Tom"))
                .andExpect(status().isOk());
    }

    @Test
    void testAssignFilmsToActor() throws Exception {
        FilmDTO film = new FilmDTO();
        film.setFilmId(1);
        film.setTitle("Inception");
        List<FilmDTO> films = List.of(film);
        Mockito.when(actorService.assignFilmsToActor(1, List.of(101, 102))).thenReturn(films);

        mockMvc.perform(put("/api/actors/1/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[101,102]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"));
    }
}
