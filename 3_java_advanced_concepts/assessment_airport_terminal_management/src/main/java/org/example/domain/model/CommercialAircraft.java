package org.example.domain.model;

public class CommercialAircraft extends Aircraft {
    private String airlineName;

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName) {
        super(model, capacity, fuelCapacity);
        this.airlineName = airlineName;
    }

    @Override
    public String getAirlineInfo() {
        return airlineName;
    }
}
