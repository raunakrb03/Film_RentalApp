package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmInventoryCountDTO;
import com.capgemini.film_rental.dto.InventoryAddDTO;
import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;

import java.util.List;

public interface IInventoryService {
    FilmInventoryByStoreDTO inventoryOfFilmInStore(int filmId, int storeId);

    String addFilmToStore(InventoryAddDTO dto);

    List<FilmInventoryCountDTO> getAllFilmsInventoryCounts();
    List<FilmInventoryByStoreDTO> inventoryOfFilmAcrossStores(int filmId);

    List<FilmInventoryCountDTO> inventoryByStore(int storeId);
}
