package com.example.PetPalHub.Creators;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import org.springframework.stereotype.Component;

@Component
public class ShelterCustomCreator {
    static int value = 0;

    public Shelter createShelter() {
        return Shelter.builder()
                .name("abu ryad" + value + "here")
                .shelterLocation(Location.builder().country("bianky").city("paradise").build())
                .build();
    }
}
