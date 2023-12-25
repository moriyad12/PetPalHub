package com.example.PetPalHub.Exceptions.DashboardException;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidPageSize extends BaseException {
    public InvalidPageSize() {
        this.message = "Invalid page size";
        this.httpStatus = HttpStatus.FORBIDDEN;

    }
}
