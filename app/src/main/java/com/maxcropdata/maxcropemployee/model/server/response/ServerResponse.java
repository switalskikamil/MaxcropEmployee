package com.maxcropdata.maxcropemployee.model.server.response;


import javax.net.ssl.HttpsURLConnection;

public abstract class ServerResponse {
    private String jsonResponse;
    private int responseCode;



    public ServerResponse(int responseCode, String jsonResponse) {
        this.responseCode = responseCode;
        this.jsonResponse = jsonResponse;
    }

    public boolean processResponse() throws RequestUnathorizedException, UexpectedResponseStatusException {
        if (getResponseCode() == HttpsURLConnection.HTTP_OK) {

            return true;

        } else if (getResponseCode() == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            throw new RequestUnathorizedException("Request has not been authorized");
        } else {
            throw new UexpectedResponseStatusException("Unexpected response status code: " + getResponseCode());
        }
    }

    /**
     * Reads the json response into response specific fields
     */
    public abstract void readResponse() throws RequestUnathorizedException, ResponseMalformedException, UexpectedResponseStatusException;


    public String getJsonResponse() {
        return jsonResponse;
    }


    public int getResponseCode() {
        return responseCode;
    }
}
