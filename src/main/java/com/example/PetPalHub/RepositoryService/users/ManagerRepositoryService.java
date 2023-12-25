package com.example.PetPalHub.RepositoryService.users;

import com.example.PetPalHub.Entity.users.Manager;
import com.example.PetPalHub.Exceptions.UsersExceptions.ManagerNotFoundException;
import com.example.PetPalHub.Repository.users.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerRepositoryService {

    private final ManagerRepository managerRepository;

    public void add(Manager manager) {
        managerRepository.save(manager);
    }


    public void deleteById(int id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty())
            throw new ManagerNotFoundException();
        managerRepository.delete(manager.get());
    }

    public void deleteByEmail(String email) {
        Optional<Manager> manager = managerRepository.findByEmail(email);
        if (manager.isEmpty())
            throw new ManagerNotFoundException();
        managerRepository.delete(manager.get());
    }

    public void delete(Manager manager) {
        try {
            managerRepository.delete(manager);
        } catch (Exception e) {
            throw new ManagerNotFoundException();
        }
    }

    public Manager findById(int id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty())
            throw new ManagerNotFoundException();
        return manager.get();
    }

    public Manager update(Manager manager) {
        Optional<Manager> managerOptional = managerRepository.findById(manager.getId());
        if (managerOptional.isEmpty())
            throw new ManagerNotFoundException();
        managerRepository.save(manager);
        return manager;
    }

    public Manager findByEmail(String email) {
        Optional<Manager> manager = managerRepository.findByEmail(email);
        if (manager.isEmpty())
            throw new ManagerNotFoundException();
        return manager.get();
    }

}
