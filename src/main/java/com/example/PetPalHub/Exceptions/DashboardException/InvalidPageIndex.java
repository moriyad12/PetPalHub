package com.example.PetPalHub.Exceptions.DashboardException;

import com.example.PetPalHub.Exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidPageIndex extends BaseException {
    public InvalidPageIndex() {
        this.message = "Invalid page index";
        this.httpStatus = HttpStatus.FORBIDDEN;
    }
}
