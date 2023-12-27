package com.example.PetPalHub.Exceptions.Shelter;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class PetIsNotAvailableException extends BaseException {

    public PetIsNotAvailableException() {
        this.message = "Pet Is Not Available Exception";
        this.httpStatus = HttpStatus.FORBIDDEN;
    }

}
