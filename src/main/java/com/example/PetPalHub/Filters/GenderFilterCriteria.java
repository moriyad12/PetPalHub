package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class GenderFilterCriteria implements FilterCriteria {

    private Gender gender;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("gender"), gender);
    }
}
