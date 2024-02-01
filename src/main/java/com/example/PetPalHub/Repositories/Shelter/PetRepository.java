package com.example.PetPalHub.Repositories.Shelter;

import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import jakarta.annotation.Nullable;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> , JpaSpecificationExecutor<Pet> {
    List<Pet> findByShelterId(int shelterId);
    List<Pet> findAll(@Nullable Specification<Pet> specification);

    List<Pet> findByName(String s);

    @Modifying
    @Transactional
    @Query("UPDATE Pet p SET p.profilePicturePath = :profileImagePicture WHERE p.id = :petId")
    void updateProfileImagePictureById(Integer petId, String profileImagePicture);
}
