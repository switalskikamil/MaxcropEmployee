package com.maxcropdata.maxcropemployee.model.reportform;

public class ReportFormController {
    private static final int MAX_NUM_OF_DAYS_FOR_ONE_REQUEST = 7;

    public static String generateReportRequest(ReportForm reportForm) throws NoSuchFieldException, IllegalAccessException {
        return ReportFormService.getInstance().toJSON(reportForm);
    }

    public static boolean verify(ReportForm form) throws DateFromOlderThanDateToException, TooLongTimeSpanRequested {
        if (form.getDateFrom().compareTo(form.getDateTo()) > 0) throw new DateFromOlderThanDateToException();
        if (form.getDateTo().getTime() - form.getDateFrom().getTime() > 1000*60*60*24*MAX_NUM_OF_DAYS_FOR_ONE_REQUEST) throw new TooLongTimeSpanRequested();
        if (form.getWorkerId() > 0) return true;
        return false;
    }
}
