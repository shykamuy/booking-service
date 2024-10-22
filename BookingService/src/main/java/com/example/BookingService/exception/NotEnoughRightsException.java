package com.example.BookingService.exception;

public class NotEnoughRightsException extends RuntimeException{
    public NotEnoughRightsException() {}

    public NotEnoughRightsException(String message) {
        super(message);
    }
}

