package com.capgemini.film_rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmrental.beans.Address;
import com.filmrental.beans.Inventory;

public interface IAddressRepository extends JpaRepository<Address, Integer>{

}
