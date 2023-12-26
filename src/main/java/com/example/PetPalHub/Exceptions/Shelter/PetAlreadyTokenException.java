package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PetAlreadyTokenException extends BaseException {

    public PetAlreadyTokenException() {
        this.message = "Pet  already token from another adopter";
        this.httpStatus = HttpStatus.CONFLICT;
    }
}
