
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.FilmInventoryCountDTO;
import com.capgemini.film_rental.dto.InventoryAddDTO;
import com.capgemini.film_rental.dto.aggregates.FilmInventoryByStoreDTO;
import com.capgemini.film_rental.service.IInventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InventoryRestControllerTest {

    @Mock
    private IInventoryService inventoryService;

    @InjectMocks
    private InventoryRestController inventoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    void testInventoryOfFilmInStore() {
        FilmInventoryByStoreDTO dto = new FilmInventoryByStoreDTO(1, 10L);
        when(inventoryService.inventoryOfFilmInStore(101, 1)).thenReturn(dto);

        FilmInventoryByStoreDTO result = inventoryRestController.inventoryOfFilmInStore(101, 1);

        assertEquals(1, result.getStoreId());
        assertEquals(10L, result.getInventoryCount());
        verify(inventoryService, times(1)).inventoryOfFilmInStore(101, 1);
    }

    @Test
    void testAddFilmToStore() {
        InventoryAddDTO dto = new InventoryAddDTO();
        dto.setFilmId(101);
        dto.setStoreId(1);

        when(inventoryService.addFilmToStore(dto)).thenReturn("Inventory record created successfully with ID: 500");

        String result = inventoryRestController.addFilmToStore(dto);

        assertEquals("Inventory record created successfully with ID: 500", result);
        verify(inventoryService, times(1)).addFilmToStore(dto);
    }

    @Test
    void testGetAllFilmsInventory() {
        List<FilmInventoryCountDTO> mockList = Arrays.asList(
                new FilmInventoryCountDTO(101, "Film A", 0, 20L),
                new FilmInventoryCountDTO(102, "Film B", 0, 15L)
        );

        when(inventoryService.getAllFilmsInventoryCounts()).thenReturn(mockList);

        List<FilmInventoryCountDTO> result = inventoryRestController.getAllFilmsInventory();

        assertEquals(2, result.size());
        assertEquals("Film A", result.get(0).getFilmTitle());
        verify(inventoryService, times(1)).getAllFilmsInventoryCounts();
    }

    @Test
    void testInventoryOfFilmAcrossStores() {
        List<FilmInventoryByStoreDTO> mockList = Arrays.asList(
                new FilmInventoryByStoreDTO(1, 10L),
                new FilmInventoryByStoreDTO(2, 5L)
        );

        when(inventoryService.inventoryOfFilmAcrossStores(101)).thenReturn(mockList);

        List<FilmInventoryByStoreDTO> result = inventoryRestController.inventoryOfFilmAcrossStores(101);

        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getStoreId());
        verify(inventoryService, times(1)).inventoryOfFilmAcrossStores(101);
    }

    @Test
    void testInventoryByStore() {
        List<FilmInventoryCountDTO> mockList = Arrays.asList(
                new FilmInventoryCountDTO(101, "Film A", 1, 10L),
                new FilmInventoryCountDTO(102, "Film B", 1, 8L)
        );

        when(inventoryService.inventoryByStore(1)).thenReturn(mockList);

        List<FilmInventoryCountDTO> result = inventoryRestController.inventoryByStore(1);

        assertEquals(2, result.size());
        assertEquals("Film A", result.get(0).getFilmTitle());
        verify(inventoryService, times(1)).inventoryByStore(1);
    }
}
