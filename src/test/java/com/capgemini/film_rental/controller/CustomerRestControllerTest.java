package com.capgemini.film_rental.controller;

import com.capgemini.film_rental.dto.CustomerDTO;
import com.capgemini.film_rental.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
public class CustomerRestControllerTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICustomerService customerService;

    @Test
    public void testGetCustomerByFirstName() throws Exception
    {
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("John");
        customer2.setLastName("Smith");

        List<CustomerDTO> customers = Arrays.asList(customer1, customer2);

        Mockito.when(customerService.findByFirstName("John")).thenReturn(customers);

        mockMvc.perform(get("/api/customers/firstname/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].lastName").value("Smith"));
    }

    @Test
    public void testGetCustomerByEmail() throws Exception
    {
        CustomerDTO customer = new CustomerDTO();
        customer.setEmail("jane.doe@example.com");
        customer.setFirstName("Jane");

        List<CustomerDTO> result = Arrays.asList(customer);

        Mockito.when(customerService.findByEmail("jane.doe@example.com")).thenReturn(result);

        mockMvc.perform(get("/api/customers/email/jane.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].email").value("jane.doe@example.com"))
                .andExpect(jsonPath("$[0].firstName").value("Jane"));
    }
}
