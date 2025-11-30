package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category,Integer> {
}
