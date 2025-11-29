package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Actor;
import com.capgemini.film_rental.entity.Rental;

import java.util.List;

public interface IRentalService {
    public Rental rentFilm(Rental rental);
    //praphul
    //akshaya
    //leena
    //raunak
    //praphul
    List<FilmDTO> getTopTenFilmsByStore(Integer storeId);
}
