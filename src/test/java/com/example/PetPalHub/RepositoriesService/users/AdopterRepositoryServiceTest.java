package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Creators.AdopterCustomCreator;
import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Exceptions.UsersExceptions.AdopterNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AdopterRepositoryServiceTest {

    @Autowired
    private AdopterRepositoryService adopterRepos;

    @Autowired
    private AdopterCustomCreator adopterCreator;

    @Test
    public void testSuccessfulAddAdopter() {
        assertDoesNotThrow(() -> adopterRepos.add(adopterCreator.createAdopter()));
    }

    @Test
    public void testUnsuccessfulAddAdopter() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertThrows(AlreadyFoundException.class, () -> adopterRepos.add(adopter));
    }

    @Test
    public void testSuccessfulDeleteAdopterById() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertDoesNotThrow(() -> adopterRepos.deleteById(adopter.getId()));
    }

    @Test
    public void testUnsuccessfulDeleteAdopterById() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.deleteById(adopter.getId() + 1));
    }

    @Test
    public void testSuccessfulDeleteAdopterByEmail() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertDoesNotThrow(() -> adopterRepos.deleteByEmail(adopter.getEmail()));
    }

    @Test
    public void testUnsuccessfulDeleteAdopterByEmail() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.deleteByEmail("not" + adopter.getEmail()));
    }

    @Test
    public void testSuccessfulDeleteAdopter() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertDoesNotThrow(() -> adopterRepos.delete(adopter));
    }

    @Test
    public void testUnsuccessfulDeleteAdopter() {
        Adopter adopter = adopterCreator.createAdopter();
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.delete(adopter));
    }

    @Test
    public void testSuccessfulGetAdopterById() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        Adopter adopter1 = adopterRepos.findById(adopter.getId());
        assertEquals(adopter.getEmail(), adopter1.getEmail());
    }

    @Test
    public void testUnSuccessfulGetAdopterById() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.findById(adopter.getId() + 1));
    }
    @Test
    public void testSuccessfulUpdateAdopter() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        adopter.setEmail(adopter.getId()+"@gmail.com");
        Adopter adopter1 = adopterRepos.update(adopter);
        assertEquals(adopter.getEmail(), adopter1.getEmail());
    }

    @Test
    public void testUnsuccessfulUpdateAdopter() {
        Adopter adopter = adopterCreator.createAdopter();
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.update(adopter));
    }

    @Test
    public void testSuccessfulGetAdopterByEmail() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        Adopter adopter1 = adopterRepos.findByEmail(adopter.getEmail());
        assertEquals(adopter.getEmail(), adopter1.getEmail());
    }

    @Test
    public void testUnsuccessfulGetAdopterByEmail() {
        Adopter adopter = adopterCreator.createAdopter();
        adopterRepos.add(adopter);
        assertThrows(AdopterNotFoundException.class, () -> adopterRepos.findByEmail("not"+adopter.getEmail()));
    }

}
