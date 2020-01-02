package com.khrystyna.nerdysoft.exceptions;

public class OccupiedEmailException extends RuntimeException {
    public OccupiedEmailException(String message) {
        super(message);
    }
}
