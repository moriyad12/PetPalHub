package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Filters.Enums.FilterTypes;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FilterFactory {

    public FilterCriteria getFilterCriteria(FilterRelationList<FilterTypes, Object> filter) {
        return switch (filter.first) {
            case Gender -> new GenderFilterCriteria((Gender) filter.second);
            case HealthStatus -> new HealthStatusFilterCriteria((HealthStatus) filter.second);
            case Name -> new NameFilterCriteria((String) filter.second);
            case Availability -> new AvailabilityFilterCriteria((Availability) filter.second);
            case DateOfBirth -> new DateOfBirthFilterCriteria((Date) filter.second);
            case VaccineStatus -> new VaccineStatusFilterCriteria((VaccineStatus) filter.second);
            case Species -> new SpiecesFilterCriteria((String) filter.second);
            case Behaviour -> new BehaviourFilterCriteria((Behaviour) filter.second);
        };
    }
}
