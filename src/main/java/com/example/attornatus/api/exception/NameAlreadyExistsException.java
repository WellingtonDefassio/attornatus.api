package com.example.attornatus.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException(String s) {
        super(s);
    }
}
