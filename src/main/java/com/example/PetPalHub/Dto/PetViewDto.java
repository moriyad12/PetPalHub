package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.*;
import lombok.*;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PetViewDto {

    private int id;
    private String name;
    private String species;
    private Gender gender;
    private HealthStatus healthStatus;
    private Availability availability;
    private Breed breed;
    private Behaviour behaviour;
    private Date dateOfBirth;
    private String description;
    private VaccineStatus vaccineStatus;
    private String imagePath;
    private int shelterId;
    private String shelterName;
}
