package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IStoreRepository extends JpaRepository<Store,Integer> {
    List<Store> findByAddress_City_CityIgnoreCase(String city);

    List<Store> findByAddress_Phone(String phone);
}
