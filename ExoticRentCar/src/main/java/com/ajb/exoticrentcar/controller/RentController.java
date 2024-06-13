package com.ajb.exoticrentcar.controller;

import com.ajb.exoticrentcar.dtos.RentRequest;
import com.ajb.exoticrentcar.entitie.Car;
import com.ajb.exoticrentcar.entitie.Rent;
import com.ajb.exoticrentcar.entitie.User;
import com.ajb.exoticrentcar.interfaces.RentService;
import com.ajb.exoticrentcar.repository.CarRepository;
import com.ajb.exoticrentcar.repository.RentRepository;
import com.ajb.exoticrentcar.repository.UserRepository;
import com.ajb.exoticrentcar.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RentController {
    private final RentRepository rentRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RentService rentService;

    public RentController(RentRepository rentRepository, UserRepository userRepository, CarRepository carRepository1, RentService rentService) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository1;
        this.rentService = rentService;
    }

    @GetMapping("/rents")
    public ResponseEntity<List<Rent>> findAll() {
        try {
            List<Rent> rents = rentRepository.findAll();
            return ResponseEntity.ok(rents);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rentsFilter")
    public ResponseEntity<List<Rent>> findAllFilter(Authentication authentication) {
        try {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);

            // Verificar que el usuario autenticado no sea nulo
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            // Obtener todos los alquileres del usuario autenticado
            List<Rent> rents = rentRepository.findByUser(user);

            return ResponseEntity.ok(rents);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/rent/create")
    public ResponseEntity<Rent> createRent(@RequestBody RentRequest rentRequest) {
        try {
            if (rentRequest.getUsername() == null || rentRequest.getCar() == null
                    || rentRequest.getStartDate() == null || rentRequest.getEndDate() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // Obtener el usuario y el coche de la base de datos
            User user = userRepository.findByUsername(rentRequest.getUsername());
            Optional<Car> optionalCar = carRepository.findById(rentRequest.getCar());

            // Verificar si el usuario y el coche existen
            if (user == null || optionalCar.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Car car = optionalCar.get();

            // Crear una nueva instancia de Rent con los datos proporcionados
            Rent rent = new Rent();
            rent.setUser(user);
            rent.setCar(car);
            rent.setStartDate(rentRequest.getStartDate());
            rent.setEndDate(rentRequest.getEndDate());

            // Verifica si el coche está disponible para alquiler en el rango de fechas
            if (!rentService.isCarAvailableForRent(rent.getCar(), rent.getStartDate(), rent.getEndDate())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT); // El coche no está disponible
            }

            rent.setRentalPrice(rentService.calculateRentalPrice(rent));

            Rent result = rentRepository.save(rent);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
