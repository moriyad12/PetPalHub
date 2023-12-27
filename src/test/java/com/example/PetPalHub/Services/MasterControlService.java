package com.example.PetPalHub.Services;

import com.example.PetPalHub.Creators.AdopterCustomCreator;
import com.example.PetPalHub.Creators.PetCustomCreator;
import com.example.PetPalHub.Creators.ShelterCustomCreator;
import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.Repositories.Shelter.PetRepository;
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
public class MasterControlService {

    @Autowired
    MasterControlServices masterControlServices;
    @Autowired
    PetRepository petRepository;
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
    @Autowired
    PetViewDtoMapper petViewDtoMapper;


    @Test
    public void acceptApplicationTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());

        masterControlServices.acceptApplication(a.getAdopter().getId(), a.getPet().getId());

        AdopterPetApplication b = applicationRepositoryService.getApplicationsByAdopterIdAndPetId(a.getAdopter().getId(), a.getPet().getId());

        Assertions.assertEquals(b.getStatus(), Status.ACCEPTED);
        Assertions.assertEquals(b.getPet().getAvailability(), Availability.NOT_AVAILABLE);
    }

    @Test
    public void rejectApplication() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());

        masterControlServices.rejectApplication(a.getAdopter().getId(), a.getPet().getId());

        AdopterPetApplication b = applicationRepositoryService.getApplicationsByAdopterIdAndPetId(a.getAdopter().getId(), a.getPet().getId());

        Assertions.assertEquals(b.getStatus(), Status.REJECTED);
    }

    @Test
    public void getPendingApplicationByShelterIDTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        List<ApplicationDto> list = masterControlServices.getPendingApplicationByShelterID(1,50,a.getPet().getShelter().getId());
        boolean found = true;
        for (ApplicationDto application : list) {
            if (!application.getPetName().equals(a.getPet().getName()) || application.getStatus() != Status.PENDING) {
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
        Assertions.assertTrue(list.size()>0);
    }
    @Test
    public void getAcceptedApplicationByShelterIDTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        applicationRepositoryService.updateApplicationStatus(a.getAdopter().getId(), a.getPet().getId(),Status.ACCEPTED);
        List<ApplicationDto> list = masterControlServices.getAcceptedApplicationByShelterID(1,50,a.getPet().getShelter().getId());
        boolean found = true;
        for (ApplicationDto application : list) {
            if (!application.getPetName().equals(a.getPet().getName()) || application.getStatus() != Status.ACCEPTED) {
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
        Assertions.assertTrue(list.size()>0);
    }
    @Test
    public void getRejectedApplicationByShelterIDTest() {
        AdopterPetApplication a = this.Create();
        applicationRepositoryService.addApplication(a.getAdopter().getId(), a.getPet().getId());
        applicationRepositoryService.updateApplicationStatus(a.getAdopter().getId(), a.getPet().getId(),Status.REJECTED);
        List<ApplicationDto> list = masterControlServices.getRejectedApplicationByShelterID(1, 50, a.getPet().getShelter().getId());
        boolean found = true;
        for (ApplicationDto application : list) {
            if (!application.getPetName().equals(a.getPet().getName()) || application.getStatus() != Status.REJECTED) {
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
        Assertions.assertTrue(list.size()>0);
    }


    @Test
    public void addPetTest() {
        Shelter shelter = shelterCustomCreator.createShelter();
        shelterRepository.save(shelter);
        Pet pet = petCustomCreator.createPet(shelter);
        PetViewDto petViewDto = petViewDtoMapper.getDtoToView(pet);
        petViewDto.setName("pablo");
        masterControlServices.addPet(petViewDto);
        Pet p2 = petRepository.findByName("pablo").get(0);
        Assertions.assertNotEquals(0, p2.getId());
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
