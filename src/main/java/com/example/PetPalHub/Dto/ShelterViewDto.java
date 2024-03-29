package com.example.PetPalHub.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShelterViewDto {

    private int id;
    private String name;
    private String phoneNumber;
    private LocationDto shelterLocation;
    private String description;
}
