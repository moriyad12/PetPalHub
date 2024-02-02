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
import com.example.PetPalHub.Exceptions.Shelter.PetIsNotAvailableException;
import com.example.PetPalHub.Exceptions.Shelter.PetNotFoundException;
import com.example.PetPalHub.MailSender.EmailSenderService;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.Mapper.ShelterViewDtoMapper;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
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
    DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    PetViewDtoMapper petViewDtoMapper;

    @Autowired
    ShelterViewDtoMapper shelterViewDtoMapper;
    @Autowired
    private ShelterRepositoryService shelterRepositoryService;
    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private EmailSenderService emailSenderService;
    public void acceptApplication(int adoptedId, int petId) {
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.ACCEPTED);
        Pet pet = petRepositoryService.getPetById(petId);
        User user = userRepositoryService.findById(adoptedId);
        emailSenderService.sendAcceptanceMail(user.getEmail(), user.getFirstName()+" "+user.getLastName(), pet.getName());
        pet.setAvailability(Availability.NOT_AVAILABLE);
        petRepositoryService.updatePet(pet);
    }

    public void rejectApplication(int adoptedId, int petId) {
        User user = userRepositoryService.findById(adoptedId);
        Pet pet = petRepositoryService.getPetById(petId);
        emailSenderService.sendRejectionMail(user.getEmail(), user.getFirstName()+" "+user.getLastName(), pet.getName());
        applicationRepositoryService.updateApplicationStatus(adoptedId, petId, Status.REJECTED);
    }

    public void updatePetProfilePicture(int petId, String profilePictureURL) {
        petRepositoryService.updatePetProfilePicture(petId, profilePictureURL);
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
        if (petViewDto.getAvailability() == Availability.NOT_AVAILABLE)
            throw new PetIsNotAvailableException();
        Pet pet = petViewDtoMapper.getPetWhenUpdate(petViewDto);
        petRepositoryService.updatePet(pet);
    }

    public ShelterViewDto getShelterViewDtoByShelterId(int shelterId) {
        Shelter shelter = shelterRepositoryService.getShelterById(shelterId);
        return shelterViewDtoMapper.getDtoToView(shelter);
    }

    public void updateShelter(ShelterViewDto shelterViewDto) {
        shelterRepositoryService.updateShelter(shelterViewDtoMapper.getShelterWhenUpdate(shelterViewDto));
    }
}
