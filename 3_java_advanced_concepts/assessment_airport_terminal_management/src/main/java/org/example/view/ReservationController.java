package org.example.view;

import org.example.data.CSVUtil;
import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;
import org.example.domain.reservation.ReservationService;

import java.util.List;
import java.util.Map;

public class ReservationController {
    private ReservationService reservationService;
    private ConsoleIO consoleIO;
    private CSVUtil csvUtil;

    public ReservationController() {
        this.reservationService = new ReservationService();
        this.consoleIO = new ConsoleIO();
        this.csvUtil = new CSVUtil();
    }

    public void initializeData() {
        Map<String, List<Passenger>> loadedReservations = csvUtil.loadReservations();
        reservationService.loadReservations(loadedReservations);
    }

    public void viewAvailableFlights() {
        List<Flight> flights = reservationService.getAvailableFlights();
        consoleIO.displayFlights(flights);
    }

    public String validateFlightNumber() {
        while (true) {
            String flightNumber = consoleIO.getString("Enter flight number: ").toUpperCase();
            if (reservationService.isValidFlight(flightNumber)) {
                return flightNumber;
            }
            System.out.println("Error: Flight " + flightNumber + " does not exist! Please try again.\n");
        }
    }

    public void makeReservation() {
        String flightNumber = validateFlightNumber();
        String passengerName = consoleIO.getString("Enter passenger name: ").toUpperCase();
        String passportNumber = consoleIO.getString("Enter passport number: ").toUpperCase();

        Passenger passenger = new Passenger(passengerName, passportNumber);

        reservationService.addReservation(flightNumber, passenger);
        Flight flight = reservationService.getFlightByNumber(flightNumber);
        csvUtil.saveReservation(flight, passenger);

        System.out.println("Reservation successful! " + passengerName + " has been booked on flight " + flightNumber + "\n");
    }

    public void cancelReservation() {
        String flightNumber = validateFlightNumber();
        String passengerName = consoleIO.getString("Enter passenger name: ").toUpperCase();
        String passportNumber = consoleIO.getString("Enter passport number: ").toUpperCase();

        Passenger passenger = new Passenger(passengerName, passportNumber);

        boolean removed = reservationService.removeReservation(flightNumber, passenger);
        if (removed) {
            Flight flight = reservationService.getFlightByNumber(flightNumber);
            csvUtil.deleteReservation(flight, passenger);
            System.out.println("Cancellation successful! " + passengerName + " has been removed from flight " + flightNumber + "\n");
        } else {
            System.out.println("Error: " + passengerName + " was not found on flight " + flightNumber + "\n");
        }
    }

    public void viewAllReservations() {
        Map<String, List<Passenger>> reservations = reservationService.getReservations();
        consoleIO.displayReservations(reservations, reservationService);
    }

    public void viewAllPassengersInFlight() {
        String flightNumber = validateFlightNumber();
        List<Passenger> passengersInFlight = reservationService.getPassengersForFlight(flightNumber);
        consoleIO.displayPassengersPerFlight(passengersInFlight);
    }
}
