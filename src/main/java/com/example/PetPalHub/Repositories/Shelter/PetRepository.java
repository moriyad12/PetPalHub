package com.example.PetPalHub.Repositories.Shelter;

import com.example.PetPalHub.Entities.Shelter.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> , JpaSpecificationExecutor<Pet> {
    List<Pet> findByShelterId(int shelterId);

}
