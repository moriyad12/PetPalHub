package com.example.PetPalHub.RepositoryService.users;

import com.example.PetPalHub.Creators.AdopterCustomCreator;
import com.example.PetPalHub.Entity.users.Adopter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class AdopterRepositoryServiceTest {

    @Autowired
    private AdopterRepositoryService adopterRepos;

    @Autowired
    private AdopterCustomCreator adopterCreator;

}
