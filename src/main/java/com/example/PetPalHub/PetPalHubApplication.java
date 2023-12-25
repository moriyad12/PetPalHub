package com.example.PetPalHub;

import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Filters.Enums.FilterTypes;
import com.example.PetPalHub.Filters.FilterRelationList;
import com.example.PetPalHub.Repositories.Relation.ApplicationRepository;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import com.example.PetPalHub.Services.FilterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PetPalHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetPalHubApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(FilterService filterService, PetRepositoryService petRepositoryService) {
        return runner -> {
//            Pet testPet = Pet.builder()
//                    .name("filterPet")
//                    .gender(Gender.MALE)
//                    .healthStatus(HealthStatus.HEALTHY)
//                    .availability(Availability.AVAILABLE)
//                    .behaviour(Behaviour.FRIENDLY)
//                    .dateOfBirth(new Date(System.currentTimeMillis()+1000L*1000L*1000L))
//                    .species("filterSpecies")
//                    .vaccineStatus(VaccineStatus.VACCINATED)
//                    .build();
//            petRepositoryService.addPet(testPet);
//            System.out.println("Pet added");
//            List<FilterRelationList<FilterTypes,Object>>list= new ArrayList<>();
//            list.add(new FilterRelationList<>(FilterTypes.DateOfBirth,new Date(System.currentTimeMillis()+1000L*1000L*1000L)));
//            List<Pet> filteredList = filterService.getFilteredPets(list);
//            System.out.println("Pet filtered");
        };
    }
}
