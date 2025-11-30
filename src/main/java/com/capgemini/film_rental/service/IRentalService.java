package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.entity.Rental;

import java.util.List;

public interface IRentalService {
    public Rental rentFilm(RentalDTO rental);
    //praphul
    //akshaya
    //leena
    //raunak
    //praphul
    List<FilmDTO> getTopTenFilmsByStore(Integer storeId);
}
