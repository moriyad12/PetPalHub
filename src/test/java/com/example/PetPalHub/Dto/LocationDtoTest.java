package com.example.PetPalHub.Dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationDtoTest {

    @Test
    public void testNoArgsConstructor() {
        LocationDto locationDto = new LocationDto();

        assertEquals(0, locationDto.getId());
        assertNull(locationDto.getCountry());
        assertNull(locationDto.getCity());
        assertNull(locationDto.getAddress());
    }

    @Test
    public void testAllArgsConstructor() {
        LocationDto locationDto = new LocationDto(1, "Country1", "City1", "Address1");

        assertEquals(1, locationDto.getId());
        assertEquals("Country1", locationDto.getCountry());
        assertEquals("City1", locationDto.getCity());
        assertEquals("Address1", locationDto.getAddress());
    }

    @Test
    public void testSetterAndGetters() {
        LocationDto locationDto = new LocationDto();

        locationDto.setId(2);
        locationDto.setCountry("Country2");
        locationDto.setCity("City2");
        locationDto.setAddress("Address2");

        assertEquals(2, locationDto.getId());
        assertEquals("Country2", locationDto.getCountry());
        assertEquals("City2", locationDto.getCity());
        assertEquals("Address2", locationDto.getAddress());
    }

}