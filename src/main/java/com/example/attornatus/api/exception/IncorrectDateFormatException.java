package com.example.attornatus.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectDateFormatException extends RuntimeException {
    public IncorrectDateFormatException(String s) {
        super(s);
    }
}
