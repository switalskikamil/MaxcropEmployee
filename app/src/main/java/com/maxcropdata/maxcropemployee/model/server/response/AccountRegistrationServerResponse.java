package com.maxcropdata.maxcropemployee.model.server.response;

import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountRegistrationServerResponse extends ServerResponse {

    private Account account;

    public AccountRegistrationServerResponse(int responseCode, String jsonResponse) {
        super(responseCode, jsonResponse);
    }

    @Override
    public void readResponse() throws RequestUnathorizedException, ResponseMalformedException, UexpectedResponseStatusException {

        if (super.processResponse()) {
            this.account = new Account();

            try {
                JSONService.readJSONIntoObject(new JSONObject(getJsonResponse()), this.account);
            } catch (JSONException | IllegalAccessException e) {
                throw new ResponseMalformedException("Response malformed and could nto be read.");
            }
        }

    }

    public Account getAccount() {
        return account;
    }
}
