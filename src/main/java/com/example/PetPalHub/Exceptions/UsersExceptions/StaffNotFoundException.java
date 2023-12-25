package com.example.PetPalHub.Exceptions.UsersExceptions;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class StaffNotFoundException extends BaseException {
    public StaffNotFoundException() {
        this.message = " Staff Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}