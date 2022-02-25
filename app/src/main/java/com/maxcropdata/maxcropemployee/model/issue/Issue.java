package com.maxcropdata.maxcropemployee.model.issue;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.ReportActionType;

import java.util.Date;
import java.util.Objects;

public class Issue {
    private long issueDbId;
    private String fieldCode;
    private String fieldValue;
    private String issueDetails;
    private long idAccount;
    private Date reportedDay;
    private long issueLocalId;
    private Date issueRegistrationDate;

    //columns that are here to help identify data record
    private int reportRowPaymentForInt;
    private long priceGroupId;
    private String productClass;
    private String area;
    private String workType;
    private String timeSpan;

    public Issue() {

    }


    private Issue(Builder builder) {
        this.issueDbId = builder.issueDbId;
        this.fieldCode = builder.fieldCode;
        this.fieldValue = builder.fieldValue;
        this.issueDetails = builder.issueDetails;
        this.idAccount = builder.idAccount;
        this.reportedDay = builder.reportedDay;
        this.issueLocalId = builder.issueLocalId;
        this.issueRegistrationDate = builder.issueRegistrationDate;
        this.reportRowPaymentForInt = builder.reportRowPaymentForInt;
        this.priceGroupId = builder.priceGroupId;
        this.productClass = builder.productClass;
        this.area = builder.area;
        this.workType = builder.workType;
        this.timeSpan = builder.timeSpan;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return issueDbId == issue.issueDbId &&
                idAccount == issue.idAccount &&
                issueLocalId == issue.issueLocalId &&
                Objects.equals(fieldCode, issue.fieldCode) &&
                Objects.equals(issueDetails, issue.issueDetails) &&
                Objects.equals(reportedDay, issue.reportedDay) &&
                Objects.equals(issueRegistrationDate, issue.issueRegistrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDbId, fieldCode, issueDetails, idAccount, reportedDay, issueLocalId, issueRegistrationDate);
    }

    public long getIssueDbId() {
        return issueDbId;
    }

    public void setIssueDbId(long issueDbId) {
        this.issueDbId = issueDbId;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public Date getReportedDay() {
        return reportedDay;
    }

    public void setReportedDay(Date reportedDay) {
        this.reportedDay = reportedDay;
    }

    public long getIssueLocalId() {
        return issueLocalId;
    }

    public void setIssueLocalId(long issueLocalId) {
        this.issueLocalId = issueLocalId;
    }

    public Date getIssueRegistrationDate() {
        return issueRegistrationDate;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public String getApplyToRecordDescription(MainActivity activity) {

        if (reportRowPaymentForInt == ReportActionType.ACTION_PIECEWORK_HARVEST) {
            if (priceGroupId == 0) return ReportActionType.getLabel(reportRowPaymentForInt, activity)+ ", " + productClass + ", " +area;
            else return activity.getString(R.string.column_label_price_group) +": " + productClass;
        } else if (reportRowPaymentForInt == ReportActionType.ACTION_PIECEWORK
                || reportRowPaymentForInt == ReportActionType.ACTION_TIMEWORK
                || reportRowPaymentForInt == ReportActionType.ACTION_BREAK) {
            return  ReportActionType.getLabel(reportRowPaymentForInt, activity) + ", " + workType + ", " +area + ", " + timeSpan;
        } else return "";

    }

    public static class Builder {
        private long issueDbId;
        private String fieldCode;
        private String issueDetails;
        private long idAccount;
        private Date reportedDay;
        private long issueLocalId;
        private Date issueRegistrationDate;
        private String fieldValue;
        private int reportRowPaymentForInt;
        private long priceGroupId;
        private String productClass;
        private String area;
        private String workType;
        private String timeSpan;

        public Builder() {

        }

        public Builder timeSpan(String timeSpan) {
            this.timeSpan = timeSpan;
            return this;
        }

        public Builder workType(String workType) {
            this.workType = workType;
            return this;
        }

        public Builder area(String area) {
            this.area = area;
            return this;
        }

        public Builder productClass(String productClass) {
            this.productClass = productClass;
            return this;
        }

        public Builder reportRowPaymentForInt(int reportRowPaymentForInt) {
            this.reportRowPaymentForInt = reportRowPaymentForInt;
            return this;
        }

        public Builder priceGroupId(long priceGroupId) {
            this.priceGroupId = priceGroupId;
            return this;
        }

        public Builder issueRegistrationDate(Date issueRegistrationDate) {
            this.issueRegistrationDate = issueRegistrationDate;
            return this;
        }

        public Builder issueLocalId(long issueLocalId) {
            this.issueLocalId = issueLocalId;
            return this;
        }

        public Builder issueDbId(long issueDbId) {
            this.issueDbId = issueDbId;
            return this;
        }

        public Builder fieldCode(String fieldCode) {
            this.fieldCode = fieldCode;
            return this;
        }

        public Builder fieldValue(String fieldValue) {
            this.fieldValue = fieldValue;
            return this;
        }

        public Builder issueDetails(String issueDetails) {
            this.issueDetails = issueDetails;
            return this;
        }

        public Builder idAccount(long idAccount) {
            this.idAccount = idAccount;
            return this;
        }

        public Builder reportedDay(Date reportedDay) {
            this.reportedDay = reportedDay;
            return this;
        }

        public Issue build() {
            return new Issue(this);
        }
    }
}
