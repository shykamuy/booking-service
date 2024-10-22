package com.example.BookingService.exception;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException() {}

    public ObjectNotFoundException(String message) {
        super(message);
    }
}

