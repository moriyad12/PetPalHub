package com.example.PetPalHub.Entities.Enums;

public enum HealthStatus {
    HEALTHY("HEALTHY"),
    UNHEALTHY("UNHEALTHY");
    private final String healthStatus;

    HealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public static HealthStatus fromString(String g) {
        for (HealthStatus r : HealthStatus.values()) {
            if (r.healthStatus.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }

    @Override
    public String toString() {
        return healthStatus;
    }

}
