package com.example.PetPalHub.Exceptions;

import org.springframework.http.HttpStatus;

public class ShelterAlreadyCreatedException extends BaseException{
    public ShelterAlreadyCreatedException(int id) {
        this.message = "Shelter with id " + id + " already exists";
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
