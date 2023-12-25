package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class SpiecesFilterCriteria implements FilterCriteria {

    private String species;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("species"), "%" + species + "%");
    }
}
