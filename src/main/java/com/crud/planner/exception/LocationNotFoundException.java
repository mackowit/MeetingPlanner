package com.crud.planner.exception;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException() {
        super("Location has not been found.");
    }
}
