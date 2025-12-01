package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
    @Query("select c from Customer c where c.address.phone = :phone")
    List<Customer> findByPhone(@Param("phone") String phone);

    @Query("select c from Customer c where lower(c.address.city.city) = lower(:city)")
    List<Customer> findByCity(@Param("city") String city);

    @Query("select c from Customer c where lower(c.address.city.country.country) = lower(:country)")
    List<Customer> findByCountry(@Param("country") String country);

    List<Customer> findByEmailIgnoreCase(String email);

    java.util.List<Customer> findByActiveFalse();
    java.util.List<Customer> findByFirstNameIgnoreCase(String firstName);
}
