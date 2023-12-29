package com.example.PetPalHub.Dto;

import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplicationDto {
    private int adopterId;
    private int petId;
    private String adopterName;
    private String petName;
    private Status status;
    private String shelterName;
    private Date applicationDate;

    public ApplicationDto(AdopterPetApplication adopterPetApplication) {
        this.adopterId = adopterPetApplication.getAdopter().getId();
        this.petId = adopterPetApplication.getPet().getId();
        this.adopterName = adopterPetApplication.getAdopter().getFirstName() + " " + adopterPetApplication.getAdopter().getLastName();
        this.petName = adopterPetApplication.getPet().getName();
        this.status = adopterPetApplication.getStatus();
        this.shelterName = adopterPetApplication.getPet().getShelter().getName();
        this.applicationDate = adopterPetApplication.getApplicationDate();
    }
}
