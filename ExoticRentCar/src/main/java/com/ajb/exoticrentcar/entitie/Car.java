package com.ajb.exoticrentcar.entitie;

import jakarta.persistence.*;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String motor;
    private Integer cv;
    private String brand;
    private String model;
    private Integer yearProduction;
    private Integer ability;
    private Integer pricePerDay;
    private boolean availability;
    private String image;

    public Car() {
        this.availability = true;
    }

    public Car(Integer id, String motor, Integer cv, String brand, String model, Integer yearProduction, Integer ability, Integer pricePerDay, boolean availability, String image) {
        this.id = id;
        this.motor = motor;
        this.cv = cv;
        this.brand = brand;
        this.model = model;
        this.yearProduction = yearProduction;
        this.ability = ability;
        this.pricePerDay = pricePerDay;
        this.availability = availability;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getMotor() {
        return motor;
    }

    public Integer getCv() {
        return cv;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYearProduction() {
        return yearProduction;
    }

    public Integer getAbility() {
        return ability;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailability() {
        return availability;
    }

    public String getImage() {
        return image;
    }

    public void setCv(Integer cv) {
        this.cv = cv;
    }

    public void setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", motor='" + motor + '\'' +
                ", cv=" + cv +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", yearProduction=" + yearProduction +
                ", ability=" + ability +
                ", pricePerDay=" + pricePerDay +
                ", availability=" + availability +
                ", image='" + image + '\'' +
                '}';
    }
}
