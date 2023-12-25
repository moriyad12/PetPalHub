package com.example.PetPalHub.Repositories;

import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

    List<Pet> findAllPetsByShelterId(int shelterId);
}
