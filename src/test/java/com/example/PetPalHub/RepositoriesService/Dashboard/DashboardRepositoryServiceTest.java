package com.example.PetPalHub.RepositoriesService.Dashboard;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageIndex;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageSize;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class DashboardRepositoryServiceTest {

    @Mock
    private PetRepositoryService petRepositoryService;

    @Mock
    private ApplicationRepositoryService applicationRepositoryService;

    @InjectMocks
    private DashboardRepositoryService dashboardRepositoryService;


    @Test
    public void testGetApplicationsPage() {
        int pageIndex = 1;
        int pageSize = 10;
        int adopterId = 1;
        List<ApplicationDto> applicationDtos = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        when(applicationRepositoryService.getApplicationDtos(adopterId, pageRequest)).thenReturn(applicationDtos);
        List<ApplicationDto> result = dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId);
        Mockito.verify(applicationRepositoryService).getApplicationDtos(adopterId, pageRequest);
        assertEquals(applicationDtos, result);
    }
    @Test
    public void testGetApplicationsPageInvalidPageIndex() {
        int pageIndex = -1;
        int pageSize = 10;
        int adopterId = 1;
        assertThrows(InvalidPageIndex.class, () -> dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId));
    }

    @Test
    public void testGetApplicationsPageInvalidPageSize() {
        int pageIndex = 1;
        int pageSize = 0;
        int adopterId = 1;
        assertThrows(InvalidPageSize.class, () -> dashboardRepositoryService.getApplicationsPage(pageIndex, pageSize, adopterId));
    }

}