package com.example.PetPalHub.Entities.Enums;

public enum Behaviour {
    AGGRESSIVE("AGGRESSIVE"),
    FRIENDLY("FRIENDLY");
    private final String behaviour;

    Behaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public static Behaviour fromString(String g) {
        for (Behaviour r : Behaviour.values()) {
            if (r.behaviour.equals(g)) {
                return r;
            }
        }
        throw new IllegalArgumentException("No enum constant with role: " + g);
    }

    @Override
    public String toString() {
        return behaviour;
    }
}
