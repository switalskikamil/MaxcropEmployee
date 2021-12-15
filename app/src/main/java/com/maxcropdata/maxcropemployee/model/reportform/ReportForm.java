package com.maxcropdata.maxcropemployee.model.reportform;

import java.util.Date;

public class ReportForm {
    private long workerId;
    private Date dateFrom;
    private Date dateTo;


    public ReportForm(long workerId, Date dateFrom, Date dateTo) {
        this.workerId = workerId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public ReportForm() {

    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
