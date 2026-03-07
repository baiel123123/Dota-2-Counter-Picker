package com.dotacp.counterpicker.exception;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(String message) {
        super(message);
    }
}
