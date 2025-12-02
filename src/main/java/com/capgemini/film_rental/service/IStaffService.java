package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.StaffDTO;
import com.capgemini.film_rental.dto.StaffCreateDTO;

import java.util.List;

public interface IStaffService {
    String create(StaffCreateDTO dto);

    StaffDTO updateLastName(int id, String ln);

    StaffDTO updateFirstName(int id, String fn);

    StaffDTO updateEmail(int id, String email);

    java.util.List<StaffDTO> findByPhone(String phone);

    java.util.List<StaffDTO> findByCountry(String country);

    java.util.List<StaffDTO> findByFirstName(String fn);

    java.util.List<StaffDTO> findByLastName(String ln);

    java.util.List<StaffDTO> findByEmail(String email);
    StaffDTO updatePhone(int id, String phone);

    StaffDTO assignAddress(int staffId, int addressId);
    List<StaffDTO> findByCity(String city);
    StaffDTO assignStore(int staffId, int storeId);


}