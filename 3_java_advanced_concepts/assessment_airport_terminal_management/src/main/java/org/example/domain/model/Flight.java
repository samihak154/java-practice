package org.example.domain.model;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Flight {
    private String flightNumber;
    private LocalDate departureDate;
    private String destination;
    private BigDecimal ticketPrice;
    private Aircraft aircraft;

    public Flight(String flightNumber, LocalDate departureDate, String destination, BigDecimal ticketPrice, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.destination = destination;
        this.ticketPrice = ticketPrice;
        this.aircraft = aircraft;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public String getDestination() {
        return destination;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    @Override
    public String toString() {
        return String.format("%-9s%-19s%-16s%-16s%-16s$%-11.2f",
                flightNumber,
                aircraft.getAirlineInfo(),
                aircraft.getModel(),
                departureDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")),
                destination,
                ticketPrice);
    }

    public String toStringNoPrice() {
        return String.format("%-9s%-19s%-16s%-16s%-16s",
                flightNumber,
                aircraft.getAirlineInfo(),
                aircraft.getModel(),
                departureDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")),
                destination);
    }
}

