package com.example.PetPalHub.Entities.Relation;

import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.users.Adopter;

import java.io.Serializable;

public class AdopterPetApplicationComposite implements Serializable {

    private Adopter adopter;
    private Pet pet;

}
