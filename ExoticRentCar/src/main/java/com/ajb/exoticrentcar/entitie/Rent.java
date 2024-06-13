package com.ajb.exoticrentcar.entitie;

import com.ajb.exoticrentcar.controller.UserController;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name="rents")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_car")
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer rentalPrice;
    private LocalDateTime dateTimeRental;

    public Rent() {
        this.dateTimeRental = LocalDateTime.now();
    }

    public Rent(Integer id, User user, Car car, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateTimeRental = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getRentalPrice() {
        return rentalPrice;
    }

    public LocalDateTime getDateTimeRental() {
        return dateTimeRental;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setRentalPrice(Integer rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rentalPrice=" + rentalPrice +
                ", dateTimeRental=" + dateTimeRental +
                '}';
    }
}
