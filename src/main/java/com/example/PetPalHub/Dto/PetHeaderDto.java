package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.HealthStatus;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PetHeaderDto {

    private int id;
    private String name;
    private String species;
    private Gender gender;
    private HealthStatus healthStatus;
    public PetHeaderDto(Pet pet){
        this.id = pet.getId();
        this.name = pet.getName();
        this.gender = pet.getGender();
        this.healthStatus = pet.getHealthStatus();
        this.species = pet.getSpecies();
    }
}
