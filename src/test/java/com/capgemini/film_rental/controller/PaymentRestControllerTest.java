package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.PaymentCreateDTO;
import com.capgemini.film_rental.dto.aggregates.FilmRevenueDTO;
import com.capgemini.film_rental.dto.aggregates.StoreRevenueByDateDTO;
import com.capgemini.film_rental.service.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentRestControllerTest {

    @Mock
    private IPaymentService paymentService;

    @InjectMocks
    private PaymentRestController paymentRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPayment() {
        PaymentCreateDTO dto = new PaymentCreateDTO();
        dto.setCustomerId(1);
        dto.setStaffId(2);
        dto.setRentalId(3);
        dto.setAmount(BigDecimal.valueOf(100.00));

        when(paymentService.create(dto)).thenReturn("Record Created Successfully");

        String result = paymentRestController.addPayment(dto);

        assertEquals("Record Created Successfully", result);
        verify(paymentService, times(1)).create(dto); // ✅ corrected
    }

    @Test
    void testRevenueFilmsStore() {
        List<FilmRevenueDTO> mockList = Arrays.asList(
                new FilmRevenueDTO(101, BigDecimal.valueOf(200.00)),
                new FilmRevenueDTO(102, BigDecimal.valueOf(150.00))
        );

        when(paymentService.cumulativeRevenueOfAllFilmsByStore(1)).thenReturn(mockList);

        List<FilmRevenueDTO> result = paymentRestController.revenueFilmsStore(1);

        assertEquals(2, result.size());
        assertEquals(101, result.get(0).getFilmId());
        assertEquals(BigDecimal.valueOf(200.00), result.get(0).getAmount()); // ✅ fixed
        assertEquals(102, result.get(1).getFilmId());
        assertEquals(BigDecimal.valueOf(150.00), result.get(1).getAmount()); // ✅ fixed
        verify(paymentService, times(1)).cumulativeRevenueOfAllFilmsByStore(1);
    }

    @Test
    void testRevenueAllStoresDatewise() {
        List<StoreRevenueByDateDTO> mockList = Arrays.asList(
                new StoreRevenueByDateDTO(1, LocalDate.of(2025, 12, 1), BigDecimal.valueOf(500.00))
        );

        when(paymentService.cumulativeRevenueAllStoresDatewise()).thenReturn(mockList);

        List<StoreRevenueByDateDTO> result = paymentRestController.revenueAllStoresDatewise();

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStoreId());
        assertEquals(LocalDate.of(2025, 12, 1), result.get(0).getDate());
        assertEquals(BigDecimal.valueOf(500.00), result.get(0).getAmount()); // ✅ fixed
        verify(paymentService, times(1)).cumulativeRevenueAllStoresDatewise();
    }

    @Test
    void testRevenueByDateForStore() {
        List<StoreRevenueByDateDTO> mockList = Arrays.asList(
                new StoreRevenueByDateDTO(1, LocalDate.of(2025, 12, 2), BigDecimal.valueOf(300.00))
        );

        when(paymentService.cumulativeRevenueByDateForStore(1)).thenReturn(mockList);

        List<StoreRevenueByDateDTO> result = paymentRestController.revenueByDateForStore(1);

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStoreId());
        assertEquals(LocalDate.of(2025, 12, 2), result.get(0).getDate());
        assertEquals(BigDecimal.valueOf(300.00), result.get(0).getAmount()); // ✅ fixed
        verify(paymentService, times(1)).cumulativeRevenueByDateForStore(1);
    }

    @Test
    void testRevenueAllFilmsAcrossStores() {
        List<FilmRevenueDTO> mockList = Arrays.asList(
                new FilmRevenueDTO(201, BigDecimal.valueOf(400.00))
        );

        when(paymentService.cumulativeRevenueOfAllFilmsAcrossStores()).thenReturn(mockList);

        List<FilmRevenueDTO> result = paymentRestController.revenueAllFilmsAcrossStores();

        assertEquals(1, result.size());
        assertEquals(201, result.get(0).getFilmId());
        assertEquals(BigDecimal.valueOf(400.00), result.get(0).getAmount()); // ✅ fixed
        verify(paymentService, times(1)).cumulativeRevenueOfAllFilmsAcrossStores();
    }

    @Test
    void testRevenueForFilm() {
        List<FilmRevenueDTO> mockList = Arrays.asList(
                new FilmRevenueDTO(301, BigDecimal.valueOf(600.00))
        );

        when(paymentService.cumulativeRevenueForFilm(301)).thenReturn(mockList);

        List<FilmRevenueDTO> result = paymentRestController.revenueForFilm(301);

        assertEquals(1, result.size());
        assertEquals(301, result.get(0).getFilmId());
        assertEquals(BigDecimal.valueOf(600.00), result.get(0).getAmount()); // ✅ fixed
        verify(paymentService, times(1)).cumulativeRevenueForFilm(301);
    }
}