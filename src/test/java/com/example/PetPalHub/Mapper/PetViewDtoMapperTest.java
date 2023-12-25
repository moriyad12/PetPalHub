package com.example.PetPalHub.Mapper;

import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.ShelterRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class PetViewDtoMapperTest {

    @Mock
    private ShelterRepositoryService shelterRepositoryService;

    @Mock
    private PetRepositoryService petRepositoryService;

    @InjectMocks
    private PetViewDtoMapper petViewDtoMapper;


    @Test
    public void testGetDtoToView() {
        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("Buddy");
        pet.setShelter(new Shelter());
        when(shelterRepositoryService.getShelterById(anyInt())).thenReturn(new Shelter());
        PetViewDto petViewDto = petViewDtoMapper.getDtoToView(pet);
        assertEquals(1, petViewDto.getId());
        assertEquals("Buddy", petViewDto.getName());
    }

    @Test
    public void testGetPetWhenCreate() {
        PetViewDto petViewDto = new PetViewDto();
        petViewDto.setName("Buddy");
        when(shelterRepositoryService.getShelterById(anyInt())).thenReturn(new Shelter());
        Pet pet = petViewDtoMapper.getPetWhenCreate(petViewDto);
        assertEquals("Buddy", pet.getName());
    }

    @Test
    public void testGetPetWhenUpdate() {
        PetViewDto petViewDto = new PetViewDto();
        petViewDto.setId(1);
        petViewDto.setName("Buddy");
        Pet tempPet = new Pet();
        tempPet.setId(1);
        when(petRepositoryService.getPetById(anyInt())).thenReturn(tempPet);
        Pet pet = petViewDtoMapper.getPetWhenUpdate(petViewDto);
        assertEquals(1, pet.getId());
        assertEquals("Buddy", pet.getName());
    }
}