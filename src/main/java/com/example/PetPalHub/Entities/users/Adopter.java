package com.example.PetPalHub.Entities.users;

import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@Table(name = "Adopter")
@EqualsAndHashCode
public class Adopter extends User {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adopter", fetch = FetchType.EAGER)
    private List<AdopterPetApplication> adopterPetApplicationList;
}
