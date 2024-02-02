package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.users.Staff;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Exceptions.UsersExceptions.StaffNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.UserNotFoundException;
import com.example.PetPalHub.Repositories.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    private final UserRepository userRepository;

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    public User update(User user) {
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isEmpty())
            throw new UserNotFoundException();
        userRepository.save(user);
        return user;
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty())
            throw new UserNotFoundException();
        return user.get();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void deleteById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new StaffNotFoundException();
        userRepository.delete(user.get());
    }

    public void updateProfileProfilePicture(int id, String pictureUrl) {
        this.checkUserExists(id);
        this.userRepository.updateProfileImagePictureById(id, pictureUrl);
    }

    private void checkUserExists(int id) {
        if (!userRepository.existsById(id))
            throw new UserNotFoundException();
    }
}
