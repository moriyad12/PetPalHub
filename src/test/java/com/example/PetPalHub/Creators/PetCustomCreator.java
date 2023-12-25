package com.example.PetPalHub.Creators;

import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class PetCustomCreator {

    @Autowired
    ShelterCustomCreator shelterCustomCreator;

    public Pet createPet(Shelter shelter) {

        return Pet.builder()
                .name("Teto")
                .breed(Breed.BEAGLE)
                .availability(Availability.AVAILABLE)
                .dateOfBirth(new Date())
                .gender(Gender.FEMALE)
                .healthStatus(HealthStatus.HEALTHY)
                .behaviour(Behaviour.FRIENDLY)
                .vaccineStatus(VaccineStatus.VACCINATED)
                .description("cute")
                .species("dog")
                .shelter(shelter)
                .build();
    }
}
