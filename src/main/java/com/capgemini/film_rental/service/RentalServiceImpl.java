package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalCreateDTO;
import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.entity.Inventory;
import com.capgemini.film_rental.entity.Rental;
import com.capgemini.film_rental.entity.Staff;
import com.capgemini.film_rental.entity.Customer;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.*;
import com.capgemini.film_rental.service.IRentalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@Transactional
public class RentalServiceImpl implements IRentalService {

    private final com.capgemini.film_rental.repository.IRentalRepository repo;
    private final com.capgemini.film_rental.repository.IInventoryRepository invRepo;
    private final com.capgemini.film_rental.repository.ICustomerRepository custRepo;
    private final com.capgemini.film_rental.repository.IStaffRepository staffRepo;

    public RentalServiceImpl(IRentalRepository repo, IInventoryRepository invRepo, ICustomerRepository custRepo, IStaffRepository staffRepo) {
        this.repo = repo;
        this.invRepo = invRepo;
        this.custRepo = custRepo;
        this.staffRepo = staffRepo;
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String create(RentalCreateDTO dto) {
        Inventory inv = invRepo.findById(dto.getInventoryId()).orElseThrow(() -> new NotFoundException("Inventory not found"));
        Customer cust = custRepo.findById(dto.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));
        Staff staff = staffRepo.findById(dto.getStaffId()).orElseThrow(() -> new NotFoundException("Staff not found"));
        Rental r = new Rental();
        r.setInventory(inv);
        r.setCustomer(cust);
        r.setStaff(staff);
        r.setRentalDate(LocalDateTime.now());
        repo.save(r);
        return "Record Created Successfully";
    }

    @Override
    public List<Integer> filmsRentedToCustomer(int customerId) {
        return repo.filmsRentedToCustomer(customerId);
    }

