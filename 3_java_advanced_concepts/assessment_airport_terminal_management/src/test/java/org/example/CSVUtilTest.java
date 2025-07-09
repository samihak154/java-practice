package org.example;

import org.example.data.CSVUtil;
import org.example.domain.model.CommercialAircraft;
import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CSVUtilTest {
    private static final String TEST_FILE_PATH = "data/test_reservations.csv";
    private static final String SEED_FILE_PATH = "data/seed_reservations.csv";
    private CSVUtil csvUtil = new CSVUtil(TEST_FILE_PATH);

    @BeforeEach
    void setupTest() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void saveReservationShouldAddNewReservation() {
        CommercialAircraft aircraft = new CommercialAircraft("Boeing 777", 300, 50000, "American Airlines");
        Flight newFlight = new Flight("TEST123", LocalDate.of(2025, 10, 23), "Lima", new BigDecimal("704.00"), aircraft);
        Passenger newPassenger = new Passenger("NEW PASSENGER", "A00009999");

        csvUtil.saveReservation(newFlight, newPassenger);

        Map<String, List<Passenger>> reservations = csvUtil.loadReservations();
        List<Passenger> testFlightPassengers = reservations.get("TEST123");

        assertEquals(1, testFlightPassengers.size());
        assertEquals("NEW PASSENGER", testFlightPassengers.get(0).getName());
        assertEquals("A00009999", testFlightPassengers.get(0).getPassportNumber());
    }

    @Test
    void loadReservationsShouldLoadCorrectData() {
        Map<String, List<Passenger>> reservations = csvUtil.loadReservations();

        assertEquals(3, reservations.size());
        assertTrue(reservations.containsKey("UA520"));

        List<Passenger> ua520Passengers = reservations.get("UA520");

        assertEquals(2, ua520Passengers.size());
        assertEquals("SAMIHA KHALED", ua520Passengers.get(0).getName());
        assertEquals("A00008562", ua520Passengers.get(0).getPassportNumber());
    }

    @Test
    void deleteReservationShouldRemoveSpecificPassenger() {
        Flight flight = new Flight("UA520", null, null, null, null); // Only flight number matters
        Passenger passenger = new Passenger("HECTOR FERNANDEZ", "A00006283");

        csvUtil.deleteReservation(flight, passenger);

        Map<String, List<Passenger>> reservations = csvUtil.loadReservations();
        List<Passenger> ua520Passengers = reservations.get("UA520");
        
        assertEquals(1, ua520Passengers.size());
    }

    @Test
    void deleteReservationShouldReturnFalseWhenPassengerNotFound() {
        Flight flight = new Flight("UA520", null, null, null, null);
        Passenger nonExistentPassenger = new Passenger("DOES NOT EXIST", "A00000000");

        csvUtil.deleteReservation(flight, nonExistentPassenger);

        Map<String, List<Passenger>> reservations = csvUtil.loadReservations();
        List<Passenger> ua520Passengers = reservations.get("UA520");

        assertEquals(2, ua520Passengers.size());
    }

    @Test
    void deleteReservationShouldHandleNonExistentFlight() {
        Flight nonExistentFlight = new Flight("FAKEFLIGHT123", null, null, null, null);
        Passenger passenger = new Passenger("SOME PASSENGER", "A00000000");

        csvUtil.deleteReservation(nonExistentFlight, passenger);

        Map<String, List<Passenger>> reservations = csvUtil.loadReservations();
        assertEquals(3, reservations.size());
    }
}