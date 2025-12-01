package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.CustomerDTO;
import com.capgemini.film_rental.service.ICustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@SuppressWarnings("unused") // Spring instantiates this controller reflectively; suppress "never used" warnings
public class CustomerRestController {

    private final ICustomerService service;

    public CustomerRestController(ICustomerService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}/store")
    public CustomerDTO assignStore(@PathVariable int id, @RequestParam int storeId) {
        return service.assignStore(id, storeId);
    }

    @GetMapping("/phone/{phone}")
    public List<CustomerDTO> byPhone(@PathVariable String phone) {
        return service.findByPhone(phone);
    }

    @GetMapping("/city/{city}")
    public List<CustomerDTO> byCity(@PathVariable String city) {
        return service.findByCity(city);
    }

    @PutMapping("/{id}/{addressId}")
    public CustomerDTO assignAddress(@PathVariable int id, @PathVariable int addressId) {
        return service.assignAddress(id, addressId);
    }

    @GetMapping("/email/{email}")
    public List<CustomerDTO> byEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PutMapping("/update/{id}/phone")
    public CustomerDTO updatePhone(@PathVariable int id, @RequestParam String phone) {
        return service.updatePhone(id, phone);
    }
}