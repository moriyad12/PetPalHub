package com.example.PetPalHub.Repositories.users;

import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.users.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.profilePicturePath = :profileImagePicture WHERE u.id = :userId")
    void updateProfileImagePictureById(Integer userId, String profileImagePicture);

//    void updateProfileImagePictureById(Integer id, byte[] profileImagePicture);
}
