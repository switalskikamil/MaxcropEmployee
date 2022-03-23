package com.maxcropdata.maxcropemployee.model.server.response;

import android.util.Log;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportsForDatesServerResponse extends ServerResponse {

    Report report;
    private static final String REPORTS = "reports";

    public ReportsForDatesServerResponse(int responseCode, String jsonResponse) {
        super(responseCode, jsonResponse);
    }

    @Override
    public void readResponse(MainActivity activity) throws
            RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException, ForbiddenActionException, IssueRegistrationBlockedException, LimitExceededException {
        if (super.processResponse()) {

            try {
                JSONObject json = new JSONObject(getJsonResponse());

                report = ReportService.getInstance().fromJSON(json, activity);

            } catch (JSONException e) {
                throw new ResponseMalformedException("Response malformed and could not be read.");
            }
        }
    }

    public Report getReport() {
        return report;
    }
}
