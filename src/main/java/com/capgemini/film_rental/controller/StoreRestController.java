package com.capgemini.film_rental.controller;



import com.capgemini.film_rental.dto.StoreDTO;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.service.IStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
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

    @GetMapping("/phone/{phone}")
    public ResponseEntity<StoreDTO> getStoreByPhone(@PathVariable String phone) {
        try {
            StoreDTO storeDTO = service.findByPhone(phone);
            return ResponseEntity.ok(storeDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // or custom error response
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    /**
     * PUT /api/store/{storeId}/manager/{manager_staff_id}
     * Assign manager to a store
     *
     * @param storeId the store ID
     * @param manager_staff_id the staff ID to assign as manager
     * @return updated store as StoreDTO with address details
     */
    @PutMapping("/{storeId}/manager/{manager_staff_id}")
    public StoreDTO assignManager(@PathVariable int storeId, @PathVariable int manager_staff_id) {
        return service.assignManager(storeId, manager_staff_id);
    }

}