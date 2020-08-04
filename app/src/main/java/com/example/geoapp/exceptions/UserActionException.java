package com.example.geoapp.exceptions;

public class UserActionException extends RuntimeException {
    private String message = "Error while user action : ";

    public UserActionException(String message){
        this.message += message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
