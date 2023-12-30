package com.example.PetPalHub.Filter;

import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Filters.Enums.FilterTypes;
import com.example.PetPalHub.Filters.FilterRelationList;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.Services.FilterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class FilterServiceTest {

    @Autowired
    private FilterService filterService;
    @Autowired
    private PetRepositoryService petReposit;
    private Pet testPet;
    private void initializeTestPet(){
        testPet = Pet.builder()
                .name("filterPet")
                .gender(Gender.MALE)
                .healthStatus(HealthStatus.HEALTHY)
                .availability(Availability.AVAILABLE)
                .behaviour(Behaviour.FRIENDLY)
                .dateOfBirth(new Date(System.currentTimeMillis()+1000L*1000L*1000L))
                .species("filterSpecies")
                .vaccineStatus(VaccineStatus.VACCINATED)
                .build();
    }

    @Test
    public void testFilterServiceForAllConditions(){
        initializeTestPet();
        petReposit.addPet(testPet);
        List<FilterRelationList<FilterTypes,Object>>list= new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.Name,"filterPet"));
        list.add(new FilterRelationList<>(FilterTypes.Gender,Gender.MALE.toString()));
        list.add(new FilterRelationList<>(FilterTypes.HealthStatus,HealthStatus.HEALTHY.toString()));
        list.add(new FilterRelationList<>(FilterTypes.Availability,Availability.AVAILABLE));
        list.add(new FilterRelationList<>(FilterTypes.Behaviour,Behaviour.FRIENDLY.toString()));
        list.add(new FilterRelationList<>(FilterTypes.DateOfBirth,new Date(System.currentTimeMillis()+1000L*1000L*1000L)));
        list.add(new FilterRelationList<>(FilterTypes.Species,"filterSpecies"));
        list.add(new FilterRelationList<>(FilterTypes.VaccineStatus,VaccineStatus.VACCINATED.toString()));
        List<Pet> filteredList = filterService.getFilteredPets(list);
        Assertions.assertEquals(testPet.getId(),filteredList.get(filteredList.size()-1).getId());
    }

    @Test
    public void testFilterServiceForAllConditionsWithNoMatches(){
        initializeTestPet();
        petReposit.addPet(testPet);
        List<FilterRelationList<FilterTypes,Object>>list= new ArrayList<>();
        list.add(new FilterRelationList<>(FilterTypes.Name,"filterPet2"));
        list.add(new FilterRelationList<>(FilterTypes.Gender,Gender.MALE.toString()));
        list.add(new FilterRelationList<>(FilterTypes.HealthStatus,HealthStatus.HEALTHY.toString()));
        list.add(new FilterRelationList<>(FilterTypes.Availability,Availability.AVAILABLE));
        list.add(new FilterRelationList<>(FilterTypes.Behaviour,Behaviour.FRIENDLY.toString()));
        list.add(new FilterRelationList<>(FilterTypes.DateOfBirth,new Date(System.currentTimeMillis()+1000L*1000L*1000L)));
        list.add(new FilterRelationList<>(FilterTypes.Species,"filterSpecies"));
        list.add(new FilterRelationList<>(FilterTypes.VaccineStatus,VaccineStatus.VACCINATED.toString()));
        List<Pet> filteredList = filterService.getFilteredPets(list);
        Assertions.assertEquals(0,filteredList.size());
    }
}
