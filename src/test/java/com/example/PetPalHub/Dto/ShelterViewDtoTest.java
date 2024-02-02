package com.example.PetPalHub.Dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShelterViewDtoTest {

    @Test
    public void testBuilder() {
        ShelterViewDto shelterViewDto = ShelterViewDto.builder()
                .id(1)
                .name("Happy Paws Shelter")
                .phoneNumber("123-456-7890")
                .shelterLocation(LocationDto.builder().country("Country").city("City").address("Address").build())
                .build();

        assertEquals(1, shelterViewDto.getId());
        assertEquals("Happy Paws Shelter", shelterViewDto.getName());
        assertEquals("123-456-7890", shelterViewDto.getPhoneNumber());
        assertNotNull(shelterViewDto.getShelterLocation());
        assertEquals("Country", shelterViewDto.getShelterLocation().getCountry());
        assertEquals("City", shelterViewDto.getShelterLocation().getCity());
        assertEquals("Address", shelterViewDto.getShelterLocation().getAddress());
    }

    @Test
    public void testNoArgsConstructor() {
        ShelterViewDto shelterViewDto = new ShelterViewDto();

        assertEquals(0, shelterViewDto.getId());
        assertNull(shelterViewDto.getName());
        assertNull(shelterViewDto.getPhoneNumber());
        assertNull(shelterViewDto.getShelterLocation());
    }

    @Test
    public void testAllArgsConstructor() {
        LocationDto locationDto = LocationDto.builder().country("Country1").city("City1").address("Address1").build();
        ShelterViewDto shelterViewDto = new ShelterViewDto(1, "Happy Paws Shelter", "123-456-7890", locationDto,"");

        assertEquals(1, shelterViewDto.getId());
        assertEquals("Happy Paws Shelter", shelterViewDto.getName());
        assertEquals("123-456-7890", shelterViewDto.getPhoneNumber());
        assertNotNull(shelterViewDto.getShelterLocation());
        assertEquals("Country1", shelterViewDto.getShelterLocation().getCountry());
        assertEquals("City1", shelterViewDto.getShelterLocation().getCity());
        assertEquals("Address1", shelterViewDto.getShelterLocation().getAddress());
    }

    @Test
    public void testDataAnnotation() {
        ShelterViewDto shelterViewDto = new ShelterViewDto();
        shelterViewDto.setId(2);
        shelterViewDto.setName("Second Shelter");
        shelterViewDto.setPhoneNumber("987-654-3210");
        shelterViewDto.setShelterLocation(LocationDto.builder().country("Country2").city("City2").address("Address2").build());

        assertEquals(2, shelterViewDto.getId());
        assertEquals("Second Shelter", shelterViewDto.getName());
        assertEquals("987-654-3210", shelterViewDto.getPhoneNumber());
        assertNotNull(shelterViewDto.getShelterLocation());
        assertEquals("Country2", shelterViewDto.getShelterLocation().getCountry());
        assertEquals("City2", shelterViewDto.getShelterLocation().getCity());
        assertEquals("Address2", shelterViewDto.getShelterLocation().getAddress());
    }
}