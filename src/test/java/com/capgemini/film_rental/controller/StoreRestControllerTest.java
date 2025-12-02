
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.StoreDTO;
import com.capgemini.film_rental.service.IStoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StoreRestControllerTest {

    @Mock
    private IStoreService storeService;

    @InjectMocks
    private StoreRestController storeRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testManagerDetails() {
        StoreDTO.ManagerDetails details = new StoreDTO.ManagerDetails();
        details.firstName = "Praphul";
        details.lastName = "Kumar Singh";
        details.email = "praphul.kumar-singh@capgemini.com";

        when(storeService.managerDetails(1)).thenReturn(details);

        StoreDTO.ManagerDetails result = storeRestController.manager(1);

        assertEquals("Praphul", result.firstName);
        assertEquals("Kumar Singh", result.lastName);
        assertEquals("praphul.kumar-singh@capgemini.com", result.email);
        verify(storeService, times(1)).managerDetails(1);
    }

    @Test
    void testCustomerIds() {
        List<Integer> customerIds = Arrays.asList(101, 102, 103);
        when(storeService.customerIds(1)).thenReturn(customerIds);

        List<Integer> result = storeRestController.customers(1);

        assertEquals(3, result.size());
        assertEquals(101, result.get(0));
        verify(storeService, times(1)).customerIds(1);
    }

    @Test
    void testByCity() {
        StoreDTO dto = new StoreDTO();
        dto.setStoreId(1);
        dto.setAddressId(10);
        dto.setManagerStaffId(100);

        when(storeService.findByCity("Hyderabad")).thenReturn(Arrays.asList(dto));

        List<StoreDTO> result = storeRestController.byCity("Hyderabad");

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getStoreId());
        verify(storeService, times(1)).findByCity("Hyderabad");
    }

    @Test
    void testAssignAddress() {
        StoreDTO dto = new StoreDTO();
        dto.setStoreId(1);
        dto.setAddressId(20);

        when(storeService.assignAddress(1, 20)).thenReturn(dto);

        StoreDTO result = storeRestController.assignAddress(1, 20);

        assertEquals(20, result.getAddressId());
        verify(storeService, times(1)).assignAddress(1, 20);
    }

    @Test
    void testUpdatePhone() {
        StoreDTO dto = new StoreDTO();
        dto.setStoreId(1);

        when(storeService.updatePhone(1, "9999999999")).thenReturn(dto);

        StoreDTO result = storeRestController.updatePhone(1, "9999999999");

        assertEquals(1, result.getStoreId());
        verify(storeService, times(1)).updatePhone(1, "9999999999");
    }

    @Test
    void testManagersOverview() {
        StoreDTO.ManagerAndStoreView view = new StoreDTO.ManagerAndStoreView();
        view.managerFirstName = "Alice";

        when(storeService.managersOverview()).thenReturn(Arrays.asList(view));

        List<StoreDTO.ManagerAndStoreView> result = storeRestController.managersOverview();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).managerFirstName);
        verify(storeService, times(1)).managersOverview();
    }

    @Test
    void testStaffIds() {
        List<Integer> staffIds = Arrays.asList(201, 202);
        when(storeService.staffIds(1)).thenReturn(staffIds);

        List<Integer> result = storeRestController.staffIds(1);

        assertEquals(2, result.size());
        verify(storeService, times(1)).staffIds(1);
    }

    @Test
    void testCreateStore() {
        StoreDTO dto = new StoreDTO();
        dto.setStoreId(1);

        when(storeService.createStore(dto)).thenReturn(dto);

        StoreDTO result = storeRestController.createStore(dto);

        assertEquals(1, result.getStoreId());
        verify(storeService, times(1)).createStore(dto);
    }
}
