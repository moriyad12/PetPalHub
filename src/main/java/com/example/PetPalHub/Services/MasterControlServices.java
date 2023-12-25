package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterControlServices {

    @Autowired
    ApplicationRepositoryService applicationRepositoryService;
    @Autowired
    PetRepositoryService petRepositoryService;

    @Autowired
    FilterService filterService;
    @Autowired
    PetViewDtoMapper petViewDtoMapper;

    public void acceptApplication(int adoptedId, int petId) {
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.ACCEPTED);
        Pet pet = petRepositoryService.getPetById(petId);
        pet.setAvailability(Availability.NOT_AVAILABLE);
        petRepositoryService.updatePet(pet);
    }

    public void rejectApplication(int adoptedId, int petId) {
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.REJECTED);
    }

    public List<AdopterPetApplication> getPendingApplicationByShelterID(int shelterId) {
        return applicationRepositoryService.getByPet_Shelter_IdAndStatus(shelterId, Status.PENDING);
    }

    public List<AdopterPetApplication> getAcceptedApplicationByShelterID(int shelterId) {
        return applicationRepositoryService.getByPet_Shelter_IdAndStatus(shelterId, Status.ACCEPTED);
    }

    public List<AdopterPetApplication> getRejectedApplicationByShelterID(int shelterId) {
        return applicationRepositoryService.getByPet_Shelter_IdAndStatus(shelterId, Status.REJECTED);
    }

    public void addPet(PetViewDto petViewDto) {
        Pet pet = petViewDtoMapper.getPetWhenCreate(petViewDto);
        petRepositoryService.addPet(pet);
    }

    public void editPet(PetViewDto petViewDto) {
        Pet pet = petViewDtoMapper.getPetWhenUpdate(petViewDto);
        petRepositoryService.addPet(pet);
    }
}
