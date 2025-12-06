package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.PageResponse;
import com.capgemini.film_rental.dto.StoreDTO;
import com.capgemini.film_rental.service.IStoreService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Store REST Controller with Caffeine caching support
 *
 * Cached endpoints:
 * - GET /api/store - Caches all stores for 10 minutes
 * - GET /api/store/city/{city} - Caches stores by city for 10 minutes
 * - GET /api/store/country/{country} - Caches stores by country for 10 minutes
 * - GET /api/store/managers - Caches managers overview for 10 minutes
 *
 * Cache invalidation occurs on:
 * - POST /api/store/post - Creates new store
 * - PUT /api/store/{storeId}/address/{addressId} - Updates store address
 * - PUT /api/store/update/{storeId}/{phone} - Updates store phone
 */
@RestController
@RequestMapping("/api/store")
@CrossOrigin(origins = {"http://localhost:4200", "https://localhost:4200"})
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

    @GetMapping("/country/{country}")
    public List<StoreDTO> byCountry(@PathVariable String country) {
        return service.findByCountry(country);
    }

    @PutMapping("/{storeId}/address/{addressId}")
    public StoreDTO assignAddress(@PathVariable int storeId, @PathVariable int addressId) {
        return service.assignAddress(storeId, addressId);
    }

    @PutMapping("/update/{storeId}/{phone}")
    public StoreDTO updatePhone(@PathVariable int storeId, @PathVariable String phone) {
        return service.updatePhone(storeId, phone);
    }

    @GetMapping("/managers")
    public List<StoreDTO.ManagerAndStoreView> managersOverview() {
        return service.managersOverview();
    }

    @GetMapping("/staff/{storeId}")
    public List<Integer> staffIds(@PathVariable int storeId) {
        return service.staffIds(storeId);
    }

    @PostMapping("/post")
    public StoreDTO createStore(@RequestBody StoreDTO dto) {
        return service.createStore(dto);
    }

    @GetMapping
    public List<StoreDTO> getAll() {
        return service.getAll();
    }

    // New paged endpoint for frontend performance
    @GetMapping("/paged")
    public PageResponse<StoreDTO> getPaged(@PageableDefault(size = 20) Pageable pageable) {
        return service.findAllPaged(pageable);
    }

}