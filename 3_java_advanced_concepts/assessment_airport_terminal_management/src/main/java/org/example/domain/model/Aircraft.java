package org.example.domain.model;

public abstract class Aircraft {
    protected String model;
    protected int capacity;
    protected double fuelCapacity;

    public Aircraft(String model, int capacity, double fuelCapacity) {
        this.model = model;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public abstract String getAirlineInfo();


    @Override
    public String toString() {
        return super.toString();
    }
}
