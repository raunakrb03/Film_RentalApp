package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;

public interface IInventoryService {
    FilmInventoryByStoreDTO inventoryOfFilmInStore(int filmId, int storeId);

}
