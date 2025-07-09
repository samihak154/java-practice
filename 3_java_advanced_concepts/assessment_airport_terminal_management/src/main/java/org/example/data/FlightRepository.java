package org.example.data;

import org.example.domain.model.Aircraft;
import org.example.domain.model.CommercialAircraft;
import org.example.domain.model.Flight;
import org.example.domain.model.PrivateJet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class FlightRepository {
    Aircraft commercialAircraft1 = new CommercialAircraft("Boeing 737", 160, 28000.0, "Delta Airlines");
    Aircraft commercialAircraft2 = new CommercialAircraft("Boeing 777", 273, 171000.0, "American Airlines");
    Aircraft commercialAircraft3 = new CommercialAircraft("Boeing 787", 318, 171000.0, "United Airlines");
    Aircraft privateJet = new PrivateJet("Gulfstream G650", 5, 24200.0, true, 956);


    public List<Flight> loadFlights() {
        return List.of(
            new Flight("DL2618", LocalDate.of(2025, 8, 2), "Los Angeles", new BigDecimal("212.00"), commercialAircraft1),
            new Flight("UA520", LocalDate.of(2025, 7, 9), "Paris", new BigDecimal("1050.00"), commercialAircraft3),
            new Flight("AA240", LocalDate.of(2025, 10, 23), "Lima", new BigDecimal("704.00"), commercialAircraft2),
            new Flight("DL1356", LocalDate.of(2025, 11, 4), "Denver", new BigDecimal("186.00"), commercialAircraft1),
            new Flight("PV621", LocalDate.of(2025, 7, 28), "Cancun", new BigDecimal("4600.00"), privateJet)
        );
    }
}
