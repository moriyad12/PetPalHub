package com.example.PetPalHub.Mapper;

import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetViewDtoMapper {

    @Autowired
    private ShelterRepositoryService shelterRepositoryService;
    @Autowired
    private PetRepositoryService petRepositoryService;
    public PetViewDto getDtoToView(Pet pet){
        return PetViewDto.builder()
                .id(pet.getId())
                .name(pet.getName())
                .species(pet.getSpecies())
                .breed(pet.getBreed())
                .behaviour(pet.getBehaviour())
                .dateOfBirth(pet.getDateOfBirth())
                .description(pet.getDescription())
                .availability(pet.getAvailability())
                .gender(pet.getGender())
                .healthStatus(pet.getHealthStatus())
                .vaccineStatus(pet.getVaccineStatus())
                .imagePath(pet.getImagePath())
                .shelterId(pet.getShelter().getId())
                .shelterName(pet.getShelter().getName())
                .profilePicturePath(pet.getProfilePicturePath())
                .build();
    }
    public Pet getPetWhenCreate(PetViewDto petViewDto){
        return Pet.builder()
                .name(petViewDto.getName())
                .species(petViewDto.getSpecies())
                .breed(petViewDto.getBreed())
                .behaviour(petViewDto.getBehaviour())
                .dateOfBirth(petViewDto.getDateOfBirth())
                .description(petViewDto.getDescription())
                .availability(petViewDto.getAvailability())
                .gender(petViewDto.getGender())
                .healthStatus(petViewDto.getHealthStatus())
                .vaccineStatus(petViewDto.getVaccineStatus())
                .imagePath(petViewDto.getImagePath())
                .shelter(shelterRepositoryService.getShelterById(petViewDto.getShelterId()))
                .build();
    }
    public Pet getPetWhenUpdate(PetViewDto petViewDto){
        Pet pet =petRepositoryService.getPetById(petViewDto.getId());
        pet.setName(petViewDto.getName());
        pet.setSpecies(petViewDto.getSpecies());
        pet.setBreed(petViewDto.getBreed());
        pet.setBehaviour(petViewDto.getBehaviour());
        pet.setDateOfBirth(petViewDto.getDateOfBirth());
        pet.setDescription(petViewDto.getDescription());
        pet.setAvailability(petViewDto.getAvailability());
        pet.setGender(petViewDto.getGender());
        pet.setHealthStatus(petViewDto.getHealthStatus());
        pet.setVaccineStatus(petViewDto.getVaccineStatus());
        pet.setImagePath(petViewDto.getImagePath());
        return pet;
    }
}
