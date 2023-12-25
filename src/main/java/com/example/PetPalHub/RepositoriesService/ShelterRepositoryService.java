package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Entities.Shelter;
import com.example.PetPalHub.Exceptions.ShelterAlreadyCreatedException;
import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelterRepositoryService {
    @Autowired
    private ShelterRepository shelterRepository;

    public Shelter getShelterById(int id) {
        Optional<Shelter> shelter = shelterRepository.findById(id);
        if (shelter.isEmpty())
            throw new ShelterNotFoundException(id);
        return shelter.get();
    }

    public void createShelter(Shelter shelter) {
        if (shelterRepository.existsById(shelter.getId()))
            throw new ShelterAlreadyCreatedException(shelter.getId());
        shelterRepository.save(shelter);
    }

    public void updateShelter(Shelter shelter) {
        if (!shelterRepository.existsById(shelter.getId()))
            throw new ShelterNotFoundException(shelter.getId());
        shelterRepository.save(shelter);
    }

    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    public List<Pet> getAllPetsByShelterId(int id) {
        if (!shelterRepository.existsById(id))
            throw new ShelterNotFoundException(id);
        return shelterRepository.findPetsByShelterId(id);
    }

}
