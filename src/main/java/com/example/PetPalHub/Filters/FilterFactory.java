package com.example.PetPalHub.Filters;

import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Enums.Gender;
import com.example.PetPalHub.Entities.Enums.HealthStatus;
import com.example.PetPalHub.Entities.Enums.VaccineStatus;
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
            case Spieces -> new SpiecesFilterCriteria((String) filter.second);
        };
    }
}
