package com.maxcropdata.maxcropemployee.model.server.request;

import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.response.AccountLoginServerResponse;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;

public class AccountLoginServerRequest extends ServerRequest {

    private static final String END_POINT = "employeeLogin";

    public AccountLoginServerRequest(Token authToken, Server server, AsyncResponseProcessor delegatedProcessor) {
        super(authToken, "", server, delegatedProcessor, MethodType.POST, END_POINT);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        this.getDelegatedProcessor().processFinish(new AccountLoginServerResponse(getHttpResponseCode(), s));
    }
}
