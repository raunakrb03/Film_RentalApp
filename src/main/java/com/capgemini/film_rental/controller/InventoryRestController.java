package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmInventoryCountDTO;
import com.capgemini.film_rental.dto.InventoryAddDTO;
import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.service.IInventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    public String addFilmToStore(@RequestBody InventoryAddDTO dto) {
        return service.addFilmToStore(dto);
    }

    @GetMapping("/films")
    public List<FilmInventoryCountDTO> getAllFilmsInventory() {
        return service.getAllFilmsInventoryCounts();
    }

    @GetMapping("/film/{id}")
    public List<FilmInventoryByStoreDTO> inventoryOfFilmAcrossStores(@PathVariable int id) {
        return service.inventoryOfFilmAcrossStores(id);
    }

    /**
     * GET /api/inventory/store/{id}
     * Display inventory of all Films by a Store
     *
     * @param id the store ID
     * @return collection of films with their inventory count as FilmInventoryCountDTO
     */
    @GetMapping("/store/{id}")
    public List<FilmInventoryCountDTO> inventoryByStore(@PathVariable int id) {
        return service.inventoryByStore(id);
    }

}