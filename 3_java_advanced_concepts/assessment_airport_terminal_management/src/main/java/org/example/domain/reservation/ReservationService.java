package org.example.domain.reservation;

import org.example.data.FlightRepository;
import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationService {
    Map<String, List<Passenger>> reservations;
    private final FlightRepository flightRepository;

    public ReservationService() {
        reservations = new HashMap<>();
        this.flightRepository = new FlightRepository();
    }


    public Map<String, List<Passenger>> getReservations() {
        return new HashMap<>(reservations);
    }

    public void loadReservations(Map<String, List<Passenger>> loadedReservations) {
        this.reservations.clear();
        this.reservations.putAll(loadedReservations);
    }

    public List<Flight> getAvailableFlights() {
        return flightRepository.loadFlights();
    }

    public boolean isValidFlight(String flightNumber) {
        List<Flight> flights = flightRepository.loadFlights();
        return flights.stream()
                .anyMatch(flight -> flight.getFlightNumber().equalsIgnoreCase(flightNumber));
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.loadFlights().stream()
                .filter(flight -> flight.getFlightNumber().equals(flightNumber))
                .findFirst()
                .orElse(null);
    }

    public void addReservation(String flightNumber, Passenger passenger) {
        if (reservations.containsKey(flightNumber)) {
            List<Passenger> passengersInFlight = reservations.get(flightNumber);
            passengersInFlight.add(passenger);
        } else {
            List<Passenger> passengersInFlight = new ArrayList<>();
            passengersInFlight.add(passenger);
            reservations.put(flightNumber, passengersInFlight);
        }
    }

    public List<Passenger> getPassengersForFlight(String flightNumber) {
        List<Passenger> passengersInFlight = reservations.get(flightNumber);
        if (!reservations.containsKey(flightNumber)) {
            return new ArrayList<>();
        }
        return passengersInFlight;
    }

    public boolean removeReservation(String flightNumber, Passenger passenger) {
        if (reservations.isEmpty() || !reservations.containsKey(flightNumber)) {
            return false;
        }
        List<Passenger> passengers = reservations.get(flightNumber);
        if (!passengers.contains(passenger)) {
            return false;
        }
        passengers.remove(passenger);
        if (passengers.isEmpty()) {
            reservations.remove(flightNumber);
        }
        return true;
    }
}
