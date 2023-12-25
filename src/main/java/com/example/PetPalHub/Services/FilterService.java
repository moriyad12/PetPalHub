package com.example.PetPalHub.Services;


import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Filters.Enums.FilterTypes;
import com.example.PetPalHub.Filters.FilterCriteria;
import com.example.PetPalHub.Filters.FilterFactory;
import com.example.PetPalHub.Filters.FilterRelationList;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final FilterFactory filterFactory;
    private final PetRepositoryService petReposit;

    private Specification<Pet> getSpecificationForAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("id"), -1);
    }

    public Specification<Pet> getSpecifications(List<FilterRelationList<FilterTypes, Object>> filters) {
        Specification<Pet> specification = getSpecificationForAll();
        for (FilterRelationList<FilterTypes, Object> filter : filters) {
            FilterCriteria filterCriteria = filterFactory.getFilterCriteria(filter);
            specification = specification.and(filterCriteria.meetCriteria());
        }
        return specification;
    }

    public List<Pet> getFilteredPets(List<FilterRelationList<FilterTypes, Object>> filters) {
        return petReposit.getAllPets(getSpecifications(filters));
    }

//    public List<Pet> getFilteredEventHeadersList(int pageIndex, int pageSize, List<FilterRelationList<FilterTypes, Object>> filters) {
//        return this.dashboardRepositoryService.getFilteredPage(pageIndex, pageSize, getSpecifications(filters));
//    }

}
