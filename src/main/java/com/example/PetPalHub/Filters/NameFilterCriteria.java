package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class NameFilterCriteria implements FilterCriteria {

    private String name;

    @Override
    public Specification<Pet> meetCriteria() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
