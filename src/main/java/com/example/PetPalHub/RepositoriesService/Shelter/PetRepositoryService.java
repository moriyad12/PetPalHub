package com.example.PetPalHub.RepositoriesService.Shelter;

import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Exceptions.Shelter.PetAlreadyAddedException;
import com.example.PetPalHub.Exceptions.Shelter.PetNotFoundException;
import com.example.PetPalHub.Exceptions.Shelter.ShelterNotFoundException;
import com.example.PetPalHub.Repositories.Shelter.PetRepository;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetRepositoryService {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ShelterRepository shelterRepository;


    public Pet getPetById(int id) {
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

    public List<Pet> getAllPets(Specification<Pet> specification) {
        return petRepository.findAll(specification);
    }

    public List<PetHeaderDto> getFilteredPetsHeaderDto(PageRequest pageRequest, Specification<Pet> specification) {
        List<Pet> pets = petRepository.findAll(specification, pageRequest).getContent();
        List<PetHeaderDto> petHeaderDtos = new ArrayList<>();
        for (Pet pet : pets) {
            petHeaderDtos.add(new PetHeaderDto(pet));
        }
        return petHeaderDtos;
    }

}
