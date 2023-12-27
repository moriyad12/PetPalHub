package com.example.PetPalHub.Controller;

import com.example.PetPalHub.Dto.ApplicationDto;
import com.example.PetPalHub.Dto.FilterDto;
import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Services.AdopterService;
import com.example.PetPalHub.Services.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adopter/")
@CrossOrigin("*")
public class AdopterController {

    @Autowired
    private AdopterService adopterService;
    @Autowired
    private FilterService filterService;

    @GetMapping("petForUser/{petId}")
    public ResponseEntity<PetViewDto> getPetForUser(@PathVariable int petId) {
        PetViewDto petViewDto = adopterService.viewPet(petId);
        return new ResponseEntity<>(petViewDto, HttpStatus.OK);
    }

    @PostMapping("applyForPet/{petId}/{adopterId}")
    public ResponseEntity<Void> applyForPet(@PathVariable int petId, @PathVariable int adopterId) {
        adopterService.applyForPet(petId, adopterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getApplications/{pageIndex}/{pageSize}/{adopterId}")
    public ResponseEntity<List<ApplicationDto>> getApplications(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int adopterId) {
        List<ApplicationDto> applicationDtoList = adopterService.getApplicationsByAdopterId(pageIndex, pageSize, adopterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }

    @PostMapping("dashboard/{pageIndex}/{pageSize}")
    public ResponseEntity<List<PetHeaderDto>> getAvailablePetsHeaders(@PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<PetHeaderDto> petHeaderDtos = filterService.getFilteredAvailablePetsHeadersList(pageIndex, pageSize, filterDto.getFilters());
        return new ResponseEntity<>(petHeaderDtos, HttpStatus.OK);
    }
}
