package com.example.PetPalHub.Creators;

import com.example.PetPalHub.Entity.enums.Gender;
import com.example.PetPalHub.Entity.enums.Role;
import com.example.PetPalHub.Entity.users.Adopter;
import org.springframework.stereotype.Component;

@Component
public class AdopterCustomCreator {
    static int value = 0;

    public Adopter createAdopter() {
        return Adopter.builder()
                .firstName("Adopter")
                .lastName("Test")
                .email("adopter" + value++ + "@test.com")
                .phoneNumber("1234567890")
                .gender(Gender.MALE)
                .role(Role.ADOPTER)
                .password("password")
                .build();
    }
}
