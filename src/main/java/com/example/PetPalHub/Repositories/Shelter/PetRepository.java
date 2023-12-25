package com.example.PetPalHub.Repositories.Shelter;

import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> , JpaSpecificationExecutor<Pet> {
    List<Pet> findByShelterId(int shelterId);
    List<Pet> findAll(@Nullable Specification<Pet> specification);

    @Query("SELECT p FROM Pet p WHERE p.shelter = :shelter")
    Page<Pet> find(@Param("shelter") Shelter shelter, @Nullable Specification<Pet> specification, PageRequest pageRequest);

}
