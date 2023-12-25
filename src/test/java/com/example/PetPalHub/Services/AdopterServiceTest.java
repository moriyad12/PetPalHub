package com.example.PetPalHub.Services;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Mapper.PetViewDtoMapper;
import com.example.PetPalHub.RepositoriesService.Dashboard.DashboardRepositoryService;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class AdopterServiceTest {

    @Mock
    private PetRepositoryService petRepositoryService;

    @Mock
    private PetViewDtoMapper petViewDtoMapper;

    @Mock
    private ApplicationRepositoryService applicationRepositoryService;

    @Mock
    private DashboardRepositoryService dashboardRepositoryService;

    @InjectMocks
    private AdopterService adopterService;

    @Test
    public void testViewPet() {
        int petId = 1;
        Pet pet = new Pet();
        when(petRepositoryService.getPetById(anyInt())).thenReturn(pet);
        PetViewDto petViewDto = adopterService.viewPet(petId);
        Mockito.verify(petRepositoryService).getPetById(petId);
        Mockito.verify(petViewDtoMapper).getDtoToView(pet);
    }

    @Test
    public void testApplyForPet() {
        int petId = 1;
        int adopterId = 1;
        Pet pet = new Pet();
        when(petRepositoryService.getPetById(anyInt())).thenReturn(pet);
        adopterService.applyForPet(petId, adopterId);
        Mockito.verify(petRepositoryService).getPetById(petId);
        Mockito.verify(applicationRepositoryService).addApplication(adopterId, petId);
    }

    @Test
    public void testGetApplicationsByAdopterId() {
        int pageIndex = 1;
        int pageSize = 10;
        int adopterId = 1;
        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        when(dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId)).thenReturn(applicationDtoList);
        List<ApplicationDto> result = adopterService.getApplicationsByAdopterId(pageIndex, pageSize, adopterId);
        Mockito.verify(dashboardRepositoryService).getApplicationsPage(pageIndex, pageSize, adopterId);
        assertEquals(applicationDtoList, result);
    }
}