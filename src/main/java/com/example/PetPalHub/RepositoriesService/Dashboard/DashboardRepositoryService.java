package com.example.PetPalHub.RepositoriesService.Dashboard;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Entities.Enums.Status;
import com.example.PetPalHub.Entities.Relation.AdopterPetApplication;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageIndex;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageSize;
import com.example.PetPalHub.RepositoriesService.Relation.ApplicationRepositoryService;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardRepositoryService {
    @Autowired
    private PetRepositoryService petRepositoryService;
    @Autowired
    private ApplicationRepositoryService applicationRepositoryService;
    private int pageSize;
    private PageRequest pageWithRecords;
    private List<PetHeaderDto> petHeaderDtos;
    private List<ApplicationDto> applicationDtos;

    public List<PetHeaderDto> getFilteredPetsPage(int pageIndex, int pageSize, Specification<Pet> specification) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "dateOfBirth"));
        petHeaderDtos = petRepositoryService.getFilteredPetsHeaderDto(pageWithRecords, specification);
        return petHeaderDtos;
    }

    public List<ApplicationDto> getApplicationsPage(int pageIndex, int pageSize, int adopterId) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize);
        applicationDtos = applicationRepositoryService.getApplicationDtos(adopterId, pageWithRecords);
        return applicationDtos;
    }

    public List<ApplicationDto> getApplicationByShelterIDAndStatusPage(int pageIndex, int pageSize, int shelterId, Status status) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize);
        applicationDtos = Transfer(applicationRepositoryService.getByPet_Shelter_IdAndStatus(shelterId, status,pageWithRecords));
        return applicationDtos;
    }
    public List<ApplicationDto> Transfer(List<AdopterPetApplication> applications) {
        List<ApplicationDto> applicationDtoList = new ArrayList<>();
        for (AdopterPetApplication adopterPetApplication : applications) {
            applicationDtoList.add(new ApplicationDto(adopterPetApplication));
        }
        return applicationDtoList;
    }
}
