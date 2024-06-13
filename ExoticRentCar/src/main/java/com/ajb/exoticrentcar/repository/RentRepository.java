package com.ajb.exoticrentcar.repository;

import com.ajb.exoticrentcar.entitie.Rent;
import com.ajb.exoticrentcar.entitie.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    List<Rent> findByUser(User user);

    @Query("SELECT r FROM Rent r WHERE r.car.id = :carId AND ((r.startDate BETWEEN :startDate AND :endDate) OR (r.endDate BETWEEN :startDate AND :endDate))")
    List<Rent> findOverlappingRentals(@Param("carId") Integer carId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
