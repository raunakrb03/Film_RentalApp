package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.StaffDTO;
import com.capgemini.film_rental.dto.StaffCreateDTO;
import com.capgemini.film_rental.service.IStaffService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffRestController {


    private final IStaffService service;

    public StaffRestController(IStaffService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public String create(@RequestBody StaffCreateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/update/ln/{id}")
    public StaffDTO updateLN(@PathVariable int id, @RequestParam String ln) {
        return service.updateLastName(id, ln);
    }

    @PutMapping("/update/fn/{id}")
    public StaffDTO updateFN(@PathVariable int id, @RequestParam String fn) {
        return service.updateFirstName(id, fn);
    }

    @PutMapping("/update/email/{id}")
    public StaffDTO updateEmail(@PathVariable int id, @RequestParam String email) {
        return service.updateEmail(id, email);
    }

    @GetMapping("/phone/{phone}")
    public List<StaffDTO> byPhone(@PathVariable String phone) {
        return service.findByPhone(phone);
    }

    @GetMapping("/country/{country}")
    public List<StaffDTO> byCountry(@PathVariable String country) {
        return service.findByCountry(country);
    }

    @GetMapping("/firstname/{fn}")
    public List<StaffDTO> byFN(@PathVariable String fn) {
        return service.findByFirstName(fn);
    }

    @GetMapping("/lastname/{ln}")
    public List<StaffDTO> byLN(@PathVariable String ln) {
        return service.findByLastName(ln);
    }

    @GetMapping("/email/{email}")
    public List<StaffDTO> byEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }


    @PutMapping("/update/phone/{id}")
    public StaffDTO updatePhone(@PathVariable int id, @RequestParam String phone) {
        return service.updatePhone(id, phone);
    }

    @GetMapping("/city/{city}")
    public List<StaffDTO> byCity(@PathVariable String city) {
        return service.findByCity(city);
    }

    // New endpoint: assign an address to a staff member
    @PutMapping("/{id}/address/{addressId}")
    public StaffDTO assignAddress(@PathVariable int id, @PathVariable int addressId) {
        return service.assignAddress(id, addressId);
    }
    /**
     * PUT /api/staff/update/store/{id}
     * Assign store to a staff member
     *
     * @param id the staff ID
     * @param storeId the store ID to assign (query parameter)
     * @return updated staff as StaffDTO
     */
    @PutMapping("/update/store/{id}")
    public StaffDTO assignStore(@PathVariable int id, @RequestParam int storeId) {
        return service.assignStore(id, storeId);
    }

}
