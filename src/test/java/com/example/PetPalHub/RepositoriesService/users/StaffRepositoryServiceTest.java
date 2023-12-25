package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Creators.StaffCustomCreator;
import com.example.PetPalHub.Entities.users.Staff;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.StaffNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StaffRepositoryServiceTest {
    @Autowired
    private StaffRepositoryService staffRepos;

    @Autowired
    private StaffCustomCreator staffCreator;

    @Test
    public void testSuccessfulAddStaff() {
        assertDoesNotThrow(() -> staffRepos.add(staffCreator.createStaff()));
    }

    @Test
    public void testUnsuccessfulAddStaff() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertThrows(AlreadyFoundException.class, () -> staffRepos.add(staff));
    }

    @Test
    public void testSuccessfulDeleteStaffById() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertDoesNotThrow(() -> staffRepos.deleteById(staff.getId()));
    }

    @Test
    public void testUnsuccessfulDeleteStaffById() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertThrows(StaffNotFoundException.class, () -> staffRepos.deleteById(staff.getId() + 1));
    }

    @Test
    public void testSuccessfulDeleteStaffByEmail() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertDoesNotThrow(() -> staffRepos.deleteByEmail(staff.getEmail()));
    }

    @Test
    public void testUnsuccessfulDeleteStaffByEmail() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertThrows(StaffNotFoundException.class, () -> staffRepos.deleteByEmail("not" + staff.getEmail()));
    }

    @Test
    public void testSuccessfulDeleteStaff() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertDoesNotThrow(() -> staffRepos.delete(staff));
    }

    @Test
    public void testUnsuccessfulDeleteStaff() {
        Staff staff = staffCreator.createStaff();
        assertThrows(StaffNotFoundException.class, () -> staffRepos.delete(staff));
    }

    @Test
    public void testSuccessfulGetStaffById() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        Staff staff1 = staffRepos.findById(staff.getId());
        assertEquals(staff.getEmail(), staff1.getEmail());
    }

    @Test
    public void testUnSuccessfulGetStaffById() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertThrows(StaffNotFoundException.class, () -> staffRepos.findById(staff.getId() + 1));
    }
    @Test
    public void testSuccessfulUpdateStaff() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        staff.setEmail(staff.getId()+"@gmail.com");
        Staff staff1 = staffRepos.update(staff);
        assertEquals(staff.getEmail(), staff1.getEmail());
    }

    @Test
    public void testUnsuccessfulUpdateStaff() {
        Staff staff = staffCreator.createStaff();
        assertThrows(StaffNotFoundException.class, () -> staffRepos.update(staff));
    }

    @Test
    public void testSuccessfulGetStaffByEmail() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        Staff staff1 = staffRepos.findByEmail(staff.getEmail());
        assertEquals(staff.getEmail(), staff1.getEmail());
    }

    @Test
    public void testUnsuccessfulGetStaffByEmail() {
        Staff staff = staffCreator.createStaff();
        staffRepos.add(staff);
        assertThrows(StaffNotFoundException.class, () -> staffRepos.findByEmail("not"+staff.getEmail()));
    }

}
