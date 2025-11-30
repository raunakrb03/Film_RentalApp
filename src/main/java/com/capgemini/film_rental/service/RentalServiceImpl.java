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

    /**
     * Get top 10 most rented films for a given store.
     */
    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> getTopTenFilmsByStore(Integer storeId) {
        if (storeId == null || storeId <= 0) {
            throw new IllegalArgumentException("storeId must be a positive integer");
        }

        String jpql = """
            SELECT new com.capgemini.film_rental.dto.FilmDTO(
                f.filmId,
                f.title,
                f.description,
                f.releaseYear,
                l.name,
                ol.name,
                f.rentalDuration,
                f.rentalRate,
                f.length,
                f.replacementCost,
                f.rating,
                f.specialFeatures,
                f.lastUpdate,
                null
            )
            FROM Rental r
            JOIN r.inventory i
            JOIN i.film f
            JOIN i.store s
            JOIN f.language l
            LEFT JOIN f.originalLanguage ol
            WHERE s.storeId = :storeId
            GROUP BY f.filmId, f.title, f.description, f.releaseYear, l.name, ol.name,
                     f.rentalDuration, f.rentalRate, f.length, f.replacementCost,
                     f.rating, f.specialFeatures, f.lastUpdate
            ORDER BY COUNT(r.rentalId) DESC, f.title ASC
        """;

        TypedQuery<FilmDTO> query = entityManager.createQuery(jpql, FilmDTO.class);
        query.setParameter("storeId", storeId);
        query.setMaxResults(10);

        List<FilmDTO> films = query.getResultList();

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
}
