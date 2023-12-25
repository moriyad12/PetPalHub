package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Creators.ManagerCustomCreator;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Exceptions.UsersExceptions.ManagerNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ManagerRepositoryServiceTest {
    @Autowired
    private ManagerRepositoryService managerRepos;

    @Autowired
    private ManagerCustomCreator managerCreator;

    @Test
    public void testSuccessfulAddManager() {
        assertDoesNotThrow(() -> managerRepos.add(managerCreator.createManager()));
    }

    @Test
    public void testUnsuccessfulAddManager() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertThrows(AlreadyFoundException.class, () -> managerRepos.add(manager));
    }

    @Test
    public void testSuccessfulDeleteManagerById() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertDoesNotThrow(() -> managerRepos.deleteById(manager.getId()));
    }

    @Test
    public void testUnsuccessfulDeleteManagerById() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.deleteById(manager.getId() + 1));
    }

    @Test
    public void testSuccessfulDeleteManagerByEmail() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertDoesNotThrow(() -> managerRepos.deleteByEmail(manager.getEmail()));
    }

    @Test
    public void testUnsuccessfulDeleteManagerByEmail() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.deleteByEmail("not" + manager.getEmail()));
    }

    @Test
    public void testSuccessfulDeleteManager() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertDoesNotThrow(() -> managerRepos.delete(manager));
    }

    @Test
    public void testUnsuccessfulDeleteManager() {
        Manager manager = managerCreator.createManager();
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.delete(manager));
    }

    @Test
    public void testSuccessfulGetManagerById() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        Manager manager1 = managerRepos.findById(manager.getId());
        assertEquals(manager.getEmail(), manager1.getEmail());
    }

    @Test
    public void testUnSuccessfulGetManagerById() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.findById(manager.getId() + 1));
    }
    @Test
    public void testSuccessfulUpdateManager() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        manager.setEmail(manager.getId()+"@gmail.com");
        Manager manager1 = managerRepos.update(manager);
        assertEquals(manager.getEmail(), manager1.getEmail());
    }

    @Test
    public void testUnsuccessfulUpdateManager() {
        Manager manager = managerCreator.createManager();
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.update(manager));
    }

    @Test
    public void testSuccessfulGetManagerByEmail() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        Manager manager1 = managerRepos.findByEmail(manager.getEmail());
        assertEquals(manager.getEmail(), manager1.getEmail());
    }

    @Test
    public void testUnsuccessfulGetManagerByEmail() {
        Manager manager = managerCreator.createManager();
        managerRepos.add(manager);
        assertThrows(ManagerNotFoundException.class, () -> managerRepos.findByEmail("not"+manager.getEmail()));
    }

}
