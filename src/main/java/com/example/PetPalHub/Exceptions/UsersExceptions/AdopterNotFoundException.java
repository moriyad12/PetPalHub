package com.example.PetPalHub.Exceptions.UsersExceptions;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AdopterNotFoundException extends BaseException {
    public AdopterNotFoundException() {
        this.message = " Adopter Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}