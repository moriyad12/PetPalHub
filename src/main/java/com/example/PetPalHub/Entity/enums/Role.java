package com.example.PetPalHub.Entity.enums;

public enum Role {
    MANAGER("ROLE_MANAGER"),
    STAFF("ROLE_STAFF"),
    ADOPTER("ROLE_ADOPTER");
    private String role;

    Role(String role) {
        this.role = role;
    }

    public static Role fromString(String role) {
        for (Role r : Role.values()) {
            if (r.role.equals(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + role);
    }

    @Override
    public String toString() {
        return role;
    }
}
