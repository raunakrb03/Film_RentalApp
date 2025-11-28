package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IAddressRepository extends JpaRepository<Address, Integer>{

}
