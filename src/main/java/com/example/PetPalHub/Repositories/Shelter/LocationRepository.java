package com.example.PetPalHub.Repositories.Shelter;

import com.example.PetPalHub.Entities.Shelter.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
