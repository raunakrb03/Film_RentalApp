package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.dto.aggregates.StoreInventoryDTO;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.IInventoryRepository;
import com.capgemini.film_rental.repository.IStoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryServiceImpl implements IInventoryService {

    private final IInventoryRepository repo;
    private final IStoreRepository storeRepo;

    public InventoryServiceImpl(IInventoryRepository repo, IStoreRepository storeRepo) {
        this.repo = repo;
        this.storeRepo = storeRepo;
    }

    @Override
    public FilmInventoryByStoreDTO inventoryOfFilmInStore(int filmId, int storeId) {
        List<Object[]> list = repo.inventoryFilmInStore(filmId, storeId);
        if (list.isEmpty()) return new FilmInventoryByStoreDTO(0, 0);
        Object[] arr = list.get(0);
        return new FilmInventoryByStoreDTO(((Number) arr[0]).intValue(), ((Number) arr[1]).longValue());
    }

    @Override
    public List<StoreInventoryDTO> inventoryByStore(int storeId) {
        // Validate storeId
        if (storeId <= 0) {
            throw new IllegalArgumentException("Store ID must be greater than 0");
        }

        // Check if store exists
        if (!storeRepo.existsById(storeId)) {
            throw new NotFoundException("Store not found with ID: " + storeId);
        }

        // Fetch inventory for the store
        List<Object[]> results = repo.inventoryByStore(storeId);

        // Check if any inventory exists for this store
        if (results == null || results.isEmpty()) {
            throw new NotFoundException("No inventory found for store ID: " + storeId);
        }

        // Convert results to DTOs
        return results.stream()
                .map(arr -> new StoreInventoryDTO(
                        (String) arr[0],  // film title
                        ((Number) arr[1]).longValue()  // count of copies
                ))
                .collect(Collectors.toList());
    }
}