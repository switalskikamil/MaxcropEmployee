package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import java.util.Date;

public class ReportRequest {
    private Date dateFrom;
    private Date dateTo;

    public ReportRequest(Date dateFrom, Date dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
    public ReportRequest() {
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }
}
