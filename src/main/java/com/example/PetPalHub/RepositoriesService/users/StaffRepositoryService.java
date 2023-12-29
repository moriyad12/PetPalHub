package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Entities.users.Staff;
import com.example.PetPalHub.Exceptions.Shelter.ShelterNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.StaffNotFoundException;
import com.example.PetPalHub.Repositories.users.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffRepositoryService {

    private final StaffRepository staffRepository;

    public void add(Staff staff) {
        if (staffRepository.findByEmail(staff.getEmail()).isPresent() || staffRepository.existsById(staff.getId()))
            throw new AlreadyFoundException();
        staffRepository.save(staff);
    }

    public void deleteById(int id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty())
            throw new StaffNotFoundException();
        staffRepository.delete(staff.get());
    }

    public void deleteByEmail(String email) {
        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isEmpty())
            throw new StaffNotFoundException();
        staffRepository.delete(staff.get());
    }

    public void delete(Staff staff) {
        if (!staffRepository.existsById(staff.getId()))
            throw new StaffNotFoundException();
        staffRepository.delete(staff);
    }

    public Staff findById(int id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty())
            throw new StaffNotFoundException();
        return staff.get();
    }

    public Staff update(Staff staff) {
        Optional<Staff> staffOptional = staffRepository.findById(staff.getId());
        if (staffOptional.isEmpty())
            throw new StaffNotFoundException();
        staffRepository.save(staff);
        return staff;
    }

    public Staff findByEmail(String email) {
        Optional<Staff> staff = staffRepository.findByEmail(email);
        if (staff.isEmpty())
            throw new StaffNotFoundException();
        return staff.get();
    }

}