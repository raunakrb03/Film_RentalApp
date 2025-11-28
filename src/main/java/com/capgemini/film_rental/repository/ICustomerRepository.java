package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<Customer,Integer>{
}