    @Override
    public RentalDTO updateReturnDate(int rentalId, String returnDateIso) {
        Rental r = repo.findById(rentalId).orElseThrow(() -> new NotFoundException("Rental not found"));
        try {
            r.setReturnDate(LocalDateTime.parse(returnDateIso));
        } catch (DateTimeParseException e) {
            r.setReturnDate(LocalDate.parse(returnDateIso).atStartOfDay());
        }
        repo.save(r);
        RentalDTO dto = new RentalDTO();
        dto.setRentalId(r.getRentalId());
        dto.setRentalDate(r.getRentalDate());
        dto.setReturnDate(r.getReturnDate());
        dto.setInventoryId(r.getInventory().getInventoryId());
        dto.setCustomerId(r.getCustomer().getCustomerId());
        dto.setStaffId(r.getStaff().getStaffId());
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> getTopTenFilmsByStore(Integer storeId) {
        if (storeId == null || storeId <= 0) {
            throw new IllegalArgumentException("storeId must be a positive integer");
        }

        String jpql = """
            SELECT f.filmId, f.title, f.description, f.releaseYear, f.language.languageId, 
                   f.originalLanguage.languageId, f.rentalDuration, f.rentalRate, f.length, 
                   f.replacementCost, f.rating, f.specialFeatures, f.lastUpdate
            FROM Rental r
            JOIN r.inventory i
            JOIN i.film f
            JOIN i.store s
            WHERE s.storeId = :storeId
            GROUP BY f.filmId, f.title, f.description, f.releaseYear, f.language.languageId, f.originalLanguage.languageId,
                     f.rentalDuration, f.rentalRate, f.length, f.replacementCost,
                     f.rating, f.specialFeatures, f.lastUpdate
            ORDER BY COUNT(r.rentalId) DESC, f.title ASC
        """;

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("storeId", storeId);
        query.setMaxResults(10);

        List<Object[]> results = query.getResultList();
        List<FilmDTO> films = new java.util.ArrayList<>();

        for (Object[] row : results) {
            FilmDTO film = new FilmDTO();
            film.setFilmId(((Number) row[0]).intValue());
            film.setTitle((String) row[1]);
            film.setDescription((String) row[2]);
            film.setReleaseYear((Integer) row[3]);
            film.setLanguageId(row[4] != null ? ((Number) row[4]).intValue() : null);
            film.setOriginalLanguageId(row[5] != null ? ((Number) row[5]).intValue() : null);
            film.setRentalDuration(((Number) row[6]).intValue());
            film.setRentalRate((BigDecimal) row[7]);
            film.setLength((Short) row[8]);
            film.setReplacementCost((BigDecimal) row[9]);
            film.setRating(((com.capgemini.film_rental.entity.enums.Rating) row[10]).toString());
            film.setSpecialFeatures((String) row[11]);
            film.setLastUpdate((LocalDateTime) row[12]);
            films.add(film);
        }

        // Populate rental count for each film
        for (FilmDTO film : films) {
            Long count = entityManager.createQuery("""
                SELECT COUNT(r.rentalId)
                FROM Rental r
                JOIN r.inventory i
                WHERE i.film.filmId = :filmId AND i.store.storeId = :storeId
            """, Long.class)
                    .setParameter("filmId", film.getFilmId())
                    .setParameter("storeId", storeId)
                    .getSingleResult();

            film.setRentalCount(count);
        }

        return films;
    }

    /**
     * Get top 10 most rented films across all stores.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> getTopTenMostRentedFilms() {
        String jpql = """
            SELECT f.filmId, f.title, f.description, f.releaseYear, f.language.languageId, 
                   f.originalLanguage.languageId, f.rentalDuration, f.rentalRate, f.length, 
                   f.replacementCost, f.rating, f.specialFeatures, f.lastUpdate
            FROM Rental r
            JOIN r.inventory i
            JOIN i.film f
            GROUP BY f.filmId, f.title, f.description, f.releaseYear, f.language.languageId, f.originalLanguage.languageId,
                     f.rentalDuration, f.rentalRate, f.length, f.replacementCost,
                     f.rating, f.specialFeatures, f.lastUpdate
            ORDER BY COUNT(r.rentalId) DESC, f.title ASC
        """;

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setMaxResults(10);

        List<Object[]> results = query.getResultList();
        List<FilmDTO> films = new java.util.ArrayList<>();

        for (Object[] row : results) {
            FilmDTO film = new FilmDTO();
            film.setFilmId(((Number) row[0]).intValue());
            film.setTitle((String) row[1]);
            film.setDescription((String) row[2]);
            film.setReleaseYear((Integer) row[3]);
            film.setLanguageId(row[4] != null ? ((Number) row[4]).intValue() : null);
            film.setOriginalLanguageId(row[5] != null ? ((Number) row[5]).intValue() : null);
            film.setRentalDuration(((Number) row[6]).intValue());
            film.setRentalRate((BigDecimal) row[7]);
            film.setLength((Short) row[8]);
            film.setReplacementCost((BigDecimal) row[9]);
            film.setRating(((com.capgemini.film_rental.entity.enums.Rating) row[10]).toString());
            film.setSpecialFeatures((String) row[11]);
            film.setLastUpdate((LocalDateTime) row[12]);
            films.add(film);
        }

        // Populate rental count for each film
        for (FilmDTO film : films) {
            Long count = entityManager.createQuery("""
                SELECT COUNT(r.rentalId)
                FROM Rental r
                JOIN r.inventory i
                WHERE i.film.filmId = :filmId
            """, Long.class)
                    .setParameter("filmId", film.getFilmId())
                    .getSingleResult();

            film.setRentalCount(count);
        }

        return films;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getDueRentalsByStore(int storeId) {
        String jpql = """
        SELECT r FROM Rental r
        JOIN r.inventory i
        JOIN i.store s
        WHERE s.storeId = :storeId AND r.returnDate IS NULL
    """;

        List<Rental> rentals = entityManager.createQuery(jpql, Rental.class)
                .setParameter("storeId", storeId)
                .getResultList();

        return rentals.stream().map(r -> {
            RentalDTO dto = new RentalDTO();
            dto.setRentalId(r.getRentalId());
            dto.setRentalDate(r.getRentalDate());
            dto.setReturnDate(r.getReturnDate());
            dto.setInventoryId(r.getInventory().getInventoryId());
            dto.setCustomerId(r.getCustomer().getCustomerId());
            dto.setStaffId(r.getStaff().getStaffId());
            return dto;
        }).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RentalDTO> getAll() {
        return repo.findAll().stream().map(r -> {
            RentalDTO dto = new RentalDTO();
            dto.setRentalId(r.getRentalId());
            dto.setRentalDate(r.getRentalDate());
            dto.setReturnDate(r.getReturnDate());
            dto.setInventoryId(r.getInventory().getInventoryId());
            dto.setCustomerId(r.getCustomer().getCustomerId());
            dto.setStaffId(r.getStaff().getStaffId());
            return dto;
        }).toList();
    }

}
