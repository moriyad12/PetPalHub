package com.example.PetPalHub.Filters;


import com.example.PetPalHub.Entities.Enums.Behaviour;
import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class BehaviourFilterCriteria implements FilterCriteria {

    private Behaviour behaviour;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("behaviour"), behaviour);
    }
}
