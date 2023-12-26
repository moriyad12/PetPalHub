package com.example.PetPalHub.Exceptions.UsersExceptions;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        this.message = " User Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}