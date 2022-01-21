package com.maxcropdata.maxcropemployee.model.report;

import java.util.Date;

public class ReportRowDetail {
    String fieldLabel;
    String fieldId;
    String fieldValue;
    Date reportRowDate;
    boolean isOddRow;

    public ReportRowDetail(
            String fieldLabel,
            String fieldValue,
            String fieldId,
            Date reportRowDate,
            boolean isOddRow) {
        this.fieldLabel = fieldLabel;
        this.fieldValue = fieldValue;
        this.fieldId = fieldId;
        this.reportRowDate = reportRowDate;
        this.isOddRow = isOddRow;
    }

    public ReportRowDetail(
            String fieldLabel,
            String fieldValue,
            String fieldId) {
        this.fieldLabel = fieldLabel;
        this.fieldValue = fieldValue;
        this.fieldId = fieldId;
        this.isOddRow = false;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public boolean isOddRow() {
        return isOddRow;
    }

    public void setOddRow(boolean oddRow) {
        isOddRow = oddRow;
    }

    public String getFieldId() {
        return fieldId;
    }

    public Date getReportRowDate() {
        return reportRowDate;
    }
}
