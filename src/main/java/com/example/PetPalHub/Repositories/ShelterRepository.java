package com.example.PetPalHub.Repositories;

import com.example.PetPalHub.Entities.Pet;
import com.example.PetPalHub.Entities.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

    @Query(value = "SELECT * FROM Pet WHERE shelter_id = :shelterId", nativeQuery = true)
    List<Pet> findPetsByShelterId(@Param("shelterId") int shelterId);
}
