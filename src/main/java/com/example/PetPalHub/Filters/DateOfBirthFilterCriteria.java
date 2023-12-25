package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class DateOfBirthFilterCriteria implements FilterCriteria {

    private Date date;

    @Override
    public Specification<Pet> meetCriteria() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("dateOfBirth"), "%" + strDate + "%");
    }
}
