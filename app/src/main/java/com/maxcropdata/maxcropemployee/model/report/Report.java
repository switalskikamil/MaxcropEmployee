package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.model.pricegroup.PriceGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Report {
    private Date reportGenerationDate;
    private List<String> columnDefinition;
    private List<ReportRow> reportRows;
    private List<PriceGroup> priceGroups;

    private boolean showAccountRecords;
    private boolean showSummaryRecords;
    private boolean showTimeRecords;
    private boolean showAccordRecords;

    public Report() {
        this.columnDefinition = new ArrayList<>();
        this.reportRows = new ArrayList<>();
        this.reportGenerationDate = new Date();

        this.showAccordRecords = true;
        this.showAccountRecords = true;
        this.showSummaryRecords = true;
        this.showTimeRecords = true;
    }

    public Date getReportGenerationDate() {
        return reportGenerationDate;
    }

    public List<String> getColumnDefinition() {
        return columnDefinition;
    }

    public List<ReportRow> getReportRows() {
        return reportRows;
    }

    public boolean isShowAccountRecords() {
        return showAccountRecords;
    }

    public void setShowAccountRecords(boolean showAccountRecords) {
        this.showAccountRecords = showAccountRecords;
    }

    public boolean isShowSummaryRecords() {
        return showSummaryRecords;
    }

    public void setShowSummaryRecords(boolean showSummaryRecords) {
        this.showSummaryRecords = showSummaryRecords;
    }

    public boolean isShowTimeRecords() {
        return showTimeRecords;
    }

    public void setShowTimeRecords(boolean showTimeRecords) {
        this.showTimeRecords = showTimeRecords;
    }

    public boolean isShowAccordRecords() {
        return showAccordRecords;
    }

    public void setShowAccordRecords(boolean showAccordRecords) {
        this.showAccordRecords = showAccordRecords;
    }

    public List<PriceGroup> getPriceGroups() {
        return priceGroups;
    }

    public void setPriceGroups(List<PriceGroup> priceGroups) {
        this.priceGroups = priceGroups;
    }

    public PriceGroup getPriceGroupById(long id) {
        for (PriceGroup pg : priceGroups) {
            if (pg.getId() == id) return pg;
        }
        return null;
    }
}
