package org.example;

import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;
import org.example.domain.reservation.ReservationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {
    ReservationService reservations = new ReservationService();

    @Test
    @DisplayName("Empty HashMap initialized")
    public void hashMapInitializeAsEmpty() {
        Map<String, List<Passenger>> testReservations = reservations.getReservations();

        assertTrue(testReservations.isEmpty());
    }

    @Test
    @DisplayName("Returns copy of reservations")
    public void getReservationsReturnsListOfReservations() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");
        reservations.addReservation("A240", passenger1);

        Map<String, List<Passenger>> testReservations = reservations.getReservations();

        assertEquals(1, testReservations.get("A240").size());
        assertTrue(testReservations.containsKey("A240"));
        assertTrue(testReservations.get("A240").contains(passenger1));
    }

    @Test
    @DisplayName("Load reservations from external data")
    public void loadReservationsFromExternalData() {
        Map<String, List<Passenger>> externalData = new HashMap<>();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("External Passenger", "E12345678"));
        externalData.put("EX123", passengers);

        reservations.loadReservations(externalData);
        Map<String, List<Passenger>> loadedReservations = reservations.getReservations();

        assertTrue(loadedReservations.containsKey("EX123"));
        assertEquals(1, loadedReservations.get("EX123").size());
        assertEquals("External Passenger", loadedReservations.get("EX123").get(0).getName());
    }

    @Test
    @DisplayName("Load reservations replaces existing data")
    public void loadReservationsReplacesExistingData() {
        reservations.addReservation("INITIAL", new Passenger("Initial Passenger", "I12345678"));

        Map<String, List<Passenger>> newData = new HashMap<>();
        List<Passenger> passengers = new ArrayList<>();
        passengers.add(new Passenger("New Passenger", "N12345678"));
        newData.put("NEW123", passengers);

        reservations.loadReservations(newData);

        Map<String, List<Passenger>> result = reservations.getReservations();
        assertFalse(result.containsKey("INITIAL"));
        assertTrue(result.containsKey("NEW123"));
        assertEquals(1, result.size());
    }

    @Test
    @DisplayName("Get available flights returns flight list")
    public void getAvailableFlightsReturnsFlightList() {
        List<Flight> flights = reservations.getAvailableFlights();

        assertEquals(5, flights.size());

        assertTrue(flights.stream().anyMatch(flight -> flight.getFlightNumber().equals("UA520")));
        assertTrue(flights.stream().anyMatch(flight -> flight.getFlightNumber().equals("AA240")));
    }

    @Test
    @DisplayName("Valid flight number returns true")
    public void isValidFlightReturnsTrueForValidFlight() {
        assertTrue(reservations.isValidFlight("UA520"));
        assertTrue(reservations.isValidFlight("AA240"));
        assertTrue(reservations.isValidFlight("DL2618"));
    }

    @Test
    @DisplayName("Invalid flight number returns false")
    public void isValidFlightReturnsFalseForInvalidFlight() {
        assertFalse(reservations.isValidFlight("INVALID123"));
        assertFalse(reservations.isValidFlight("FAKE456"));
        assertFalse(reservations.isValidFlight(""));
    }

    @Test
    @DisplayName("Get flight by number returns correct flight")
    public void getFlightByNumberReturnsCorrectFlight() {
        Flight flight = reservations.getFlightByNumber("UA520");

        assertEquals("UA520", flight.getFlightNumber());
        assertEquals("Paris", flight.getDestination());
    }

    @Test
    @DisplayName("Get flight by number returns null for invalid flight")
    public void getFlightByNumberReturnsNullForInvalidFlight() {
        Flight flight = reservations.getFlightByNumber("INVALID123");

        assertTrue(flight == null);
    }

    @Test
    @DisplayName("Adds passenger to new flight")
    public void addPassengerToNewFlight() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");
        reservations.addReservation("A240", passenger1);

        Map<String, List<Passenger>> testReservations = reservations.getReservations();

        assertEquals(1, testReservations.get("A240").size());
        assertTrue(testReservations.containsKey("A240"));
    }

    @Test
    @DisplayName("Adds multiple passengers to same flight")
    public void addMultiplePassengersToSameFlight() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");
        Passenger passenger2 = new Passenger("Pumbies", "A87654321");

        reservations.addReservation("A240", passenger1);
        reservations.addReservation("A240", passenger2);

        Map<String, List<Passenger>> testReservations = reservations.getReservations();

        assertEquals(2, testReservations.get("A240").size());
        assertTrue(testReservations.get("A240").contains(passenger1));
        assertTrue(testReservations.get("A240").contains(passenger2));
    }

    @Test
    @DisplayName("Get all passengers for a flight")
    public void showAllPassengersInFlightGivenFlightNumber() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");
        Passenger passenger2 = new Passenger("Pumbies", "A87654321");

        reservations.addReservation("A240", passenger1);
        reservations.addReservation("A240", passenger2);

        List<Passenger> testlist = reservations.getPassengersForFlight("A240");

        assertEquals(2, testlist.size());
    }

    @Test
    @DisplayName("Remove passenger from ArrayList")
    public void removePassengerFromArrayList() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");
        Passenger passenger2 = new Passenger("Pumbies", "A87654321");

        reservations.addReservation("A240", passenger1);
        reservations.addReservation("A240", passenger2);
        reservations.removeReservation("A240", passenger1);

        List<Passenger> testlist = reservations.getPassengersForFlight("A240");

        assertEquals(1, testlist.size());
        assertFalse(testlist.contains(passenger1));
        assertEquals(passenger2, testlist.get(0));
    }

    @Test
    @DisplayName("Remove passenger from flight that doesn't exist")
    public void cantRemovePassengerFromNonExistentFlight() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");

        reservations.addReservation("A240", passenger1);
        reservations.removeReservation("A300", passenger1);

        List<Passenger> testlist = reservations.getPassengersForFlight("A240");

        assertEquals(passenger1, testlist.get(0));
    }

    @Test
    @DisplayName("Remove passenger that isn't found")
    public void cantRemovePassengerNotOnList() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");

        boolean result = reservations.removeReservation("A240", passenger1);

        assertFalse(result);
    }

    @Test
    @DisplayName("Remove last passenger removes flight from reservations")
    public void removeLastPassengerRemovesFlight() {
        Passenger passenger1 = new Passenger("Hectorini", "A12345678");

        reservations.addReservation("A240", passenger1);
        boolean result = reservations.removeReservation("A240", passenger1);

        Map<String, List<Passenger>> testReservations = reservations.getReservations();

        assertTrue(result);
        assertFalse(testReservations.containsKey("A240"));
    }
}
