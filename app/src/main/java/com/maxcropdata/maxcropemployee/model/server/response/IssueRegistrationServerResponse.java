package com.maxcropdata.maxcropemployee.model.server.response;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class IssueRegistrationServerResponse extends ServerResponse {

    private Issue receivedIssueRegistration;

    public IssueRegistrationServerResponse(int responseCode, String jsonResponse) {
        super(responseCode, jsonResponse);
    }

    @Override
    public void readResponse(MainActivity activity) throws
            RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException {
        if (super.processResponse()) {
            this.receivedIssueRegistration = new Issue();

            try {
                JSONService.readJSONIntoObject(new JSONObject(getJsonResponse()), receivedIssueRegistration);
            } catch (JSONException | IllegalAccessException e) {
                throw new ResponseMalformedException("Response malformed and could not be read: " + getJsonResponse() );
            }
        }

    }

    public Issue getReceivedIssueRegistration() {
        return receivedIssueRegistration;
    }
}
