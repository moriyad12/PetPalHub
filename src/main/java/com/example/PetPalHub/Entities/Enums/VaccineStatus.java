package com.example.PetPalHub.Entities.Enums;

public enum VaccineStatus {
    VACCINATED("VACCINATED"),
    NOT_VACCINATED("NOT_VACCINATED"),
    PARTIALLY_VACCINATED("PARTIALLY_VACCINATED");
    private final String vaccineStatus;
    VaccineStatus(String vaccineStatus) {
        this.vaccineStatus = vaccineStatus;
    }
    public static VaccineStatus fromString(String g) {
        for (VaccineStatus r : VaccineStatus.values()) {
            if (r.vaccineStatus.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }
    @Override
    public String toString() {
        return vaccineStatus;
    }
}
