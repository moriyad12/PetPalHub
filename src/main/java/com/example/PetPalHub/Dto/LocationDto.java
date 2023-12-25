package com.example.PetPalHub.Dto;

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

}
