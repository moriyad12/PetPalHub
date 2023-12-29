package com.example.PetPalHub.Controller;

import com.example.PetPalHub.Dto.FilterDto;
import com.example.PetPalHub.Dto.PetHeaderDto;
import com.example.PetPalHub.Dto.PetViewDto;
import com.example.PetPalHub.Dto.UserProfileDto;
import com.example.PetPalHub.Entities.users.User;
import com.example.PetPalHub.Mapper.UserProfileDtoMapper;
import com.example.PetPalHub.RepositoriesService.users.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepositoryService userRepositoryService;
    @Autowired
    private UserProfileDtoMapper userProfileDtoMapper;

    @GetMapping("getUserDto/{userId}")
    public ResponseEntity<UserProfileDto> getUserDto(@PathVariable int userId) {
        User user = userRepositoryService.findById(userId);
        UserProfileDto userProfileDto = userProfileDtoMapper.getDtoToView(user);
        return new ResponseEntity<>(userProfileDto, HttpStatus.OK);
    }

    @PostMapping("updateUserProfile")
    public ResponseEntity<Void> updateUserProfile(@RequestBody UserProfileDto userProfileDto) {
        User user = userRepositoryService.findById(userProfileDto.getId());
        user = userProfileDtoMapper.getUserFromDto(user, userProfileDto);
        userRepositoryService.update(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
