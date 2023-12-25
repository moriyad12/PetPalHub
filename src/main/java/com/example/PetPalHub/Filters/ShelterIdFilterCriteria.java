package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
@AllArgsConstructor
public class ShelterIdFilterCriteria implements FilterCriteria {

    private int id;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shelter").get("id"), id);
    }

}
