package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Location;
import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Entities.Shelter;
import com.example.PetPalHub.Exceptions.PetAlreadyAddedException;
import com.example.PetPalHub.Exceptions.PetNotFoundException;
import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.ShelterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetRepositoryServiceTest {
    
    @Autowired
    private PetRepositoryService petRepositoryService;
    @Autowired
    private ShelterRepository shelterRepository;

    @Test
    public void testGetPetById() {
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            petRepositoryService.getPetById(-1);
        });
    }

    @Test
    public void testCreatePet() {
        Pet pet = petCreator();
        petRepositoryService.addPet(pet);
        Assertions.assertNotEquals(pet.getId(), 0);
    }

    @Test
    public void createShelterThrowException() {
        Pet pet = petCreator();
        petRepositoryService.addPet(pet);
        Assertions.assertThrows(PetAlreadyAddedException.class, () -> {
            petRepositoryService.addPet(pet);
        });
    }

    @Test
    public void testUpdateShelter() {
        Pet pet = petCreator();
        petRepositoryService.addPet(pet);
        pet.setName("micol");
        petRepositoryService.updatePet(pet);
        Assertions.assertEquals(pet.getName(), "micol");
    }

    @Test
    public void updateShelterThrowException() {
        Pet pet = petCreator();
        Assertions.assertThrows(PetNotFoundException.class, () -> {
            petRepositoryService.updatePet(pet);
        });
    }
    @Test
    public void testFindAllPetsByShelterIdThrowException() {
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            petRepositoryService.getAllPetsByShelterId(-1);
        });
    }
    @Test
    public void testFindAllPetsByShelterIs(){
        Shelter shelter = shelterCreator();
        shelterRepository.save(shelter);
        Pet pet1 = petCreator();
        pet1.setShelter(shelter);
        petRepositoryService.addPet(pet1);
        Pet pet2 = petCreator();
        pet2.setShelter(shelter);
        petRepositoryService.addPet(pet2);
        Assertions.assertEquals(petRepositoryService.getAllPetsByShelterId(shelter.getId()).size(),2);
    }

    private Pet petCreator(){
        Shelter shelter = shelterCreator();
        shelterRepository.save(shelter);
        return  Pet.builder()
                .name("faris")
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
    private Shelter shelterCreator(){
        return Shelter.builder()
                .name("abu ryad")
                .shelterLocation(Location.builder().country("bianky").city("paradise").build())
                .build();
    }

}