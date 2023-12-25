package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Shelter.Location;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDto {

    private int id;
    private String country;
    private String city;
    private String address;

    public LocationDto(Location location) {
        this.id = location.getId();
        this.country = location.getCountry();
        this.city = location.getCity();
        this.address = location.getAddress();
    }

}
