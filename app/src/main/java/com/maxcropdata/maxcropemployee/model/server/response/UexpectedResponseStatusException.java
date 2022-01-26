package com.maxcropdata.maxcropemployee.model.server.response;

public class UexpectedResponseStatusException extends Exception {
    private int statusCode;
    public UexpectedResponseStatusException(String message, int statusCode) {

        super(message);

        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
