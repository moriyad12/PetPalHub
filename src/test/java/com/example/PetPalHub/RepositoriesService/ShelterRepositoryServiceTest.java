package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Location;
import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Entities.Shelter;
import com.example.PetPalHub.Exceptions.ShelterAlreadyCreatedException;
import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShelterRepositoryServiceTest {

    @Autowired
    private ShelterRepositoryService shelterRepositoryService;

    @Test
    public void testGetShelterById() {
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            shelterRepositoryService.getShelterById(-1);
        });
    }

    @Test
    public void testCreateShelter() {
        Shelter shelter = new Shelter();
        shelter.setName("abu ryad");
        shelter.setShelterLocation(Location.builder().country("bianky").city("paradise").build());
        shelterRepositoryService.createShelter(shelter);
        Assertions.assertNotEquals(shelter.getId(), 0);
    }

    @Test
    public void createShelterThrowException() {
        Shelter shelter = new Shelter();
        shelter.setName("abu ryad");
        shelterRepositoryService.createShelter(shelter);
        Assertions.assertThrows(ShelterAlreadyCreatedException.class, () -> {
            shelterRepositoryService.createShelter(shelter);
        });
    }

    @Test
    public void testUpdateShelter() {
        Shelter shelter = new Shelter();
        shelter.setName("abu ryad");
        shelterRepositoryService.createShelter(shelter);
        shelter.setName("abu ryad 2");
        shelterRepositoryService.updateShelter(shelter);
        Assertions.assertEquals(shelter.getName(), "abu ryad 2");
    }

    @Test
    public void updateShelterThrowException() {
        Shelter shelter = new Shelter();
        shelter.setName("abu ryad");
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            shelterRepositoryService.updateShelter(shelter);
        });
    }

    @Test
    public void testGetAllShelters() {
        Shelter shelter = new Shelter();
        shelter.setName("abu ryad");
        shelterRepositoryService.createShelter(shelter);
        Assertions.assertNotEquals(shelterRepositoryService.getAllShelters().size(), 0);
    }


}