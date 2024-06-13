package com.ajb.exoticrentcar.controller;

import com.ajb.exoticrentcar.entitie.Car;
import com.ajb.exoticrentcar.interfaces.RentService;
import com.ajb.exoticrentcar.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private final CarRepository carRepository;
    private final RentService rentService;

    public CarController(CarRepository carRepository, RentService rentService) {
        this.carRepository = carRepository;
        this.rentService = rentService;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> findAll() {
        try {
            List<Car> cars = carRepository.findAll();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/byBrand")
    public ResponseEntity<List<Car>> findByBrand(@RequestParam String brand) {
        try {
            logger.info(brand);
            List<Car> cars = carRepository.findByBrand(brand);
            logger.info(cars.toString());
            if (cars.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/{id}/available")
    public ResponseEntity<Car> isCarAvailable(@PathVariable Integer id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);

            if (optionalCar.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Car car = optionalCar.get();
            if (!rentService.isCarAvailableForRent(car, startDate, endDate)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ResponseEntity.ok(car);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> findById(@PathVariable Integer id) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);

            return optionalCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cars/create")
    public ResponseEntity<Car> create(@RequestBody Car car) {
        try {
            if (car.getId() != null || car.getMotor() == null || car.getCv() == null || car.getBrand() == null || car.getModel() == null
                    || car.getYearProduction() == null || car.getAbility() == null || car.getPricePerDay() == null || !car.isAvailability() || car.getImage() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Car result = carRepository.save(car);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
