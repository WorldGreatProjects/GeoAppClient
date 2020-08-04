package com.example.geoapp.exceptions;

public class RegistrationException extends  RuntimeException {
    private String message = " Error while registration: ";

    public RegistrationException(String message){
        this.message += message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
