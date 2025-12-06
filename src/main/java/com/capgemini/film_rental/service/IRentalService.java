package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalCreateDTO;
import com.capgemini.film_rental.dto.RentalDTO;

import java.util.List;

public interface IRentalService {
    String create(RentalCreateDTO dto);

    List<Integer> filmsRentedToCustomer(int customerId);

    RentalDTO updateReturnDate(int rentalId, String returnDateIso);
    List<FilmDTO> getTopTenFilmsByStore(Integer storeId);
    List<FilmDTO> getTopTenMostRentedFilms();
    List<RentalDTO> getDueRentalsByStore(int storeId);
    List<RentalDTO> getAll();
}