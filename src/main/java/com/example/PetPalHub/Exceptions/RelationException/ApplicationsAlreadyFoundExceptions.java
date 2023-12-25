package com.example.PetPalHub.Exceptions.RelationException;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ApplicationsAlreadyFoundExceptions extends BaseException {


    public ApplicationsAlreadyFoundExceptions() {
        this.message = " Application  Found  Already";
        this.httpStatus = HttpStatus.FOUND;
    }
}
