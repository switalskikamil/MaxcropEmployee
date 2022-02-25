package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportRequestService {

    public static String toJSON(ReportRequest reportRequest) throws IllegalAccessException {
        return JSONService.formatAsJSON(reportRequest);
    }

    public static ReportRequest fromJSON(JSONObject json)
            throws JSONException, IllegalAccessException {
        final ReportRequest reportRequest = new ReportRequest();
        JSONService.readJSONIntoObject(json, reportRequest);

        return reportRequest;
    }
}
