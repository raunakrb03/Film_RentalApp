
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.service.IRentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental")
public class RentalRestController {

    private final IRentalService rentalService;

    public RentalRestController(IRentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * Display top 10 most rented Films of a Store
     * Endpoint: GET /api/rental/toptenfilms/store/{id}
     */
    @GetMapping("/toptenfilms/store/{id}")
    public ResponseEntity<List<FilmDTO>> getTopTenFilmsByStore(@PathVariable("id") Integer storeId) {
        if (storeId == null || storeId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<FilmDTO> films = rentalService.getTopTenFilmsByStore(storeId);
        return ResponseEntity.ok(films);
    }
}
