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

    private String petName;
    private Status status;
    private Date applicationDate;

    public ApplicationDto(AdopterPetApplication adopterPetApplication) {
        this.petName = adopterPetApplication.getPet().getName();
        this.status = adopterPetApplication.getStatus();
        this.applicationDate = adopterPetApplication.getApplicationDate();
    }
}
