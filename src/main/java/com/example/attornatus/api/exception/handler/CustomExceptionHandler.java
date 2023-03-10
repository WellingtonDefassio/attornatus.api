package com.example.attornatus.api.exception.handler;

import com.example.attornatus.api.exception.IncorrectDateFormatException;
import com.example.attornatus.api.exception.NameAlreadyExistsException;
import com.example.attornatus.api.exception.ResourceNotFoundException;
import com.example.attornatus.api.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IncorrectDateFormatException.class)
    public ResponseEntity<StandardError> incorrectDataFormat(IncorrectDateFormatException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> argumentsNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getFieldError().getDefaultMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<StandardError> nomeAlreadyExists(NameAlreadyExistsException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> notReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage().substring(0, 32), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
