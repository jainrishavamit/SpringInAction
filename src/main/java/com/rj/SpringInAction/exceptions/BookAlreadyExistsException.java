package com.rj.SpringInAction.exceptions;

public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(String error) {
        super(error);
    }
}
