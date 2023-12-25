package com.example.PetPalHub.Repository.users;

import com.example.PetPalHub.Entity.users.Adopter;
import com.example.PetPalHub.Entity.users.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
    @Query("SELECT m FROM User m WHERE m.email =:email")
    Optional<Manager> findByEmail(String email);
}
