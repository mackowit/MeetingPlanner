package com.crud.planner.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User has not been found.");
    }
}
