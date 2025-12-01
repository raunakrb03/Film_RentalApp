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
    public List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsByStore(int storeId) {
        return repo.revenueFilmsByStore(storeId).stream()
                .map(arr -> new FilmRevenueDTO(((Number) arr[0]).intValue(), (BigDecimal) arr[1]))
                .collect(Collectors.toList());
    }

    @Override
    public List<FilmRevenueDTO> cumulativeRevenueOfAllFilmsAcrossStores() {
        return repo.revenueAllFilmsAcrossStores().stream()
                .map(arr -> new FilmRevenueDTO(((Number) arr[0]).intValue(), (BigDecimal) arr[1]))
                .collect(Collectors.toList());
    }

    @Override
    public List<StoreRevenueByDateDTO> cumulativeRevenueAllStoresDatewise() {
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