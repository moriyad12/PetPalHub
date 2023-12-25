package com.example.PetPalHub.RepositoryService.users;

import com.example.PetPalHub.Entity.users.Staff;
import com.example.PetPalHub.Exceptions.UsersExceptions.StaffNotFoundException;
import com.example.PetPalHub.Repository.users.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffRepositoryService {

    private final StaffRepository staffRepository;

    public void add(Staff staff) {
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
        try {
            staffRepository.delete(staff);
        } catch (Exception e) {
            throw new StaffNotFoundException();
        }
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