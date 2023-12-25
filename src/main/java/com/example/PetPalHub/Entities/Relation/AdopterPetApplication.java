package com.example.PetPalHub.Entities.Relation;


import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.Adopter;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "Application")
@IdClass(AdopterPetApplicationComposite.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@Builder
public class AdopterPetApplication {

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "adopter_id", referencedColumnName = "id")
    private Adopter adopter;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;

    @Column(nullable = false)
    private Status status;


//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        AdopterPetApplication that = (AdopterPetApplication) o;
//
//        return adopter.equals(that.adopter) && pet.equals(that.pet) && status == that.status;
//    }

}
