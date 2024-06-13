package com.ajb.exoticrentcar.dtos;

import java.time.LocalDate;

public class RentRequest {
    private final String username;
    private final Integer car;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public RentRequest(String username, Integer car, LocalDate startDate, LocalDate endDate) {
        this.username = username;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public Integer getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "RentRequest{" +
                "username='" + username + '\'' +
                ", car=" + car +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
