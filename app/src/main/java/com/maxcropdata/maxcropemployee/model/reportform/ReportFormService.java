package com.maxcropdata.maxcropemployee.model.reportform;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportFormService implements JSONAble<ReportForm> {

    private static ReportFormService instance = new ReportFormService();
    public static ReportFormService getInstance() {
        return instance;
    }

    @Override
    public String toJSON(ReportForm reportForm) throws IllegalAccessException, NoSuchFieldException {
        return JSONService.formatAsJSON(reportForm);
    }

    @Override
    public ReportForm fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        return null;
    }
}
