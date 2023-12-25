package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.HealthStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PetViewDtoTest {

    @Test
    public void testConstructorAndGetters() {

        PetViewDto petViewDto = new PetViewDto();
        petViewDto.setId(1);
        petViewDto.setName("Bella");
        petViewDto.setSpecies("Dog");
        petViewDto.setGender(Gender.FEMALE);
        petViewDto.setHealthStatus(HealthStatus.HEALTHY);
        petViewDto.setAvailability(Availability.AVAILABLE);

        assertEquals(1, petViewDto.getId());
        assertEquals("Bella", petViewDto.getName());
        assertEquals("Dog", petViewDto.getSpecies());
        assertEquals(Gender.FEMALE, petViewDto.getGender());
        assertEquals(HealthStatus.HEALTHY, petViewDto.getHealthStatus());
        assertEquals(Availability.AVAILABLE, petViewDto.getAvailability());
    }

    @Test
    public void testEqualsAndHashCode() {
        PetViewDto petViewDto1 = new PetViewDto();
        petViewDto1.setId(2);
        petViewDto1.setName("Charlie");

        PetViewDto petViewDto2 = new PetViewDto();
        petViewDto2.setId(2);
        petViewDto2.setName("Charlie");
        assertEquals(petViewDto1, petViewDto2);
        assertEquals(petViewDto1.hashCode(), petViewDto2.hashCode());
    }
}