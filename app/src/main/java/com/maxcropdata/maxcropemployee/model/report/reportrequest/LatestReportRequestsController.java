package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import android.content.Context;

import com.maxcropdata.maxcropemployee.shared.utils.FileManager;

import org.json.JSONException;
import org.json.JSONObject;

public class LatestReportRequestsController {
    static final String FILE_NAME = "latest_report_requests.json";


    public static void saveLatestReportRequestsToFileSystem(
            Context context,
            LatestReportRequests latestReportRequests)
            throws IllegalAccessException, JSONException {
        saveLatestReportRequestsToFileSystem(context, latestReportRequests, FILE_NAME);
    }


    public static void saveLatestReportRequestsToFileSystem(
            Context context,
            LatestReportRequests latestReportRequests,
            String fileName)
            throws IllegalAccessException, JSONException {

        FileManager.writeFileToStorage(
                context,
                fileName,
                LatestReportRequestsService.toJSON(latestReportRequests));
    }

    public static LatestReportRequests readLatestReportRequestsFromFileSystem(Context context)  {
        try {
            return readLatestReportRequestsFromFileSystem(context, FILE_NAME);
        } catch (Exception e) {
            return new LatestReportRequests();
        }
    }

    public static LatestReportRequests readLatestReportRequestsFromFileSystem(
            Context context,
            String fileName
    ) throws JSONException, IllegalAccessException {

        String file = FileManager.readFileFromStorage(context, fileName);

        if (file != null && file.length() > 0) {
            return LatestReportRequestsService.fromJSON(file);
        } else {
            return new LatestReportRequests();
        }
    }
}
