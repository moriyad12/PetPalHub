package com.example.PetPalHub.Exceptions.Securiity;


import com.example.PetPalHub.Exceptions.BaseException;

public class ForbiddenException extends BaseException {
    public ForbiddenException() {
        this.httpStatus = org.springframework.http.HttpStatus.FORBIDDEN;
        this.message = "You do not have permission to access this resource";
    }

}
