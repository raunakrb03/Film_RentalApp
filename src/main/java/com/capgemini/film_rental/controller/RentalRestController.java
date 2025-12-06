package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalCreateDTO;
import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.dto.PageResponse;
import com.capgemini.film_rental.dto.aggregates.UpdateReturnDateDTO;
import com.capgemini.film_rental.service.IRentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FilmDTO>> getTopTenFilmsByStore(@PathVariable("id") Integer storeId) {
        if (storeId == null || storeId <= 0) {
            return ResponseEntity.badRequest().build();
        }
        List<FilmDTO> films = service.getTopTenFilmsByStore(storeId);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/due/store/{id}")
    public ResponseEntity<List<RentalDTO>> getDueRentalsByStore(@PathVariable("id") int storeId) {
        List<RentalDTO> dueRentals = service.getDueRentalsByStore(storeId);
        return ResponseEntity.ok(dueRentals);
    }


    @PutMapping("/update/returndate/{id}")
    public ResponseEntity<RentalDTO> updateReturnDate(
            @PathVariable int id,
            @Valid @RequestBody UpdateReturnDateDTO dto) {
        RentalDTO updatedRental = service.updateReturnDate(id, dto.getReturnDateIso());
        return ResponseEntity.ok(updatedRental);
    }

    /**
     * GET /api/rental/getAll
     * Supports optional paging: ?page=0&size=20
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        if (page == null || size == null) {
            List<RentalDTO> rentals = service.getAll();
            return ResponseEntity.ok(rentals);
        }
        int p = Math.max(0, page);
        int s = Math.max(1, size);
        Pageable pageable = PageRequest.of(p, s);
        Page<RentalDTO> pageResult = service.getAll(pageable);
        PageResponse<RentalDTO> resp = PageResponse.fromPage(pageResult);
        return ResponseEntity.ok(resp);
    }

}
