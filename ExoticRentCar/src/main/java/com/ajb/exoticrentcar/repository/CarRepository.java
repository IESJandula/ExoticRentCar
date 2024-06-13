package com.ajb.exoticrentcar.repository;

import com.ajb.exoticrentcar.entitie.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByBrand(String brand);
}
