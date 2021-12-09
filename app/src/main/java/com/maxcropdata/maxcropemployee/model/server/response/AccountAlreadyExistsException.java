package com.maxcropdata.maxcropemployee.model.server.response;

public class AccountAlreadyExistsException extends  Exception {

    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
