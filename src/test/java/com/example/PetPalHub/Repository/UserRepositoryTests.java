package com.example.PetPalHub.Repository;

import com.example.PetPalHub.Creators.ManagerCustomCreator;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Repositories.users.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerCustomCreator managerCustomCreator;

    @Test
    public void existsById(){
        Manager manager = managerCustomCreator.createManager();
        userRepository.save(manager);
        Assertions.assertTrue(userRepository.existsById(manager.getId()));
    }

    @Test
    public void DoesNotExistById(){
        Assertions.assertFalse(userRepository.existsById(100000000));
    }

    @Test
    public void updateProfileImagePath() {
        Manager manager = managerCustomCreator.createManager();
        userRepository.save(manager);
        userRepository.updateProfileImagePictureById(manager.getId(), "test");
        User user = userRepository.findById(manager.getId()).get();
        Assertions.assertEquals("test", user.getProfilePicturePath());
        Assertions.assertEquals(user.getUsername(), manager.getUsername());
    }
}
