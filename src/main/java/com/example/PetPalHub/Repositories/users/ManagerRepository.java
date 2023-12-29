package com.example.PetPalHub.Repositories.users;

import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query("SELECT m FROM User m WHERE m.email =:email")
    Optional<Manager> findByEmail(String email);

//    Optional<Shelter> findShelterById(int id);
}
