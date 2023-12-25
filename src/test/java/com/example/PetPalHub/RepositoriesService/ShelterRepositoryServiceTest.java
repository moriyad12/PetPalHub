package com.example.PetPalHub.RepositoriesService;

import com.example.PetPalHub.Exceptions.ShelterNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShelterRepositoryServiceTest {

    @Autowired
    private ShelterRepositoryService shelterRepositoryService;

    @Test
    public void testGetShelterById() {
        Assertions.assertThrows(ShelterNotFoundException.class, () -> {
            shelterRepositoryService.getShelterById(1);
        });
    }



}