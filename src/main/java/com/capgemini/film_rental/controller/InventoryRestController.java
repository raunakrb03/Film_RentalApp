package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.service.IInventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryRestController {


    private final IInventoryService service;

    public InventoryRestController(IInventoryService service) {
        this.service = service;
    }

    @GetMapping("/film/{id}/store/{id2}")
    public FilmInventoryByStoreDTO inventoryOfFilmInStore(@PathVariable("id") int filmId, @PathVariable("id2") int storeId) {
        return service.inventoryOfFilmInStore(filmId, storeId);
    }
}