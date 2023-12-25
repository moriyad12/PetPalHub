package com.example.PetPalHub.RepositoriesService.Shelter;

import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Exceptions.Shelter.ShelterAlreadyCreatedException;
import com.example.PetPalHub.Exceptions.Shelter.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
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


}
