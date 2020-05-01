package com.crud.planner.exception;

public class MeetingNotFoundException extends RuntimeException {

    public MeetingNotFoundException() {
        super("Meeting has not been found.");
    }
}
