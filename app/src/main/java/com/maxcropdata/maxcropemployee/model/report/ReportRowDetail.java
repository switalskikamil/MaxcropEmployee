package com.maxcropdata.maxcropemployee.model.report;

public class ReportRowDetail {
    String fieldLabel;
    String fieldValue;
    boolean isOddRow;

    public ReportRowDetail(String fieldLabel, String fieldValue, boolean isOddRow) {
        this.fieldLabel = fieldLabel;
        this.fieldValue = fieldValue;
        this.isOddRow = isOddRow;
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
}
