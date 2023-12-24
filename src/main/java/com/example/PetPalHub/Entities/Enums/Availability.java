package com.example.PetPalHub.Entities.Enums;

public enum Availability {
    AVAILABLE("AVAILABLE"),
    NOT_AVAILABLE("NOT_AVAILABLE");
    private final String availability;

    Availability(String availability) {
        this.availability = availability;
    }

    public static Availability fromString(String g) {
        for (Availability r : Availability.values()) {
            if (r.availability.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }

    @Override
    public String toString() {
        return availability;
    }
}
