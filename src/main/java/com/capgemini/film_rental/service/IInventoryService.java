package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmInventoryCountDTO;
import com.capgemini.film_rental.dto.InventoryAddDTO;
import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.dto.aggregates.StoreInventoryDTO;

import java.util.List;

import java.util.List;

public interface IInventoryService {
    FilmInventoryByStoreDTO inventoryOfFilmInStore(int filmId, int storeId);

    List<StoreInventoryDTO> inventoryByStore(int storeId);
    String addFilmToStore(InventoryAddDTO dto);

    List<FilmInventoryCountDTO> getAllFilmsInventoryCounts();
    List<FilmInventoryByStoreDTO> inventoryOfFilmAcrossStores(int filmId);
}
