package com.example.PetPalHub.Creators;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.Role;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Entities.users.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffCustomCreator {
    static int value = 0;

    public Staff createStaff() {
        return Staff.builder()
                .firstName("staff")
                .lastName("Test")
                .email("staff" + value++ + "@test.com")
                .phoneNumber("1234567890")
                .gender(Gender.MALE)
                .role(Role.ADOPTER)
                .password("password")
                .build();
    }
}
