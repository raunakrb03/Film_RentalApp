package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment,Integer> {
}
