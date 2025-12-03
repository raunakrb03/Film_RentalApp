package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.CustomerCreateDTO;
import com.capgemini.film_rental.dto.CustomerDTO;
import com.capgemini.film_rental.service.ICustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customers")

@SuppressWarnings("unused") // Spring instantiates this controller reflectively; suppress "never used" warnings
public class CustomerRestController {

    private final ICustomerService service;

    public CustomerRestController(ICustomerService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public CustomerDTO createCustomer(@RequestBody CustomerCreateDTO dto) {
        return service.createCustomer(dto);
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

    @GetMapping("/country/{country}")
    public List<CustomerDTO> byCountry(@PathVariable String country) {
        return service.findByCountry(country);
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

    @PutMapping("/update/{id}/{fn}")
    public CustomerDTO updateFirstName(@PathVariable int id, @PathVariable String fn) {
        return service.updateFirstName(id, fn);
    }

    @PutMapping("/update/{id}/ln")
    public CustomerDTO updateLastName(@PathVariable int id, @PathVariable String ln) {
        return service.updateLastName(id, ln);
    }
    public CustomerDTO updateEmail(@PathVariable int id, @RequestParam String email) {
        return service.updateEmail(id, email);
    }

    @GetMapping("/inactive")
    public java.util.List<CustomerDTO> getInactiveCustomers() {
        return service.findInactiveCustomers();
    }

    @GetMapping("/active")
    public java.util.List<CustomerDTO> getActiveCustomers() {
        return service.findActiveCustomers();
    }


    @GetMapping("/lastname/{ln}")
    public java.util.List<CustomerDTO> getCustomersByLastName(@PathVariable String ln) {
        return service.findByLastName(ln);
    }

    @GetMapping("/firstname/{fn}")
    public CustomerDTO getCustomerByFirstName( @PathVariable String fn) {
        return service.findByFirstName(fn);
    }

}