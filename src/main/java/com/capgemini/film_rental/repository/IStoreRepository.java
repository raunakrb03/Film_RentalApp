package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<Store,Integer> {
}
