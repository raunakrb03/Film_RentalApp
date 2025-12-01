package com.capgemini.film_rental.service;

import com.capgemini.film_rental.dto.PaymentCreateDTO;
import com.capgemini.film_rental.dto.aggregates.FilmRevenueDTO;
import com.capgemini.film_rental.dto.aggregates.StoreRevenueByDateDTO;
import com.capgemini.film_rental.entity.Payment;
import com.capgemini.film_rental.exception.NotFoundException;
import com.capgemini.film_rental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements IPaymentService {

    private final IPaymentRepository repo;
    private final ICustomerRepository custRepo;
    private final IStaffRepository staffRepo;
    private final IRentalRepository rentalRepo;

    public PaymentServiceImpl(IPaymentRepository repo, ICustomerRepository custRepo, IStaffRepository staffRepo, IRentalRepository rentalRepo) {
        this.repo = repo;
        this.custRepo = custRepo;
        this.staffRepo = staffRepo;
        this.rentalRepo = rentalRepo;
    }

    @Override
    public String create(PaymentCreateDTO dto) {
        Payment p = new Payment();
        p.setCustomer(custRepo.findById(dto.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found")));
        p.setStaff(staffRepo.findById(dto.getStaffId()).orElseThrow(() -> new NotFoundException("Staff not found")));
        if (dto.getRentalId() != null)
            p.setRental(rentalRepo.findById(dto.getRentalId()).orElseThrow(() -> new NotFoundException("Rental not found")));
        p.setAmount(dto.getAmount());
        p.setPaymentDate(LocalDateTime.now());
        repo.save(p);
        return "Record Created Successfully";
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsByStore(int storeId) {
        // Validate storeId
        if (storeId <= 0) {
            throw new IllegalArgumentException("Store ID must be greater than 0");
        }

        try {
            // Fetch revenue data from repository
            List<Object[]> results = repo.revenueFilmsByStore(storeId);

            // Check if results are empty
            if (results == null || results.isEmpty()) {
                throw new NotFoundException("No revenue data found for store ID: " + storeId);
            }

            // Convert results to DTOs
            List<FilmRevenueDTO> revenues = results.stream()
                    .map(arr -> {
                        try {
                            int filmId = ((Number) arr[0]).intValue();
                            String filmTitle = (String) arr[1];
                            BigDecimal amount = (BigDecimal) arr[2];
                            return new FilmRevenueDTO(filmId, filmTitle, amount);
                        } catch (ClassCastException e) {
                            throw new RuntimeException("Invalid data format in revenue calculation: " + e.getMessage(), e);
                        }
                    })
                    .collect(Collectors.toList());

            return revenues;
        } catch (IllegalArgumentException | NotFoundException e) {
            // Re-throw validation and not found exceptions
            throw e;
        } catch (Exception e) {
            // Wrap any unexpected exceptions
            throw new RuntimeException("Error calculating cumulative revenue for store ID: " + storeId, e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsAcrossStores() {
        try {
            // Fetch revenue data from repository
            List<Object[]> results = repo.revenueAllFilmsAcrossStores();

            // Check if results are empty
            if (results == null || results.isEmpty()) {
                throw new NotFoundException("No revenue data found across all films and stores");
            }

            // Convert results to DTOs
            List<FilmRevenueDTO> revenues = results.stream()
                    .map(arr -> {
                        try {
                            int filmId = ((Number) arr[0]).intValue();
                            String filmTitle = (String) arr[1];
                            BigDecimal amount = (BigDecimal) arr[2];
                            return new FilmRevenueDTO(filmId, filmTitle, amount);
                        } catch (ClassCastException e) {
                            throw new RuntimeException("Invalid data format in revenue calculation: " + e.getMessage(), e);
                        }
                    })
                    .collect(Collectors.toList());

            return revenues;
        } catch (NotFoundException e) {
            // Re-throw not found exceptions
            throw e;
        } catch (Exception e) {
            // Wrap any unexpected exceptions
            throw new RuntimeException("Error calculating cumulative revenue for all films across stores", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<StoreRevenueByDateDTO> cumulativeRevenueAllStoresDatewise() {
        try {
            // Fetch revenue data from repository
            List<Object[]> results = repo.revenueAllStoresDatewise();

            // Check if results are empty
            if (results == null || results.isEmpty()) {
                throw new NotFoundException("No revenue data found across all stores and dates");
            }

            // Convert results to DTOs
            List<StoreRevenueByDateDTO> revenues = results.stream()
                    .map(arr -> {
                        try {
                            int storeId = ((Number) arr[0]).intValue();
                            java.time.LocalDate date = (java.time.LocalDate) arr[1];
                            BigDecimal amount = (BigDecimal) arr[2];
                            return new StoreRevenueByDateDTO(storeId, date, amount);
                        } catch (ClassCastException e) {
                            throw new RuntimeException("Invalid data format in revenue calculation: " + e.getMessage(), e);
                        }
                    })
                    .collect(Collectors.toList());

            return revenues;
        } catch (NotFoundException e) {
            // Re-throw not found exceptions
            throw e;
        } catch (Exception e) {
            // Wrap any unexpected exceptions
            throw new RuntimeException("Error calculating cumulative revenue for all stores datewise", e);
        }
        return repo.revenueAllStoresDatewise().stream()
                .map(arr -> new StoreRevenueByDateDTO(((Number) arr[0]).intValue(), ((java.sql.Date) arr[1]).toLocalDate(), (BigDecimal) arr[2]))
                .collect(Collectors.toList());
    }


    @Override
    public List<StoreRevenueByDateDTO> cumulativeRevenueByDateForStore(int storeId) {
        return repo.revenueByDateForStore(storeId).stream()
                .map(arr -> new StoreRevenueByDateDTO(storeId, ((java.sql.Date) arr[0]).toLocalDate(), (BigDecimal) arr[1]))
                .collect(Collectors.toList());
    }
}