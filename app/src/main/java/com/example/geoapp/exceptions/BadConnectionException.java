package com.example.geoapp.exceptions;

public class BadConnectionException extends  RuntimeException {

    private int responseCode ;
    private String message = " Connection error. Response code is: ";

    public BadConnectionException( int responseCode, String message){
        this.message = message;
        this.responseCode = responseCode;
    }

    public BadConnectionException ( int responseCode ){
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return this.message + this.responseCode;
    }

}
