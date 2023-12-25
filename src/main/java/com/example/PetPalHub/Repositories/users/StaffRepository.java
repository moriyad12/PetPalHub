package com.example.PetPalHub.Repositories.users;

import com.example.PetPalHub.Entities.users.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {
    @Query("SELECT s FROM User s WHERE s.email =:email")
    Optional<Staff> findByEmail(String email);
}
