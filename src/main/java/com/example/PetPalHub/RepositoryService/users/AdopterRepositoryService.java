package com.example.PetPalHub.RepositoryService.users;

import com.example.PetPalHub.Entity.users.Adopter;
import com.example.PetPalHub.Exceptions.UsersExceptions.AdopterNotFoundException;
import com.example.PetPalHub.Repository.users.AdopterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdopterRepositoryService {

    private final AdopterRepository adopterRepository;

    public void add(Adopter adopter) {
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
        try {
            adopterRepository.delete(adopter);
        } catch (Exception e) {
            throw new AdopterNotFoundException();
        }
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
