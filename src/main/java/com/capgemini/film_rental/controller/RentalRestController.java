
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalCreateDTO;
import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.service.IRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalRestController {

    @Autowired
    private final IRentalService service;

    public RentalRestController(IRentalService service) {
        this.service = service;
    }
    @PostMapping("/add")
    public String rent(@RequestBody RentalCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/customer/{id}")
    public List<Integer> filmsRentedToCustomer(@PathVariable int id) {
        return service.filmsRentedToCustomer(id);
    }

    @PostMapping("/update/returndate/{id}")
    public RentalDTO updateReturnDate(@PathVariable int id, @RequestParam String returnDateIso) {
        return service.updateReturnDate(id, returnDateIso);
    }

    /**
     * GET /api/rental/toptenfilms
     * Display top 10 most rented Films across all stores
     */
    @GetMapping("/toptenfilms")
    public ResponseEntity<List<FilmDTO>> getTopTenMostRentedFilms() {
        List<FilmDTO> films = service.getTopTenMostRentedFilms();
        return ResponseEntity.ok(films);
    }

    /**
     * GET /api/rental/toptenfilms/store/{id}
     * Display top 10 most rented Films of a Store
     */
    @GetMapping("/toptenfilms/store/{id}")
    public List<FilmDTO> getTopTenFilmsByStore(@PathVariable("id") Integer storeId) {
        return service.getTopTenFilmsByStore(storeId);
    }
}
