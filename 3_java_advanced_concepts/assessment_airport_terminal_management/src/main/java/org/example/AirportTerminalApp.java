package org.example;

import org.example.view.ConsoleIO;
import org.example.view.ReservationController;

public class AirportTerminalApp {
    public static void main(String[] args) {
        ReservationController controller = new ReservationController();
        ConsoleIO consoleIO = new ConsoleIO();

        controller.initializeData();
        consoleIO.displayMenu();
        boolean running = true;

        do {
            String choice = consoleIO.getMenuChoice();
            switch (choice) {
                case "1":
                    controller.viewAvailableFlights();
                    controller.makeReservation();
                    break;
                case "2":
                    controller.cancelReservation();
                    break;
                case "3":
                    controller.viewAllReservations();
                    break;
                case "4":
                    controller.viewAllPassengersInFlight();
                    break;
                case "5":
                    System.out.println("Thank you for using the Airport Terminal Management System!\n");
                    running = false;
                    break;
            }
        } while (running);
    }
}
