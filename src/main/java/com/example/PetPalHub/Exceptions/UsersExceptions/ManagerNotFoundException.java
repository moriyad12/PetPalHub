package com.example.PetPalHub.Exceptions.UsersExceptions;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ManagerNotFoundException extends BaseException {
    public ManagerNotFoundException() {
        this.message = " Manager Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}