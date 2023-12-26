package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ShelterAlreadyCreatedException extends BaseException {
    public ShelterAlreadyCreatedException(int id) {
        this.message = "Shelter with id " + id + " already exists";
        this.httpStatus = HttpStatus.CONFLICT;
    }
    public ShelterAlreadyCreatedException(String code) {
        this.message = "Shelter with code " + code + " already exists";
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
