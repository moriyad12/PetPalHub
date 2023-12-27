package com.example.PetPalHub.RepositoriesService.Relation;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Exceptions.RelationException.ApplicationNotFoundExceptions;
import com.example.PetPalHub.Exceptions.RelationException.ApplicationsAlreadyFoundExceptions;
import com.example.PetPalHub.Repositories.Relation.ApplicationRepository;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRepositoryService {


    @Autowired
    private PetRepositoryService petRepositoryService;
    @Autowired
    private AdopterRepositoryService adopterRepositoryService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ShelterRepositoryService shelterRepositoryService;


    public void addApplication(int adopterId, int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        Optional<AdopterPetApplication> a = Optional.ofNullable(applicationRepository.findByAdopterAndPet(adopter, pet));
        if (a.isPresent())
            throw new ApplicationsAlreadyFoundExceptions();
        AdopterPetApplication adopterPetApplication = new AdopterPetApplication(adopter, pet, Status.PENDING, new Date());
        applicationRepository.save(adopterPetApplication);
    }

    public void updateApplicationStatus(int adopterId, int petId,Status status) {
        AdopterPetApplication application = this.getApplicationsByAdopterIdAndPetId(adopterId, petId);
        application.setStatus(status);
        applicationRepository.save(application);
    }

    public AdopterPetApplication getApplicationsByAdopterIdAndPetId(int adopterId, int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        Optional<AdopterPetApplication> adopterPetApplication = Optional.ofNullable(applicationRepository.findByAdopterAndPet(adopter, pet));
        if (adopterPetApplication.isEmpty())
            throw new ApplicationNotFoundExceptions();
        return adopterPetApplication.get();
    }

    public List<AdopterPetApplication> getByPet_Shelter_IdAndStatus(int shelterId,Status status,PageRequest pageRequest) {
        Shelter shelter = shelterRepositoryService.getShelterById(shelterId);
        return applicationRepository.findByPet_ShelterAndStatus(shelter, status);
    }
    public List<ApplicationDto> getApplicationDtos(int adopterId,PageRequest pageRequest) {
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        List<AdopterPetApplication> applications = applicationRepository.findByAdopter(adopter);
        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        for (AdopterPetApplication adopterPetApplication : applications) {
            applicationDtoList.add(new ApplicationDto(adopterPetApplication));
        }
        return applicationDtoList;
    }

    public List<AdopterPetApplication> getApplicationsByAdopterId(int adopterId) {
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        return applicationRepository.findByAdopter(adopter);
    }

    public List<AdopterPetApplication> getApplicationsByPetId(int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
        return applicationRepository.findByPet(pet);
    }

    public List<AdopterPetApplication> getApplicationsByStatus(Status status) {
        return applicationRepository.findByStatus(status);
    }

    public List<AdopterPetApplication> getApplicationsByStatusAndAdopterId(Status status, int adopterId) {
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        return applicationRepository.findByAdopterAndStatus(adopter, status);
    }

    public List<AdopterPetApplication> getApplicationsByStatusAndPetId(Status status, int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
        return applicationRepository.findByPetAndStatus(pet, status);
    }
}
