package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Entities.users.Manager;
import com.example.PetPalHub.Entities.users.Staff;
import com.example.PetPalHub.Exceptions.Shelter.ShelterNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.ManagerNotFoundException;
import com.example.PetPalHub.Repositories.users.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerRepositoryService {

    private final ManagerRepository managerRepository;

    public void add(Manager manager) {
        if(managerRepository.findByEmail(manager.getEmail()).isPresent()||managerRepository.existsById(manager.getId()))
            throw new AlreadyFoundException();
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
        if (!managerRepository.existsById(manager.getId()))
            throw new ManagerNotFoundException();
        managerRepository.delete(manager);
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

    public Shelter findShelterByManagerId(int id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isEmpty())
            throw new ShelterNotFoundException();
        return manager.get().getShelter();
    }
}
