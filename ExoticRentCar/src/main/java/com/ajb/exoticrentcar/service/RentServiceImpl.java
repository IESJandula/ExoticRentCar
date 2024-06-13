package com.ajb.exoticrentcar.service;

import com.ajb.exoticrentcar.entitie.Car;
import com.ajb.exoticrentcar.entitie.Rent;
import com.ajb.exoticrentcar.interfaces.RentService;
import com.ajb.exoticrentcar.repository.CarRepository;
import com.ajb.exoticrentcar.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
@Service
public class RentServiceImpl implements RentService {
    private final RentRepository rentRepository;
    private final CarRepository carRepository;

    public RentServiceImpl(RentRepository rentRepository, CarRepository carRepository) {
        this.rentRepository = rentRepository;
        this.carRepository = carRepository;
    }

    @Override
    public boolean isCarAvailableForRent(Car car, LocalDate startDate, LocalDate endDate) {
        // Lógica para verificar la disponibilidad del coche
        List<Rent> overlappingRentals = rentRepository.findOverlappingRentals(car.getId(), startDate, endDate);
        return overlappingRentals.isEmpty();
    }

    @Override
    public List<Car> getAvailableCarsForRent(LocalDate startDate, LocalDate endDate) {
        List<Car> allCars = carRepository.findAll();
        List<Car> availableCars = new ArrayList<>();

        for (Car car : allCars) {
            // Lógica para verificar la disponibilidad del coche
            List<Rent> overlappingRentals = rentRepository.findOverlappingRentals(car.getId(), startDate, endDate);

            if (overlappingRentals.isEmpty()) {
                // El coche está disponible, agrégalo a la lista de coches disponibles
                availableCars.add(car);
            }
        }

        return availableCars;
    }

    @Override
    public int calculateRentalPrice(Rent rent) {
        if (rent.getCar() != null && rent.getStartDate() != null && rent.getEndDate() != null) {
            long differenceInDays = ChronoUnit.DAYS.between(rent.getStartDate(), rent.getEndDate());
            differenceInDays = Math.max(differenceInDays, 0);

            Car car = carRepository.findById(rent.getCar().getId()).orElse(null);

            if (car != null && car.getPricePerDay() != null) {
                return car.getPricePerDay() * Math.toIntExact(differenceInDays);
            }
        }
        return 0;
    }
}


