package com.capgemini.film_rental.entity;


import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class FilmActorId implements Serializable 
{

   
	private int actorId;
    private int filmId;

    // Getters, setters, equals, hashCode
}
