package com.example.geoapp.exceptions;

public class ResetPassException extends RuntimeException {
    private String message = "Can not reset password: ";

    public ResetPassException(String message){
        this.message += message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
