package com.capgemini.film_rental.service;
import com.capgemini.film_rental.dto.FilmDTO;
import com.capgemini.film_rental.entity.Rental;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RentalServiceImpl implements IRentalService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Rental rentFilm(Rental rental) {
        entityManager.persist(rental);
        return rental;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDTO> getTopTenFilmsByStore(Integer storeId) {
        if (storeId == null || storeId <= 0) {
            throw new IllegalArgumentException("storeId must be a positive integer");
        }

        // JPQL query to fetch FilmDTO with basic details
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

        // Populate rentalCount for each film
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
