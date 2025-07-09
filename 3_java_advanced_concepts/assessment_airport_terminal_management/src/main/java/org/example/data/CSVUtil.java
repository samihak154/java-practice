package org.example.data;

import org.example.domain.model.Flight;
import org.example.domain.model.Passenger;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtil {
    private final String csvFilePath;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CSVUtil(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public CSVUtil() {
        this.csvFilePath = "data/reservations.csv"; // Default production path
    }

    public void saveReservation(Flight flight, Passenger passenger) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath, true))) {
            String csvLine = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                    flight.getFlightNumber(),
                    flight.getDepartureDate().format(DATE_FORMATTER),
                    flight.getDestination(),
                    flight.getTicketPrice(),
                    passenger.getName(),
                    passenger.getPassportNumber(),
                    flight.getAircraft().getModel(),
                    flight.getAircraft().getClass().getSimpleName()
            );
            writer.println();
            writer.print(csvLine);
        } catch (IOException e) {
            System.err.println("Error saving reservation: " + e.getMessage());
        }
    }

    private List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return lines;
    }

    private boolean matchReservation(String line, String flightNumber, String passengerName, String passportNumber) {
        String[] parts = line.split(",");
        return parts.length >= 6 &&
                parts[0].trim().equals(flightNumber) &&
                parts[4].trim().equals(passengerName) &&
                parts[5].trim().equals(passportNumber);
    }

    public Map<String, List<Passenger>> loadReservations() {
        Map<String, List<Passenger>> reservations = new HashMap<>();
        List<String> lines = readAllLines();

        for (String line : lines) {
            if (line.startsWith("FlightNumber")) continue; // Skip header
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                String flightNumber = parts[0].trim();
                Passenger passenger = new Passenger(parts[4].trim(), parts[5].trim());
                if (reservations.containsKey(flightNumber)) {
                    reservations.get(flightNumber).add(passenger);
                } else {
                    List<Passenger> newList = new ArrayList<>();
                    newList.add(passenger);
                    reservations.put(flightNumber, newList);
                }
            }
        }
        return reservations;
    }

    public void deleteReservation(Flight flight, Passenger passenger) {
        List<String> allLines = readAllLines();
        List<String> linesToKeep = new ArrayList<>();
        boolean found = false;

        for (String line : allLines) {
            if (matchReservation(line, flight.getFlightNumber(), passenger.getName(),
                    passenger.getPassportNumber())) {
                found = true;
            } else {
                linesToKeep.add(line);
            }
        }
        if (found) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(csvFilePath))) {
                for (String line : linesToKeep) {
                    writer.println(line);
                }
            } catch (IOException e) {
                System.err.println("Error writing to CSV file: " + e.getMessage());
            }
        }
    }
}
