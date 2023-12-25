package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import org.springframework.data.jpa.domain.Specification;

public interface FilterCriteria {

    Specification<Pet> meetCriteria();
}
