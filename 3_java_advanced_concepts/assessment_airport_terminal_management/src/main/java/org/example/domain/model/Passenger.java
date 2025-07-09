package org.example.domain.model;

import java.util.Objects;

public class Passenger {
    private String name;
    private String passportNumber;

    public Passenger(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    @Override
    public String toString() {
        return String.format("%-24s%-10s",
                name,
                passportNumber);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Passenger passenger = (Passenger) object;
        return name.equals(passenger.name) && passportNumber.equals(passenger.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passportNumber);
    }
}
