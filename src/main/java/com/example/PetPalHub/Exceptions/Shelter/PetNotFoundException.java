package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PetNotFoundException extends BaseException {
    public PetNotFoundException(int id) {
        this.message = "Pet with id " + id + " not found";
        this.httpStatus= HttpStatus.NOT_FOUND;
    }
}
