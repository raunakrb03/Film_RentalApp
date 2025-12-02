package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmInventoryCountDTO;
import com.capgemini.film_rental.dto.InventoryAddDTO;
import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.entity.Film;
import com.capgemini.film_rental.entity.Inventory;
import com.capgemini.film_rental.entity.Store;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.IFilmRepository;
import com.capgemini.film_rental.repository.IInventoryRepository;
import com.capgemini.film_rental.repository.IStoreRepository;
import com.capgemini.film_rental.service.IInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventoryServiceImpl implements IInventoryService {

    private final IInventoryRepository repo;
    private final IFilmRepository filmRepo;
    private final IStoreRepository storeRepo;

    public InventoryServiceImpl(IInventoryRepository repo, IFilmRepository filmRepo, IStoreRepository storeRepo) {
        this.repo = repo;
        this.filmRepo = filmRepo;
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
    public String addFilmToStore(InventoryAddDTO dto) {
        Film film = filmRepo.findById(dto.getFilmId())
                .orElseThrow(() -> new NotFoundException("Film not found with ID: " + dto.getFilmId()));
        Store store = storeRepo.findById(dto.getStoreId())
                .orElseThrow(() -> new NotFoundException("Store not found with ID: " + dto.getStoreId()));

        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setLastUpdate(java.time.LocalDateTime.now());

        Inventory saved = repo.save(inventory);
        return "Inventory record created successfully with ID: " + saved.getInventoryId();
    }

    @Override
    public List<FilmInventoryCountDTO> getAllFilmsInventoryCounts() {
        List<Object[]> results = repo.getAllFilmsInventoryCounts();
        return results.stream().map(row -> new FilmInventoryCountDTO(
            ((Number) row[0]).intValue(),
            (String) row[1],
            ((Number) row[2]).intValue(),
            ((Number) row[3]).longValue()
        )).collect(Collectors.toList());
    }

    @Override
    public List<FilmInventoryByStoreDTO> inventoryOfFilmAcrossStores(int filmId) {
        List<Object[]> results = repo.inventoryFilmAcrossStores(filmId);
        return results.stream()
                .map(row -> new FilmInventoryByStoreDTO(
                        ((Number) row[0]).intValue(),  // storeId
                        ((Number) row[1]).longValue()  // inventoryCount
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmInventoryCountDTO> inventoryByStore(int storeId) {
        // Validate store exists
        if (!storeRepo.existsById(storeId)) {
            throw new NotFoundException("Store not found with ID: " + storeId);
        }

        // Fetch inventory by store
        List<Object[]> results = repo.inventoryByStore(storeId);

        // Check if any inventory exists for this store
        if (results == null || results.isEmpty()) {
            throw new NotFoundException("No inventory found for store ID: " + storeId);
        }

        // Convert results to DTOs
        return results.stream()
                .map(row -> new FilmInventoryCountDTO(
                        ((Number) row[0]).intValue(),  // filmId
                        (String) row[1],               // filmTitle
                        0,                             // storeId (not included in query)
                        ((Number) row[2]).longValue()  // inventoryCount
                ))
                .collect(Collectors.toList());
    }
}