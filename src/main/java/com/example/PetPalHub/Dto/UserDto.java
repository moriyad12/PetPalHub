package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String phoneNumber;
    private Role role;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String shelterCode;
    private int shelterId;
    private Gender gender;
    private boolean signInWithEmail;
}
