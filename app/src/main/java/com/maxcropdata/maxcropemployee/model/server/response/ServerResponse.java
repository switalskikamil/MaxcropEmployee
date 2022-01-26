package com.maxcropdata.maxcropemployee.model.server.response;


import android.util.Log;

import com.maxcropdata.maxcropemployee.MainActivity;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public abstract class ServerResponse {
    private String jsonResponse;
    private int responseCode;



    public ServerResponse(int responseCode, String jsonResponse) {
        this.responseCode = responseCode;
        this.jsonResponse = jsonResponse;
    }

    public boolean processResponse() throws
            RequestUnathorizedException,
            UexpectedResponseStatusException,
            ResponseMalformedException,
            AccountAlreadyExistsException,
            ForbiddenActionException {

        if (getResponseCode() == HttpsURLConnection.HTTP_OK) {
            return true;
        } else if (getResponseCode() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            throw new RequestUnathorizedException("Request has not been authorized");
        } else if (getResponseCode() == HttpsURLConnection.HTTP_INTERNAL_ERROR) {
            throw new ResponseMalformedException("Server could not read the request");
        } else if (getResponseCode() == HttpsURLConnection.HTTP_CONFLICT) {
            throw new AccountAlreadyExistsException("Account for this user already exists");
        } else if (getResponseCode() == HttpURLConnection.HTTP_FORBIDDEN) {
            throw new ForbiddenActionException(
                    "This action is not allowed by Your employer",
                    this instanceof  AccountRegistrationServerResponse
            );
        } else {
            throw new UexpectedResponseStatusException("Unexpected response status code: " + getResponseCode(), getResponseCode());
        }
    }

    /**
     * Reads the json response into response specific fields
     */
    public abstract void readResponse(MainActivity activity)
            throws RequestUnathorizedException, ResponseMalformedException,
            UexpectedResponseStatusException, AccountAlreadyExistsException, ForbiddenActionException;


    public String getJsonResponse() {
        Log.d("MCM", jsonResponse);
        return jsonResponse;
    }


    public int getResponseCode() {
        return responseCode;
    }


}
