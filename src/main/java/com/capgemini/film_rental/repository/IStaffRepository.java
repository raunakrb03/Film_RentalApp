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

    // Use nested concat to avoid 3-arg concat (JPQL supports two-arg concat only)
    @Query("select s from Staff s where lower(s.firstName) like lower(concat(concat('%',:fn), '%'))")
    List<Staff> findByFirstNameContainingIgnoreCase(@Param("fn") String fn);



    // Use nested concat to avoid 3-arg concat (JPQL supports two-arg concat only)
    @Query("select s from Staff s where lower(s.lastName) like lower(concat(concat('%',:ln), '%'))")
    List<Staff> findByLastName(@Param("ln") String ln);


    @Query("select s from Staff s where lower(s.email) = lower(:email)")
    List<Staff> findByEmail(@Param("email") String email);


    @Query("select s from Staff s where lower(s.address.city.city) = lower(:city)")
    List<Staff> findByCity(@Param("city") String city);

}
