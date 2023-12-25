package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PetAlreadyAddedException extends BaseException {
    public PetAlreadyAddedException(int id) {
        this.message = "Pet with id " + id + " already added";
        this.httpStatus= HttpStatus.CONFLICT;
    }
}
