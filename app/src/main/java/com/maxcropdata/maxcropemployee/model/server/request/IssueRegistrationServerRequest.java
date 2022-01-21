package com.maxcropdata.maxcropemployee.model.server.request;

import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.response.IssueRegistrationServerResponse;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;

public class IssueRegistrationServerRequest extends ServerRequest {

    private static final String END_POINT = "employeeIssueRegistration";

    public IssueRegistrationServerRequest(Token authToken, String payload, Server server, AsyncResponseProcessor delegatedProcessor) {
        super(authToken, payload, server, delegatedProcessor, MethodType.POST, END_POINT);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        this.getDelegatedProcessor().processFinish(new IssueRegistrationServerResponse(getHttpResponseCode(), s));
    }
}
