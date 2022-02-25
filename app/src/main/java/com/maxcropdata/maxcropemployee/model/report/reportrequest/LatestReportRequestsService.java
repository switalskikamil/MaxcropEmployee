package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LatestReportRequestsService {

    public static String toJSON(LatestReportRequests latestReportRequests)
            throws IllegalAccessException, JSONException {
        final JSONArray jsonArray = new JSONArray();

        for (ReportRequest rr : latestReportRequests.getList()) {
            if (rr != null) jsonArray.put(new JSONObject(ReportRequestService.toJSON(rr)));
        }

        return jsonArray.toString();
    }

    public static LatestReportRequests fromJSON(String json)
            throws JSONException, IllegalAccessException {
        final JSONArray jsonArray = new JSONArray(json);
        final LatestReportRequests list = new LatestReportRequests();

        for (int i=0; i<jsonArray.length(); i++) {
            list.add(ReportRequestService.fromJSON(jsonArray.getJSONObject(i)));
        }

        return list;

    }

}
