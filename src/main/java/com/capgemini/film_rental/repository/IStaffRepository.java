package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStaffRepository extends JpaRepository<Staff,Integer> {
}
