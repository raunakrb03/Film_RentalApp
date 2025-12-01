package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.CustomerDTO;

public interface ICustomerService {
    CustomerDTO createCustomer(com.capgemini.film_rental.dto.CustomerCreateDTO dto);

    CustomerDTO assignStore(int id, int storeId);

    java.util.List<CustomerDTO> findByPhone(String phone);

    java.util.List<CustomerDTO> findByCity(String city);

    java.util.List<CustomerDTO> findByCountry(String country);

    CustomerDTO assignAddress(int id, int addressId);

    java.util.List<CustomerDTO> findByEmail(String email);

    CustomerDTO updatePhone(int id, String phone);

    CustomerDTO updateFirstName(int id, String firstName);

    CustomerDTO updateLastName(int id, String lastName);

    // Added: return a single customer by phone number (throws if not found)
    CustomerDTO getCustomerByNumber(String phone);

    CustomerDTO updateEmail(int id, String email);

    java.util.List<CustomerDTO> findInactiveCustomers();

    java.util.List<CustomerDTO> findActiveCustomers();

    java.util.List<CustomerDTO> findByFirstName(String firstName);

    java.util.List<CustomerDTO> findByLastName(String lastName);
}