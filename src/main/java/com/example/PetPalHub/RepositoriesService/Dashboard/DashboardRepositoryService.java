package com.example.PetPalHub.RepositoriesService.Dashboard;

import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Entities.Shelter.Pet;
import com.example.PetPalHub.Entities.Shelter.Shelter;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageIndex;
import com.example.PetPalHub.Exceptions.DashboardException.InvalidPageSize;
import com.example.PetPalHub.RepositoriesService.Shelter.PetRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardRepositoryService {
    @Autowired
    private PetRepositoryService petRepositoryService;
    private int pageSize;
    private PageRequest pageWithRecords;
    private List<PetHeaderDto> petHeaderDtos;

    public List<PetHeaderDto> getFilteredPage(int pageIndex, int pageSize, Specification<Pet> specification) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "dateOfBirth"));
        petHeaderDtos = petRepositoryService.getFilteredPetsHeaderDto(pageWithRecords, specification);
        return petHeaderDtos;
    }

    public List<PetHeaderDto> getFilteredPageByShelter(Shelter shelter, int pageIndex, int pageSize, Specification<Pet> specification) {
        if (pageIndex < 0)
            throw new InvalidPageIndex();
        if (pageSize < 1)
            throw new InvalidPageSize();
        this.pageSize = pageSize;
        this.pageWithRecords = PageRequest.of(pageIndex, pageSize, Sort.by(Sort.Direction.DESC, "dateOfBirth"));
        petHeaderDtos = petRepositoryService.getFilteredPetsByShelterHeaderDto(shelter, pageWithRecords, specification);
        return petHeaderDtos;
    }

}
