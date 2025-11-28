package com.capgemini.film_rental.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class FilmCategoryId implements Serializable {

    
	private int filmId;
    private int categoryId;

    // Getters, setters, equals, hashCode
}

