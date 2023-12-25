package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.ShelterViewDto;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Mapper.ShelterViewDtoMapper;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    @Autowired
    ShelterViewDtoMapper shelterViewDtoMapper;
    @Autowired
    ShelterRepositoryService shelterRepositoryService;

    public void updateShelter(ShelterViewDto shelterViewDto) {
        Shelter shelter = shelterViewDtoMapper.getShelterWhenUpdate(shelterViewDto);
        shelterRepositoryService.updateShelter(shelter);
    }
}
