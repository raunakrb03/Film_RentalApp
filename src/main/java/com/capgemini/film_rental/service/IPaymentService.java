package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.PaymentCreateDTO;
import com.capgemini.film_rental.dto.aggregates.FilmRevenueDTO;
import com.capgemini.film_rental.dto.aggregates.StoreRevenueByDateDTO;

import java.util.List;

public interface IPaymentService {
    String create(PaymentCreateDTO dto);

    List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsByStore(int storeId);

    List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsAcrossStores();

    List<StoreRevenueByDateDTO> cumulativeRevenueAllStoresDatewise();

    List<StoreRevenueByDateDTO> cumulativeRevenueByDateForStore(int storeId);
    List<FilmRevenueDTO> cumulativeRevenueForFilm(int filmId);
}
