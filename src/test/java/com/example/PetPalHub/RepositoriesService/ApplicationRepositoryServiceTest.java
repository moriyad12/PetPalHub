package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Creators.AdopterCustomCreator;
import com.example.PetPalHub.Creators.PetCustomCreator;
import com.example.PetPalHub.Creators.ShelterCustomCreator;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Exceptions.RelationException.ApplicationsAlreadyFoundExceptions;
import com.example.PetPalHub.Exceptions.UsersExceptions.AdopterNotFoundException;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class ApplicationRepositoryServiceTest {

    @Autowired
    PetRepositoryService petRepositoryService;
    @Autowired
    AdopterRepositoryService adopterRepositoryService;
    @Autowired
    ApplicationRepositoryService applicationRepositoryService;
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    AdopterCustomCreator adopterCustomCreator;
    @Autowired
    PetCustomCreator petCustomCreator;
    @Autowired
    ShelterCustomCreator shelterCustomCreator;


    @Test
    public void addApplicationTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        Assertions.assertNotEquals(0, a.getAdopter().getId());
    }

    @Test
    public void addApplicationToAlreadyFoundTest() {

        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        AdopterPetApplication b = applicationRepositoryService.getApplicationsByAdopterIdAndPetId(a.getAdopter().getId(), a.getPet().getId());
        Assertions.assertThrows(ApplicationsAlreadyFoundExceptions.class, () -> {
            applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        });
    }

    @Test
    public void updateApplicationStatusTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        applicationRepositoryService.updateApplicationStatus(a.getAdopter().getId(), a.getPet().getId(), Status.ACCEPTED);
        AdopterPetApplication b = applicationRepositoryService.getApplicationsByAdopterIdAndPetId(a.getAdopter().getId(), a.getPet().getId());
        Assertions.assertEquals(Status.ACCEPTED, b.getStatus());
    }

    @Test
    public void getByPet_Shelter_IdAndStatusTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getByPet_Shelter_IdAndStatus(a.getPet().getShelter().getId(), a.getStatus());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getPet().getShelter().getId() != a.getPet().getShelter().getId()
                    || application.getStatus() != a.getStatus()) {
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
    }


    @Test
    public void getApplicationsByAdopterIdAndPetIdNotFoundTest() {////  if adopter here will return not found application
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        Assertions.assertThrows(AdopterNotFoundException.class, () -> {
            applicationRepositoryService.getApplicationsByAdopterIdAndPetId(a.getAdopter().getId() + 100000, a.getPet().getId());
        });
    }


    @Test
    public void getApplicationsByAdopterIdTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getApplicationsByAdopterId(a.getAdopter().getId());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getAdopter().getId()!=a.getAdopter().getId()){
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void getApplicationsByPetIdTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getApplicationsByPetId(a.getPet().getId());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getPet().getId() != a.getPet().getId()) {
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);

    }

    @Test
    public void getApplicationsByStatusTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getApplicationsByStatus(a.getStatus());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getStatus() != a.getStatus()) {
                found = false;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void getApplicationsByStatusAndAdopterIdTest() {

        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getApplicationsByStatusAndAdopterId(a.getStatus(), a.getAdopter().getId());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getAdopter().getId() != a.getAdopter().getId()) {
                found = false;
            }
        }
        Assertions.assertTrue(found);

    }

    @Test
    public void getApplicationsByStatusAndPetId() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<AdopterPetApplication> list = applicationRepositoryService.getApplicationsByStatusAndPetId(a.getStatus(), a.getPet().getId());
        boolean found = true;
        for (AdopterPetApplication application : list) {
            if (application.getPet().getId() != a.getPet().getId()) {
                found = false;
            }
        }
        Assertions.assertTrue(found);

    }

    public AdopterPetApplication Create() {
        Shelter shelter=shelterCustomCreator.createShelter();
        shelterRepository.save(shelter);
        Pet pet = petCustomCreator.createPet(shelter);
        Adopter adopter = adopterCustomCreator.createAdopter();
        petRepositoryService.addPet(pet);
        adopterRepositoryService.add(adopter);
        return new AdopterPetApplication(adopter, pet, Status.PENDING, new Date());
    }

}
