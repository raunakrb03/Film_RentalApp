
package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.StaffCreateDTO;
import com.capgemini.film_rental.dto.StaffDTO;
import com.capgemini.film_rental.service.IStaffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class StaffRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IStaffService staffService;

    @InjectMocks
    private StaffRestController staffRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(staffRestController).build();
    }

    @Test
    void testCreateStaff() throws Exception {
        StaffDTO responseDTO = new StaffDTO();
        responseDTO.setStaffId(1);
        responseDTO.setFirstName("John");
        responseDTO.setLastName("Doe");

        when(staffService.create(any(StaffCreateDTO.class))).thenReturn("Record Created Successfully");

        String jsonRequest = """
                {
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john.doe@example.com",
                  "username": "johndoe",
                  "password": "password123"
                }
                """;

        mockMvc.perform(post("/api/staff/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("Record Created Successfully"));
    }

    @Test
    void testUpdateLastName() throws Exception {
        StaffDTO dto = new StaffDTO();
        dto.setStaffId(1);
        dto.setLastName("Smith");

        when(staffService.updateLastName(eq(1), eq("Smith"))).thenReturn(dto);

        mockMvc.perform(put("/api/staff/update/ln/1")
                        .param("ln", "Smith"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void testFindByPhone() throws Exception {
        StaffDTO dto1 = new StaffDTO();
        dto1.setStaffId(1);
        dto1.setFirstName("Alice");

        StaffDTO dto2 = new StaffDTO();
        dto2.setStaffId(2);
        dto2.setFirstName("Bob");

        List<StaffDTO> staffList = Arrays.asList(dto1, dto2);

        when(staffService.findByPhone("1234567890")).thenReturn(staffList);

        mockMvc.perform(get("/api/staff/phone/1234567890"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Alice"))
                .andExpect(jsonPath("$[1].firstName").value("Bob"));
    }
}
