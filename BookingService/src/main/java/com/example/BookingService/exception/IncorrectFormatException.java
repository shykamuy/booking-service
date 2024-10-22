package com.example.BookingService.exception;

public class IncorrectFormatException extends RuntimeException{
    public IncorrectFormatException() {}

    public IncorrectFormatException(String message) {
        super(message);
    }
}

