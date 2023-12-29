package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Dto.ShelterViewDto;
import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.Mapper.ShelterViewDtoMapper;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.ManagerRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.StaffRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.UserRepositoryService;
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
    UserRepositoryService userRepositoryService;

    @Autowired
    ManagerRepositoryService managerRepositoryService;
    @Autowired
    StaffRepositoryService staffRepositoryService;
    @Autowired
    DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    PetViewDtoMapper petViewDtoMapper;

    @Autowired
    ShelterViewDtoMapper shelterViewDtoMapper;

    public void acceptApplication(int adoptedId, int petId) {
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.ACCEPTED);
        Pet pet = petRepositoryService.getPetById(petId);
        pet.setAvailability(Availability.NOT_AVAILABLE);
        petRepositoryService.updatePet(pet);
    }

    public void rejectApplication(int adoptedId, int petId) {
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.REJECTED);
    }

    public List<ApplicationDto> getPendingApplicationByShelterID(int pageIndex, int pageSize, int shelterId) {
        return dashboardRepositoryService.getApplicationByShelterIDAndStatusPage(pageIndex, pageSize, shelterId, Status.PENDING);
    }

    public List<ApplicationDto> getAcceptedApplicationByShelterID(int pageIndex, int pageSize, int shelterId) {
        return dashboardRepositoryService.getApplicationByShelterIDAndStatusPage(pageIndex, pageSize, shelterId, Status.ACCEPTED);
    }

    public List<ApplicationDto> getRejectedApplicationByShelterID(int pageIndex, int pageSize, int shelterId) {
        return dashboardRepositoryService.getApplicationByShelterIDAndStatusPage(pageIndex, pageSize, shelterId, Status.REJECTED);
    }

    public void addPet(PetViewDto petViewDto) {
        Pet pet = petViewDtoMapper.getPetWhenCreate(petViewDto);
        petRepositoryService.addPet(pet);
    }

    public void editPet(PetViewDto petViewDto) {
        Pet pet = petViewDtoMapper.getPetWhenUpdate(petViewDto);
        petRepositoryService.addPet(pet);
    }

    public ShelterViewDto getShelterViewDtoByUserId(int userId) {
        Role role = userRepositoryService.getRoleByUserId(userId);
        if (role == Role.STAFF) {
            Shelter shelter = staffRepositoryService.findShelterByStaffId(userId);
            return shelterViewDtoMapper.getDtoToView(shelter);
        } else if (role == Role.MANAGER) {
            Shelter shelter = managerRepositoryService.findShelterByManagerId(userId);
            return shelterViewDtoMapper.getDtoToView(shelter);
        }
        throw new RuntimeException();
    }

}
