package com.rj.SpringInAction.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String error) {
        super(error);
    }
}