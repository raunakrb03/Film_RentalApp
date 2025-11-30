package com.capgemini.film_rental.dtoConverter;


import com.capgemini.film_rental.dto.RentalDTO;
import com.capgemini.film_rental.entity.Rental;
import com.capgemini.film_rental.entity.Customer;
import com.capgemini.film_rental.entity.Inventory;
import com.capgemini.film_rental.entity.Staff;

public class RentalConverter {

    public static Rental toEntity(RentalDTO dto, Inventory inventory, Customer customer, Staff staff) {
        Rental rental = new Rental();
        rental.setRentalDate(dto.getRentalDate());
        rental.setReturnDate(dto.getReturnDate());
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        return rental;
    }
}
