package com.rj.SpringInAction.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rj.SpringInAction.exceptions.BookAlreadyExistsException;
import com.rj.SpringInAction.exceptions.BookNotFoundException;

@RestControllerAdvice
public class MyCustomExceptionHandler {

    // using the ResponseBody and specifiying the status using the annotation
    // @ResponseBody [Not required as we are using RestControllerAdvice, instead of
    // ControllerAdvice]
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFoundException(BookNotFoundException ex) {
        return ex.getMessage();
    }

    // Using the ResponseEntity
    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<String> handleBookAlreadyExistsException(BookAlreadyExistsException ex) {
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
