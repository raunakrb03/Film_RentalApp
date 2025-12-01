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

    @GetMapping("/country/{country}")
    public List<StoreDTO> byCountry(@PathVariable String country) {
        return service.findByCountry(country);
    }

    @PutMapping("/{storeId}/address/{addressId}")
    public StoreDTO assignAddress(@PathVariable int storeId, @PathVariable int addressId) {
        return service.assignAddress(storeId, addressId);
    }

    @PutMapping("/{storeId}/manager/{manager_staff_id}")
    public StoreDTO assignManager(@PathVariable int storeId, @PathVariable(name = "manager_staff_id") int managerStaffId) {
        return service.assignManager(storeId, managerStaffId);
    @PutMapping("/update/{storeId}/{phone}")
    public StoreDTO updatePhone(@PathVariable int storeId, @PathVariable String phone) {
        return service.updatePhone(storeId, phone);
    }

    @GetMapping("/managers")
    public List<StoreDTO.ManagerAndStoreView> managersOverview() {
        return service.managersOverview();
    }

    @GetMapping("/phone/{phone}")
    public StoreDTO byPhone(@PathVariable String phone) {
        return service.findByPhone(phone);
    }
    @GetMapping("/staff/{storeId}")
    public List<Integer> staffIds(@PathVariable int storeId) {
        return service.staffIds(storeId);
    }

    @PostMapping("/post")
    public StoreDTO createStore(@RequestBody StoreDTO dto) {
        return service.createStore(dto);
    }


}