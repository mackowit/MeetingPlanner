package com.crud.planner.exception;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException() {
        super("Group has not been found.");
    }
}
