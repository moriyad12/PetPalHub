package com.example.PetPalHub.Repositories.users;

import com.example.PetPalHub.Entities.users.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdopterRepository extends JpaRepository<Adopter,Integer> {
    @Query("SELECT a FROM User a WHERE a.email =:email")
    Optional<Adopter> findByEmail(String email);

}
