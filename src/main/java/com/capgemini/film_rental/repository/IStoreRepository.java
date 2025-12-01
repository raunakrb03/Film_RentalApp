package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStoreRepository extends JpaRepository<Store,Integer> {
    List<Store> findByAddress_City_CityIgnoreCase(String city);

    List<Store> findByAddress_Phone(String phone);

    @Query("select s from Store s where lower(s.address.city.country.country) = lower(:country)")
    List<Store> findByCountry(@Param("country") String country);
}
