package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Exceptions.Shelter.PetIsNotAvailableException;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdopterService {

    @Autowired
    private PetRepositoryService petRepositoryService;
    @Autowired
    private PetViewDtoMapper petViewDtoMapper;
    @Autowired
    private ApplicationRepositoryService applicationRepositoryService;
    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;

    public PetViewDto viewPet(int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
//        checkIfPetAvailable(pet);
        return petViewDtoMapper.getDtoToView(pet);
    }

    public void applyForPet(int petId, int adopterId) {
        Pet pet = petRepositoryService.getPetById(petId);
        checkIfPetAvailable(pet);
        applicationRepositoryService.addApplication(adopterId, petId);
    }

    public List<ApplicationDto> getApplicationsByAdopterId(int pageIndex, int pageSize, int adopterId) {
        return dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId);
    }

    private void checkIfPetAvailable(Pet pet) {
        if (pet.getAvailability() == Availability.NOT_AVAILABLE)
            throw new PetIsNotAvailableException();
    }

}
