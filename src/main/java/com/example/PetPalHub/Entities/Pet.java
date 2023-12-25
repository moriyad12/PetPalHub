package com.example.PetPalHub.Entities;

import com.example.PetPalHub.Entities.Enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private HealthStatus healthStatus;
    @Column(nullable = false)
    private Availability availability;
    private Breed breed;
    private Behaviour behaviour;
    @Column(nullable = false)
    private Date dateOfBirth;
    private String description;
    private String species;
    private String imagePath;
    @Column(nullable = false)
    private VaccineStatus vaccineStatus;
    @ManyToOne()
    @JoinColumn(
            name = "shelter_id",
            referencedColumnName = "id"
    )
    private Shelter shelter;
}
