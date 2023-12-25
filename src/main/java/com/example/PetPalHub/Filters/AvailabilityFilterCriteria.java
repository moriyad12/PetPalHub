package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class AvailabilityFilterCriteria implements FilterCriteria {

    private Availability availability;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("availability"), availability);
    }
}
