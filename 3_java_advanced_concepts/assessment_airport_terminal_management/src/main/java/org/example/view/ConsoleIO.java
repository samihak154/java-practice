package org.example.view;

import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;
import org.example.domain.reservation.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleIO {
    private static Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        String menu = "===========================================\n" +
                "Airport Terminal Management System\n" +
                "===========================================\n" +
                "1. Make a Reservation\n" +
                "2. Cancel a Reservation \n" +
                "3. View All Reservations \n" +
                "4. View Passengers for Specific Flight\n" +
                "5. Exit\n" +
                "===========================================\n";
        System.out.print(menu);
    }

    public static String getMenuChoice() {
        String menuChoice = "";
        while (true) {
            System.out.print("Choose an option: ");
            menuChoice = scanner.nextLine();
            if (menuChoice.matches("[1-5]")) {
                break;
            }
            System.out.println("Please choose a valid menu option!\n");
        }
        return menuChoice;
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayFlights(List<Flight> flights) {
        System.out.println("\nAvailable Flights:\n");
        System.out.println("   Flight   Airline            Aircraft        Dep. Date       Destination     Price");
        System.out.println("------------------------------------------------------------------------------------------");
        for (int i = 0; i < flights.size(); i++) {
            System.out.println((i + 1) + ". " + flights.get(i));
        }
        System.out.println("\n");
    }

    public void displayPassengersPerFlight(List<Passenger> passengers) {
        System.out.println("\n-------------------------------------");
        System.out.println("   Passenger Name          Passport");
        System.out.println("-------------------------------------");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger passenger = passengers.get(i);
            String displayName = passenger.getName();
            if (displayName.length() > 17) {
                displayName = displayName.substring(0, 17) + "...";
            }
            Passenger displayPassenger = new Passenger(displayName, passenger.getPassportNumber());
            System.out.println((i + 1) + ". " + displayPassenger.toString());
        }
        System.out.println("-------------------------------------\n");
    }

    private void formatFlightPassengers(List<String> formattedReservations, Flight flight, List<Passenger> passengers) {
        boolean firstPassenger = true;
        for (Passenger passenger : passengers) {
            String displayName = passenger.getName();
            if (displayName.length() > 17) {
                displayName = displayName.substring(0, 17) + "...";
            }
            if (firstPassenger) {
                formattedReservations.add(String.format("%s%-24s%s",
                        flight.toStringNoPrice(),
                        displayName,
                        passenger.getPassportNumber()
                ));
                firstPassenger = false;
            } else {
                formattedReservations.add(String.format("%-9s%-19s%-16s%-16s%-16s%-24s%s",
                        "", "", "", "", "",
                        displayName,
                        passenger.getPassportNumber()
                ));
            }
        }
    }

    public List<String> formatReservationsTable(Map<String, List<Passenger>> reservations, ReservationService service) {
        List<String> formattedReservations = new ArrayList<>();

        formattedReservations.add("\n--------------------------------------------------------------------------------------------------------------");
        formattedReservations.add("Flight   Airline            Aircraft        Dep. Date       Destination     Passenger Name          Passport");
        formattedReservations.add("--------------------------------------------------------------------------------------------------------------");

        for (Map.Entry<String, List<Passenger>> entry : reservations.entrySet()) {
            String flightNumber = entry.getKey();
            List<Passenger> passengers = entry.getValue();

            Flight flight = service.getFlightByNumber(flightNumber);
            if (flight == null) continue;

            formatFlightPassengers(formattedReservations, flight, passengers);
        }

        formattedReservations.add("--------------------------------------------------------------------------------------------------------------\n");
        return formattedReservations;
    }

    public void displayReservations(Map<String, List<Passenger>> reservations, ReservationService service) {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.\n");
            return;
        }
        List<String> formattedReservations = formatReservationsTable(reservations, service);
        for (String line : formattedReservations) {
            System.out.println(line);
        }
    }
}
