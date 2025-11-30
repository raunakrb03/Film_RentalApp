package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.repository.IInventoryRepository;
import com.capgemini.film_rental.service.IInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryServiceImpl implements IInventoryService {

    private final IInventoryRepository repo;

    public InventoryServiceImpl(IInventoryRepository repo) {
        this.repo = repo;
    }

    @Override
    public FilmInventoryByStoreDTO inventoryOfFilmInStore(int filmId, int storeId) {
        List<Object[]> list = repo.inventoryFilmInStore(filmId, storeId);
        if (list.isEmpty()) return new FilmInventoryByStoreDTO(0, 0);
        Object[] arr = list.get(0);
        return new FilmInventoryByStoreDTO(((Number) arr[0]).intValue(), ((Number) arr[1]).longValue());
    }
}