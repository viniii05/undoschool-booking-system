package com.undoschool.booking_system.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleNotFound(
            ResourceNotFoundException ex){

        return Map.of(
                "message",
                ex.getMessage());
    }

    @ExceptionHandler(
            ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String,String> handleConflict(
            ConflictException ex){

        return Map.of(
                "message",
                ex.getMessage());
    }
}