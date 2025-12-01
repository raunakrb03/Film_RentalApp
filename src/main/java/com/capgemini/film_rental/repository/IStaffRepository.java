package com.capgemini.film_rental.repository;

import com.capgemini.film_rental.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IStaffRepository extends JpaRepository<Staff,Integer> {
    @Query("select s from Staff s where s.address.phone = :phone")
    List<Staff> findByPhone(@Param("phone") String phone);

    @Query("select s from Staff s where lower(s.address.city.country.country) = lower(:country)")
    List<Staff> findByCountry(@Param("country") String country);

    @Query("select s from Staff s where lower(s.firstName) like lower(concat('%',:fn,'%'))")
    List<Staff> findByFirstNameContainingIgnoreCase(@Param("fn") String fn);

    @Query("select s from Staff s where lower(s.lastName) like lower(concat('%',:ln,'%'))")
    List<Staff> findByLastNameContainingIgnoreCase(@Param("ln") String ln);

    @Query("select s from Staff s where lower(s.email) = lower(:email)")
    List<Staff> findByEmail(@Param("email") String email);
}
