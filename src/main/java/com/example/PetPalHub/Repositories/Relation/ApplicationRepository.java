package com.example.PetPalHub.Repositories.Relation;

import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<AdopterPetApplication, Integer> {


    List<AdopterPetApplication> findByAdopter(Adopter adopter);

    List<AdopterPetApplication> findByPet(Pet pet);


    List<AdopterPetApplication> findByStatus(int status);

    // Find all applications for a given adopter and status
    List<AdopterPetApplication> findByAdopterAndStatus(Adopter adopter, int status);

    // Find all applications for a given pet and status
    List<AdopterPetApplication> findByPetAndStatus(Pet pet, int status);

}