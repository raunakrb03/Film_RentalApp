package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILanguageRepository extends JpaRepository<Language,Integer> {
}
