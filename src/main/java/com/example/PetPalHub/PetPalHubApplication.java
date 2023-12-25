package com.example.PetPalHub;

import com.example.PetPalHub.Entity.enums.Gender;
import com.example.PetPalHub.Entity.enums.Role;
import com.example.PetPalHub.Entity.users.Adopter;
import com.example.PetPalHub.Repository.users.AdopterRepository;
import com.example.PetPalHub.Repository.users.ManagerRepository;
import com.example.PetPalHub.Repository.users.StaffRepository;
import com.example.PetPalHub.RepositoryService.users.AdopterRepositoryService;
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
            Adopter adopter1 = Adopter.builder().email("ahmed").role(Role.ADOPTER).firstName("ahmed").lastName("ahmed").password("pass").gender(Gender.MALE).phoneNumber("012").build();
            adopterRepos.add(adopter1);
            Adopter adopter = adopterRepos.findByEmail("ahmed");
            System.out.println(adopter);

        };
    }
}
