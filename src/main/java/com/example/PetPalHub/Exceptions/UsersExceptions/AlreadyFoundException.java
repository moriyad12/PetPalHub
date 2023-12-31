package com.example.PetPalHub.Exceptions.UsersExceptions;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AlreadyFoundException extends BaseException {
    public AlreadyFoundException() {
        this.message = " Already Found ";
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
