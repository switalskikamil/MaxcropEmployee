package com.maxcropdata.maxcropemployee.model.server.response;

import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReportsForDatesServerResponse extends ServerResponse {

    List<Report> reportsList;
    private static final String REPORTS = "reports";

    public ReportsForDatesServerResponse(int responseCode, String jsonResponse) {
        super(responseCode, jsonResponse);
    }

    @Override
    public void readResponse() throws
            RequestUnathorizedException,
            ResponseMalformedException,
            UexpectedResponseStatusException,
            AccountAlreadyExistsException {
        if (super.processResponse()) {
            reportsList = new ArrayList<>();

            try {
                JSONObject json = new JSONObject(getJsonResponse());
                JSONArray reports = json.getJSONArray(REPORTS);

                for (int i = 0; i < reports.length(); i++) {
                    reportsList.add(
                            ReportService.getInstance().fromJSON(reports.getJSONObject(i))
                    );
                }


            } catch (JSONException | IllegalAccessException e) {
                throw new ResponseMalformedException("Response malformed and could nto be read.");
            }
        }
    }

    public List<Report> getReportsList() {
        return reportsList;
    }
}
