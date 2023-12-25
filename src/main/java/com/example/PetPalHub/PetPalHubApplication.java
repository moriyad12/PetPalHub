package com.example.PetPalHub;

import com.example.PetPalHub.RepositoriesService.users.AdopterRepositoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PetPalHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetPalHubApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AdopterRepositoryService adopterRepos) {
        return runner -> {

        };
    }
}
