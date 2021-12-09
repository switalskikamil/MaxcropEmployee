package com.maxcropdata.maxcropemployee.model.server.request;

import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.response.ReportsForDatesServerResponse;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;

public class ReportsForDatesServerRequest extends ServerRequest {

    private static final String END_POINT = "employeeGetReports";

    public ReportsForDatesServerRequest(Token authToken, String payload, Server server, AsyncResponseProcessor delegatedProcessor) {
        super(authToken, payload, server, delegatedProcessor, MethodType.GET, END_POINT);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        this.getDelegatedProcessor().processFinish(new ReportsForDatesServerResponse(getHttpResponseCode(), s));
    }


}
