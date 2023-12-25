package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Exceptions.PetAlreadyAddedException;
import com.example.PetPalHub.Exceptions.PetNotFoundException;
import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.PetRepository;
import com.example.PetPalHub.Repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetRepositoryService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ShelterRepository shelterRepository;

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
    public List<Pet> getAllPetsByShelterId(int id) {
        if (!shelterRepository.existsById(id))
            throw new ShelterNotFoundException(id);
        return petRepository.findByShelterId(id);
    }
}
