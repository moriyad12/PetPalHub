package com.example.PetPalHub.RepositoriesService.users;

import com.example.PetPalHub.Entities.users.Adopter;
import com.example.PetPalHub.Exceptions.UsersExceptions.AdopterNotFoundException;
import com.example.PetPalHub.Exceptions.UsersExceptions.AlreadyFoundException;
import com.example.PetPalHub.Repositories.users.AdopterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdopterRepositoryService {

    private final AdopterRepository adopterRepository;

    public void add(Adopter adopter) {
        if(adopterRepository.findByEmail(adopter.getEmail()).isPresent()||adopterRepository.existsById(adopter.getId()))
            throw new AlreadyFoundException();
        adopterRepository.save(adopter);
    }
    public void deleteById(int id) {
        Optional<Adopter> adopter = adopterRepository.findById(id);
        if (adopter.isEmpty())
            throw new AdopterNotFoundException();
        adopterRepository.delete(adopter.get());
    }

    public void deleteByEmail(String email) {
        Optional<Adopter> adopter = adopterRepository.findByEmail(email);
        if (adopter.isEmpty())
            throw new AdopterNotFoundException();
        adopterRepository.delete(adopter.get());
    }

    public void delete(Adopter adopter) {
        if(!adopterRepository.existsById(adopter.getId()))
            throw new AdopterNotFoundException();
        adopterRepository.delete(adopter);
    }

    public Adopter findById(int id) {
        Optional<Adopter> adopter = adopterRepository.findById(id);
        if (adopter.isEmpty())
            throw new AdopterNotFoundException();
        return adopter.get();
    }

    public Adopter update(Adopter adopter) {
        Optional<Adopter> adopterOptional = adopterRepository.findById(adopter.getId());
        if (adopterOptional.isEmpty())
            throw new AdopterNotFoundException();
        adopterRepository.save(adopter);
        return adopter;
    }

    public Adopter findByEmail(String email) {
        Optional<Adopter> adopter = adopterRepository.findByEmail(email);
        if (adopter.isEmpty())
            throw new AdopterNotFoundException();
        return adopter.get();
    }

}
