package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ResponseStatus: when this exception is thrown,
// Spring automatically sends a 404 response with this reason message
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends Exception {

    public CountryNotFoundException() {
        super("Country not found");
    }
}