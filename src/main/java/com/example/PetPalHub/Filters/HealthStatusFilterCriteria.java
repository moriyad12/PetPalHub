package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Enums.HealthStatus;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class HealthStatusFilterCriteria implements FilterCriteria {

    private HealthStatus healthStatus;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("healthStatus"),  healthStatus);
    }
}
