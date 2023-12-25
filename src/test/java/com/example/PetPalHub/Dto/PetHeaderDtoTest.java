package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Creators.PetCustomCreator;
import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.HealthStatus;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PetHeaderDtoTest {
    @Autowired
    private PetCustomCreator petCustomCreator;
    @Test
    public void testConstructorWithPet() {

        PetHeaderDto petHeaderDto = new PetHeaderDto(petCustomCreator.createPet(new Shelter()));

        assertEquals("Teto", petHeaderDto.getName());
        assertEquals("dog", petHeaderDto.getSpecies());
        assertEquals(Gender.FEMALE, petHeaderDto.getGender());
        assertEquals(HealthStatus.HEALTHY, petHeaderDto.getHealthStatus());
    }

    @Test
    public void testSetterAndGetters() {
        PetHeaderDto petHeaderDto = new PetHeaderDto();

        petHeaderDto.setId(2);
        petHeaderDto.setName("Fluffy");
        petHeaderDto.setSpecies("Cat");
        petHeaderDto.setGender(Gender.FEMALE);
        petHeaderDto.setHealthStatus(HealthStatus.HEALTHY);

        assertEquals(2, petHeaderDto.getId());
        assertEquals("Fluffy", petHeaderDto.getName());
        assertEquals("Cat", petHeaderDto.getSpecies());
        assertEquals(Gender.FEMALE, petHeaderDto.getGender());
        assertEquals(HealthStatus.HEALTHY, petHeaderDto.getHealthStatus());
    }

    @Test
    public void testEqualsAndHashCode() {
        PetHeaderDto petHeaderDto1 = new PetHeaderDto();
        petHeaderDto1.setId(3);
        petHeaderDto1.setName("Max");
        petHeaderDto1.setSpecies("Bird");
        petHeaderDto1.setGender(Gender.MALE);
        petHeaderDto1.setHealthStatus(HealthStatus.HEALTHY);

        PetHeaderDto petHeaderDto2 = new PetHeaderDto();
        petHeaderDto2.setId(3);
        petHeaderDto2.setName("Max");
        petHeaderDto2.setSpecies("Bird");
        petHeaderDto2.setGender(Gender.MALE);
        petHeaderDto2.setHealthStatus(HealthStatus.HEALTHY);

        assertEquals(petHeaderDto1, petHeaderDto2);
        assertEquals(petHeaderDto1.hashCode(), petHeaderDto2.hashCode());
    }

}