package com.maxcropdata.maxcropemployee.model.server.response;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountLoginServerResponse extends ServerResponse {

    private Account account;


    public AccountLoginServerResponse(int responseCode, String jsonResponse) {
        super(responseCode, jsonResponse);
    }

    @Override
    public void readResponse(MainActivity activity)
            throws RequestUnathorizedException, ResponseMalformedException,
            UexpectedResponseStatusException, AccountAlreadyExistsException,
            ForbiddenActionException {

        if (super.processResponse()) {
            this.account = new Account();

            try {
                JSONService.readJSONIntoObject(new JSONObject(getJsonResponse()), this.account);
            } catch (JSONException | IllegalAccessException e) {
                throw new ResponseMalformedException("Response malformed and could not be read: " + getJsonResponse() );
            }
        }
    }

    public Account getAccount() {
        return account;
    }
}
