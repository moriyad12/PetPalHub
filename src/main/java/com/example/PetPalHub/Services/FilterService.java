package com.example.PetPalHub.Services;


import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Entities.Enums.Availability;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Filters.Enums.FilterTypes;
import com.example.PetPalHub.Filters.FilterCriteria;
import com.example.PetPalHub.Filters.FilterFactory;
import com.example.PetPalHub.Filters.FilterRelationList;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilterService {

    @Autowired
    private DashboardRepositoryService dashboardRepositoryService;
    @Autowired
    private ShelterRepositoryService shelterRepositoryService;
    private final FilterFactory filterFactory;
    private final PetRepositoryService petRepository;

    private Specification<Pet> getSpecificationForAll() {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("id"), -1);
    }

    private Specification<Pet> getSpecifications(List<FilterRelationList<FilterTypes, Object>> filters) {
        Specification<Pet> specification = getSpecificationForAll();
        for (FilterRelationList<FilterTypes, Object> filter : filters) {
            FilterCriteria filterCriteria = filterFactory.getFilterCriteria(filter);
            specification = specification.and(filterCriteria.meetCriteria());
        }
        return specification;
    }

    public List<Pet> getFilteredPets(List<FilterRelationList<FilterTypes, Object>> filters) {
        return petRepository.getAllPets(getSpecifications(filters));
    }

    public List<PetHeaderDto> getFilteredPetsHeadersListByShelterId(int shelterId, int pageIndex, int pageSize, List<FilterRelationList<FilterTypes, Object>> filters) {
        filters.add(new FilterRelationList<>(FilterTypes.ShelterId, shelterId));
        return this.dashboardRepositoryService.getFilteredPetsPage(pageIndex, pageSize, getSpecifications(filters));
    }

    public List<PetHeaderDto> getFilteredAvailablePetsHeadersList(int pageIndex, int pageSize, List<FilterRelationList<FilterTypes, Object>> filters) {
        filters.add(new FilterRelationList<>(FilterTypes.Availability, Availability.AVAILABLE));
        return this.dashboardRepositoryService.getFilteredPetsPage(pageIndex, pageSize, getSpecifications(filters));
    }
}
