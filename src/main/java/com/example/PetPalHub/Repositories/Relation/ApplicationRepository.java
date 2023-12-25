package com.example.PetPalHub.Repositories.Relation;

import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<AdopterPetApplication, Integer> {

    AdopterPetApplication findByAdopterAndPet(Adopter adopter, Pet pet);
    List<AdopterPetApplication> findByAdopter(Adopter adopter);

    List<AdopterPetApplication> findByPet(Pet pet);

    List<AdopterPetApplication> findByStatus(Status status);

    List<AdopterPetApplication> findByAdopterAndStatus(Adopter adopter, Status status);

    List<AdopterPetApplication> findByPetAndStatus(Pet pet, Status status);


}