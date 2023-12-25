package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Shelter.Pet;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
public class DateOfBirthFilterCriteria implements FilterCriteria {

    private Date date;

    @Override
    public Specification<Pet> meetCriteria() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date", e);
        }
        Timestamp timestamp = new Timestamp(parsedDate.getTime());

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("dateOfBirth"), timestamp);
    }
}
