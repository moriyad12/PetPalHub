package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationDtoTest {

    @Test
    public void testParameterizedConstructor() {
        String petName = "Buddy";
        Status status = Status.PENDING;
        Date applicationDate = new Date();
        ApplicationDto applicationDto = new ApplicationDto(1, 1, "John Doe", petName, status, "Shelter", applicationDate);
        assertEquals(petName, applicationDto.getPetName());
        assertEquals(status, applicationDto.getStatus());
        assertEquals(applicationDate, applicationDto.getApplicationDate());
    }


    @Test
    public void testSetterGetter() {
        ApplicationDto applicationDto = new ApplicationDto();
        String petName = "Max";
        Status status = Status.REJECTED;
        Date applicationDate = new Date();
        applicationDto.setPetName(petName);
        applicationDto.setStatus(status);
        applicationDto.setApplicationDate(applicationDate);
        assertEquals(petName, applicationDto.getPetName());
        assertEquals(status, applicationDto.getStatus());
        assertEquals(applicationDate, applicationDto.getApplicationDate());
    }

}