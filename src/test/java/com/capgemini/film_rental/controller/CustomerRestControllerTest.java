
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.CustomerCreateDTO;
import com.capgemini.film_rental.dto.CustomerDTO;
import com.capgemini.film_rental.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCustomer() throws Exception {
        CustomerCreateDTO createDTO = new CustomerCreateDTO();
        createDTO.setFirstName("John");
        createDTO.setLastName("Doe");
        createDTO.setEmail("john.doe@example.com");

        CustomerDTO responseDTO = new CustomerDTO();
        responseDTO.setCustomerId(1);
        responseDTO.setFirstName("John");
        responseDTO.setLastName("Doe");
        responseDTO.setEmail("john.doe@example.com");

        Mockito.when(customerService.createCustomer(Mockito.any(CustomerCreateDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/customers/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testAssignStore() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setStoreId(2);

        Mockito.when(customerService.assignStore(1, 2)).thenReturn(dto);

        mockMvc.perform(put("/api/customers/update/1/store")
                        .param("storeId", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.storeId").value(2));
    }

    @Test
    void testByPhone() throws Exception {
        CustomerDTO c1 = new CustomerDTO();
        c1.setFirstName("Alice");
        CustomerDTO c2 = new CustomerDTO();
        c2.setFirstName("Bob");

        List<CustomerDTO> customers = Arrays.asList(c1, c2);
        Mockito.when(customerService.findByPhone("1234567890")).thenReturn(customers);

        mockMvc.perform(get("/api/customers/phone/1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Alice"));
    }

    @Test
    void testByCity() throws Exception {
        CustomerDTO c = new CustomerDTO();
        c.setFirstName("CityUser");

        Mockito.when(customerService.findByCity("Hyderabad")).thenReturn(List.of(c));

        mockMvc.perform(get("/api/customers/city/Hyderabad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("CityUser"));
    }

    @Test
    void testByCountry() throws Exception {
        CustomerDTO c = new CustomerDTO();
        c.setFirstName("CountryUser");

        Mockito.when(customerService.findByCountry("India")).thenReturn(List.of(c));

        mockMvc.perform(get("/api/customers/country/India"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("CountryUser"));
    }

    @Test
    void testAssignAddress() throws Exception {
        CustomerDTO dto = new CustomerDTO();
        dto.setCustomerId(1);
        dto.setAddressId(10);

        Mockito.when(customerService.assignAddress(1, 10)).thenReturn(dto);

        mockMvc.perform(put("/api/customers/1/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.addressId").value(10));
    }

    @Test
    void testByEmail() throws Exception {
        CustomerDTO c = new CustomerDTO();
        c.setEmail("test@example.com");

        Mockito.when(customerService.findByEmail("test@example.com")).thenReturn(List.of(c));

        mockMvc.perform(get("/api/customers/email/test@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("test@example.com"));
    }
}
