package com.example.PetPalHub.Exceptions;

import org.springframework.http.HttpStatus;

public class ShelterNotFoundException extends BaseException{
    public ShelterNotFoundException(int id) {
        this.message = "Shelter with id " + id + " not found";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
