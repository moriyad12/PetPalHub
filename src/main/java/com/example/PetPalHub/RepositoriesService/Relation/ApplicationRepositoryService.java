package com.example.PetPalHub.RepositoriesService.Relation;

import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Exceptions.RelationException.ApplicationNotFoundExceptions;
import com.example.PetPalHub.Exceptions.RelationException.ApplicationsAlreadyFoundExceptions;
import com.example.PetPalHub.Repositories.Relation.ApplicationRepository;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public void addApplication(int adopterId, int petId) {  //// handle duplicated
        Pet pet = petRepositoryService.getPetById(petId);
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        Optional<AdopterPetApplication> a = Optional.ofNullable(applicationRepository.findByAdopterAndPet(adopter, pet));
        if (a.isPresent())
            throw new ApplicationsAlreadyFoundExceptions();
        AdopterPetApplication adopterPetApplication = new AdopterPetApplication(adopter, pet, Status.PENDING, new Date());
        applicationRepository.save(adopterPetApplication);
    }

    public AdopterPetApplication getApplicationsByAdopterIdAndPetId(int adopterId, int petId) {
        Pet pet = petRepositoryService.getPetById(petId);
        Adopter adopter = adopterRepositoryService.findById(adopterId);
        Optional<AdopterPetApplication> adopterPetApplication = Optional.ofNullable(applicationRepository.findByAdopterAndPet(adopter, pet));
        if (adopterPetApplication.isEmpty())
            throw new ApplicationNotFoundExceptions();
        return adopterPetApplication.get();
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
