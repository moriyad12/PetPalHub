package com.example.PetPalHub.Filters;

import com.EventHorizon.EventHorizon.Entities.EventEntities.Event;
import com.example.PetPalHub.Entities.Enums.VaccineStatus;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class VaccineStatusFilterCriteria implements FilterCriteria {

    private VaccineStatus vaccineStatus;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vaccineStatus"), vaccineStatus);
    }
}
