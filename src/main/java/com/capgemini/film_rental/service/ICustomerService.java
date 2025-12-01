package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.CustomerDTO;

public interface ICustomerService {
    CustomerDTO assignStore(int id, int storeId);

    java.util.List<CustomerDTO> findByPhone(String phone);

    java.util.List<CustomerDTO> findByCity(String city);

    CustomerDTO assignAddress(int id, int addressId);

    java.util.List<CustomerDTO> findByEmail(String email);

    CustomerDTO updatePhone(int id, String phone);

    // Added: return a single customer by phone number (throws if not found)
    CustomerDTO getCustomerByNumber(String phone);
}