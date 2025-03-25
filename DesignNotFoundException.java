package com.canvas.exception;


public class DesignNotFoundException extends RuntimeException {
    public DesignNotFoundException(String message) {
        super(message);
    }
}