package com.capgemini.film_rental.controller;



import com.capgemini.film_rental.dto.PaymentCreateDTO;
import com.capgemini.film_rental.dto.aggregates.FilmRevenueDTO;
import com.capgemini.film_rental.dto.aggregates.StoreRevenueByDateDTO;
import com.capgemini.film_rental.service.IPaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentRestController {


    private final IPaymentService service;

    public PaymentRestController(IPaymentService service) {
        this.service = service;
    }

    @PutMapping("/add")
    public String addPayment(@RequestBody PaymentCreateDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/revenue/films/store/{id}")
    public List<FilmRevenueDTO> revenueFilmsStore(@PathVariable int id) {
        return service.cumulativeRevenueOfAllFilmsByStore(id);
    }

    @GetMapping("/revenue/datewise")
    public List<StoreRevenueByDateDTO> revenueAllStoresDatewise() {
        return service.cumulativeRevenueAllStoresDatewise();
    }

    @GetMapping("/revenue/filmwise")
    public List<FilmRevenueDTO> revenueAllFilmsAcrossStores() {
        return service.cumulativeRevenueOfAllFilmsAcrossStores();
    }
}