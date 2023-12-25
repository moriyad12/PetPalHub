package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Exceptions.PetAlreadyAddedException;
import com.example.PetPalHub.Exceptions.PetNotFoundException;
import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class PetRepositoryService {

    @Autowired
    private PetRepository petRepository;

    public  Pet getPetById(int id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isEmpty())
            throw new ShelterNotFoundException(id);
        return pet.get();
    }
    public void addPet(Pet pet) {
        if (petRepository.existsById(pet.getId()))
            throw new PetAlreadyAddedException(pet.getId());
        petRepository.save(pet);
    }
    public void updatePet(Pet pet) {
        if (!petRepository.existsById(pet.getId()))
            throw new PetNotFoundException(pet.getId());
        petRepository.save(pet);
    }
}
