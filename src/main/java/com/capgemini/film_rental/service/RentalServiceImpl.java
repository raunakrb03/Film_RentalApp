
package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.dtoConverter.RentalConverter;
import com.capgemini.film_rental.entity.Customer;
import com.capgemini.film_rental.entity.Inventory;
import com.capgemini.film_rental.entity.Rental;
import com.capgemini.film_rental.entity.Staff;
import com.capgemini.film_rental.repository.ICustomerRepository;
import com.capgemini.film_rental.repository.IInventoryRepository;
import com.capgemini.film_rental.repository.IRentalRepository;
import com.capgemini.film_rental.repository.IStaffRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalServiceImpl implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepo;

    @Autowired
    private IInventoryRepository inventoryRepo;

    @Autowired
    private ICustomerRepository customerRepo;

    @Autowired
    private IStaffRepository staffRepo;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Rent a film using DTO and convert to entity.
     */
    @Override
    public Rental rentFilm(RentalDTO dto) {
        Inventory inventory = inventoryRepo.findById(dto.getInventoryId())
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Staff staff = staffRepo.findById(dto.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Rental rental = RentalConverter.toEntity(dto, inventory, customer, staff);
        rental.setLastUpdate(LocalDateTime.now());

        return rentalRepo.save(rental);
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
