package com.example.PetPalHub.Exceptions;

import org.springframework.http.HttpStatus;

public class PetAlreadyAddedException extends BaseException{
    public PetAlreadyAddedException(int id) {
        this.message = "Pet with id " + id + " already added";
        this.httpStatus= HttpStatus.CONFLICT;
    }
}
