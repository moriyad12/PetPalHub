package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
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
        return petViewDtoMapper.getDtoToView(pet);
    }

    public void applyForPet(int petId, int adopterId) {
        Pet pet = petRepositoryService.getPetById(petId);
        applicationRepositoryService.addApplication(adopterId, petId);
    }

    public List<ApplicationDto> getApplicationsByAdopterId(int pageIndex, int pageSize, int adopterId) {
        return dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId);
    }

}
