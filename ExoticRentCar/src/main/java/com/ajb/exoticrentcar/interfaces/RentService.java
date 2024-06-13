package com.ajb.exoticrentcar.interfaces;

import com.ajb.exoticrentcar.entitie.Car;
import com.ajb.exoticrentcar.entitie.Rent;

import java.time.LocalDate;
import java.util.List;

public interface RentService {
    boolean isCarAvailableForRent(Car car, LocalDate startDate, LocalDate endDate);
    List<Car> getAvailableCarsForRent(LocalDate startDate, LocalDate endDate);
    int calculateRentalPrice(Rent rent);
}
