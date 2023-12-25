package com.example.PetPalHub.Exceptions.RelationException;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ApplicationNotFoundExceptions extends BaseException {
    public ApplicationNotFoundExceptions() {
        this.message = " Application Not Found ";
        this.httpStatus = HttpStatus.NOT_FOUND;
    }
}
