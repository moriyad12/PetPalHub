package com.example.PetPalHub.Entities.Enums;

public enum Status {
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED"),
    PENDING("PENDING");
    private String status;

    Status(String status) {
        this.status = status;
    }

    public static Status fromString(String status) {
        for (Status s : Status.values()) {
            if (s.status.equals(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + status);
    }

    @Override
    public String toString() {
        return status;
    }

}
