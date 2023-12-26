package com.example.PetPalHub.Exceptions.Securiity;


import com.example.PetPalHub.Exceptions.BaseException;

public class ExistingUserName extends BaseException {
    public ExistingUserName() {
        this.httpStatus = org.springframework.http.HttpStatus.BAD_REQUEST;
        this.message = "Username already exists";
    }

}