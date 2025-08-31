package com.example.HarareProject.Controller;

import com.example.HarareProject.Entity.ErrorReponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ne) {
        ErrorReponse er = new ErrorReponse(ne.getMessage(),"id not there", LocalDateTime.now());
        return new ResponseEntity<ErrorReponse>(er, HttpStatus.NOT_FOUND);
    }
}
