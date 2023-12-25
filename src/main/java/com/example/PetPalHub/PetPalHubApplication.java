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

        };
    }
}
