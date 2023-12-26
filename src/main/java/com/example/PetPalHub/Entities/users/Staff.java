package com.example.PetPalHub.Entities.users;

import com.example.PetPalHub.Entities.Shelter.Shelter;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@Data
@NoArgsConstructor
@Table(name = "Staff")
@EqualsAndHashCode
public class Staff extends User {
    @OneToOne()
    @JoinColumn(name = "shelter_id", referencedColumnName = "id" )
    private Shelter shelter;
}