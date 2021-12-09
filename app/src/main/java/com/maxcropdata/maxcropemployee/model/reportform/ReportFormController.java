package com.maxcropdata.maxcropemployee.model.reportform;

public class ReportFormController {

    public static String generateReportRequest(ReportForm reportForm) throws NoSuchFieldException, IllegalAccessException {
        return ReportFormService.getInstance().toJSON(reportForm);
    }
}
