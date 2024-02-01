package com.example.PetPalHub.Mapper;

import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Dto.UserProfileDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.User;
import org.springframework.stereotype.Service;

@Service
public class UserProfileDtoMapper {

    public UserProfileDto getDtoToView(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .gender(user.getGender())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .profilePicturePath(user.getProfilePicturePath())
                .build();
    }

    public User getUserFromDto(User user,UserProfileDto userProfileDto) {
        user.setGender(userProfileDto.getGender());
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        return user;
    }
}
