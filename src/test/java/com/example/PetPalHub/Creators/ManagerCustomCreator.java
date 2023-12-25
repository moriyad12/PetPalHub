package com.example.PetPalHub.Creators;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Entities.users.Manager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ManagerCustomCreator {
    static int value = 0;

    public Manager createManager() {
        return Manager.builder()
                .firstName("manager")
                .lastName("Test")
                .email("manager" + value++ + "@test.com")
                .phoneNumber("1234567890")
                .gender(Gender.MALE)
                .role(Role.ADOPTER)
                .password("password")
                .build();
    }
}
