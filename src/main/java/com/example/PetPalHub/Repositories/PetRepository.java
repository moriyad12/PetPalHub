package com.example.PetPalHub.Repositories;

import com.example.PetPalHub.Entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

}
