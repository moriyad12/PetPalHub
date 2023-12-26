package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ShelterNotFoundException extends BaseException {
    public ShelterNotFoundException(int id) {
        this.message = "Shelter with id " + id + " not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
    public ShelterNotFoundException(String code) {
        this.message = "Shelter with code " + code + " not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
