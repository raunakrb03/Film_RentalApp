package com.capgemini.film_rental.controller;



import com.capgemini.film_rental.dto.StoreDTO;
import com.capgemini.film_rental.service.IStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreRestController {

    private final IStoreService service;

    public StoreRestController(IStoreService service) {
        this.service = service;
    }

    @GetMapping("/manager/{storeId}")
    public StoreDTO.ManagerDetails manager(@PathVariable int storeId) {
        return service.managerDetails(storeId);
    }

    @GetMapping("/customer/{storeId}")
    public List<Integer> customers(@PathVariable int storeId) {
        return service.customerIds(storeId);
    }

    @GetMapping("/city/{city}")
    public List<StoreDTO> byCity(@PathVariable String city) {
        return service.findByCity(city);
    }

    @PutMapping("/{storeId}/address/{addressId}")
    public StoreDTO assignAddress(@PathVariable int storeId, @PathVariable int addressId) {
        return service.assignAddress(storeId, addressId);
    }

    @GetMapping("/managers")
    public List<StoreDTO.ManagerAndStoreView> managersOverview() {
        return service.managersOverview();
    }
}