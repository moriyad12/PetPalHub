package com.example.PetPalHub.Exceptions.Securiity;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;


public class ExistingMail extends BaseException {

    public ExistingMail() {
        this.message = "Mail Already Exists";
        this.httpStatus = HttpStatus.BAD_REQUEST;

    }
}