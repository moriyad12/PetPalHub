package com.example.PetPalHub;

import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Enums.*;
import com.example.PetPalHub.Entities.Shelter.Location;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Repositories.Relation.ApplicationRepository;
import com.example.PetPalHub.Repositories.Shelter.ShelterRepository;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PetPalHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetPalHubApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AdopterRepositoryService adopterRepos, PetRepositoryService petRepositoryService,
                                               ApplicationRepository applicationRepository, ShelterRepository shelterRepository) {
        return runner -> {
//            Adopter adopter = Adopter.builder()
//                    .firstName("Adopter")
//                    .lastName("Test")
//                    .email("adopter" + "@test.com")
//                    .phoneNumber("1234567890")
//                    .gender(Gender.MALE)
//                    .role(Role.ADOPTER)
//                    .password("password")
//                    .build();
//            adopterRepos.add(adopter);
//
//
//            Shelter shelter = Shelter.builder()
//                    .name("abu ryad")
//                    .shelterLocation(Location.builder().country("bianky").city("paradise").build())
//                    .build();
//            shelterRepository.save(shelter);
//            Pet pet = Pet.builder()
//                    .name("faris")
//                    .breed(Breed.BEAGLE)
//                    .availability(Availability.AVAILABLE)
//                    .dateOfBirth(new Date())
//                    .gender(Gender.FEMALE)
//                    .healthStatus(HealthStatus.HEALTHY)
//                    .behaviour(Behaviour.FRIENDLY)
//                    .vaccineStatus(VaccineStatus.VACCINATED)
//                    .description("cute")
//                    .species("dog")
//                    .shelter(shelter)
//                    .build();
//
//
//            petRepositoryService.addPet(pet);
//            adopterRepos.add(adopter);
//            AdopterPetApplication adopterPetApplication = new AdopterPetApplication(adopter, pet, Status.PENDING);
//            applicationRepository.save(adopterPetApplication);
//
//            List<AdopterPetApplication> adopterPetApplicationList=applicationRepository.findByPet(pet);
//            System.out.println();
//
//            List<AdopterPetApplication> adopterPetApplicationList2=applicationRepository.findByAdopter(adopter);
//            System.out.println();
        };
    }
}
