package com.maxcropdata.maxcropemployee.model.server.response;

public class ForbiddenActionException extends Throwable {
    private boolean registrationResponse;

    public ForbiddenActionException(String s, boolean isRegistrationResponse) {
        super(s);
        this.registrationResponse = isRegistrationResponse;
    }

    public boolean isRegistrationResponse() {
        return registrationResponse;
    }
}
