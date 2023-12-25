package com.example.PetPalHub.Mapper;

import com.example.PetPalHub.Dto.LocationDto;
import com.example.PetPalHub.Dto.ShelterViewDto;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShelterViewDtoMapper {

    @Autowired
    private ShelterRepositoryService shelterRepositoryService;

    public ShelterViewDto getDtoToView(Shelter shelter) {
        return ShelterViewDto.builder()
                .id(shelter.getId())
                .name(shelter.getName())
                .phoneNumber(shelter.getPhoneNumber())
                .shelterLocation(new LocationDto(shelter.getShelterLocation()))
                .build();
    }

    public Shelter getShelterWhenUpdate(ShelterViewDto shelterViewDto) {
        Shelter shelter=shelterRepositoryService.getShelterById(shelterViewDto.getId());
        shelter.setName(shelterViewDto.getName());
        shelter.setPhoneNumber(shelterViewDto.getPhoneNumber());
        shelter.setShelterLocation(getLocationFromLocationDto(shelterViewDto.getShelterLocation()));
        return shelter;
    }

    public Location getLocationFromLocationDto(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setCountry(locationDto.getCountry());
        location.setCity(locationDto.getCity());
        location.setAddress(locationDto.getAddress());
        return location;
    }
}
