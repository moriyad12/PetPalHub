package com.example.PetPalHub.Mapper;

import com.example.PetPalHub.Dto.LocationDto;
import com.example.PetPalHub.Dto.ShelterViewDto;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class ShelterViewDtoMapperTest {

    @Mock
    private ShelterRepositoryService shelterRepositoryService;

    @InjectMocks
    private ShelterViewDtoMapper shelterViewDtoMapper;

    @Test
    public void testGetDtoToView() {
        Shelter shelter = new Shelter(1, "Test Shelter", "1234567890", new Location(1, "Country", "City", "Address"));
        ShelterViewDto shelterViewDto = shelterViewDtoMapper.getDtoToView(shelter);
        assertEquals(shelter.getId(), shelterViewDto.getId());
        assertEquals(shelter.getName(), shelterViewDto.getName());
        assertEquals(shelter.getPhoneNumber(), shelterViewDto.getPhoneNumber());
        assertEquals(shelter.getShelterLocation().getCountry(), shelterViewDto.getShelterLocation().getCountry());
        assertEquals(shelter.getShelterLocation().getCity(), shelterViewDto.getShelterLocation().getCity());
        assertEquals(shelter.getShelterLocation().getAddress(), shelterViewDto.getShelterLocation().getAddress());
    }

    @Test
    public void testGetShelterWhenUpdate() {
        ShelterViewDto shelterViewDto = new ShelterViewDto(1, "Updated Shelter", "9876543210", new LocationDto(1, "Updated Country", "Updated City", "Updated Address"));
        Shelter existingShelter = new Shelter(1, "Original Shelter", "1234567890", new Location(1, "Country", "City", "Address"));
        when(shelterRepositoryService.getShelterById(anyInt())).thenReturn(existingShelter);
        Shelter updatedShelter = shelterViewDtoMapper.getShelterWhenUpdate(shelterViewDto);
        assertEquals(existingShelter.getId(), updatedShelter.getId());
        assertEquals(shelterViewDto.getName(), updatedShelter.getName());
        assertEquals(shelterViewDto.getPhoneNumber(), updatedShelter.getPhoneNumber());
        assertEquals(shelterViewDto.getShelterLocation().getCountry(), updatedShelter.getShelterLocation().getCountry());
        assertEquals(shelterViewDto.getShelterLocation().getCity(), updatedShelter.getShelterLocation().getCity());
        assertEquals(shelterViewDto.getShelterLocation().getAddress(), updatedShelter.getShelterLocation().getAddress());
    }

    @Test
    public void testGetLocationFromLocationDto() {
        LocationDto locationDto = new LocationDto(1, "Country", "City", "Address");
        Location location = shelterViewDtoMapper.getLocationFromLocationDto(locationDto);
        assertEquals(locationDto.getId(), location.getId());
        assertEquals(locationDto.getCountry(), location.getCountry());
        assertEquals(locationDto.getCity(), location.getCity());
        assertEquals(locationDto.getAddress(), location.getAddress());
    }

}