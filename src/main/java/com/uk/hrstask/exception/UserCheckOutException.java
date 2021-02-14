package com.uk.hrstask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserCheckOutException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserCheckOutException(String message) {
        super(message);
    }
}
