package com.example.PetPalHub.Controller;

import com.example.PetPalHub.Dto.*;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Mapper.ShelterViewDtoMapper;
import com.example.PetPalHub.Services.AdopterService;
import com.example.PetPalHub.Services.FilterService;
import com.example.PetPalHub.Services.MasterControlServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/master/")
@CrossOrigin("*")
public class MasterController {

    @Autowired
    private MasterControlServices masterControlServices;
    @Autowired
    private FilterService filterService;

    @Autowired
    ShelterViewDtoMapper shelterViewDtoMapper;

    @PostMapping("acceptApplication/{petId}/{adopterId}")
    public ResponseEntity<Void> acceptApplication(@PathVariable int petId, @PathVariable int adopterId) {
        masterControlServices.acceptApplication(adopterId, petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("rejectApplication/{petId}/{adopterId}")
    public ResponseEntity<Void> rejectApplication(@PathVariable int petId, @PathVariable int adopterId) {
        masterControlServices.rejectApplication(adopterId, petId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getPendingApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getPendingApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getPendingApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }

    @GetMapping("getAcceptedApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getAcceptedApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getAcceptedApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }

    @GetMapping("getRejectedApplicationByShelterID/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<ApplicationDto>> getRejectedApplicationByShelterID(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable int shelterId) {
        List<ApplicationDto> applicationDtoList = masterControlServices.getRejectedApplicationByShelterID(pageIndex, pageSize, shelterId);
        return new ResponseEntity<>(applicationDtoList, HttpStatus.OK);
    }


    @PostMapping("getFilteredPetsHeadersListByShelterId/{pageIndex}/{pageSize}/{shelterId}")
    public ResponseEntity<List<PetHeaderDto>> getFilteredPetsHeadersListByShelterId(@PathVariable int shelterId, @PathVariable int pageIndex, @PathVariable int pageSize, @RequestBody FilterDto filterDto) {
        List<PetHeaderDto> petHeaderDtos = filterService.getFilteredPetsHeadersListByShelterId(shelterId, pageIndex, pageSize, filterDto.getFilters());
        return new ResponseEntity<>(petHeaderDtos, HttpStatus.OK);
    }

    @PostMapping("addPet")////check here
    public ResponseEntity<Void> addPet(@RequestBody PetViewDto petViewDto) {
        masterControlServices.addPet(petViewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("editPet")////check here
    public ResponseEntity<Void> editPet(@RequestBody PetViewDto petViewDto) {
        masterControlServices.editPet(petViewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("getShelterDto/{shelterId}")
    public ResponseEntity<ShelterViewDto> getShelterDto(@PathVariable int shelterId) {
        ShelterViewDto shelterViewDto = masterControlServices.getShelterViewDtoByShelterId(shelterId);
        return new ResponseEntity<>(shelterViewDto, HttpStatus.OK);
    }

    @PostMapping("updateShelterDto")
    public ResponseEntity<Void> updateShelterDto(@RequestBody ShelterViewDto shelterViewDto) {
        masterControlServices.updateShelter(shelterViewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("updatePetProfilePicture/{petId}")
    public ResponseEntity<Void> updatePetProfilePicture(@PathVariable int petId, @RequestBody ImageDto imageDto) {
        masterControlServices.updatePetProfilePicture(petId, imageDto.image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
