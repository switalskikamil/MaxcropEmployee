package com.maxcropdata.maxcropemployee.model.issue;

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

    public static class Builder {
        private long issueDbId;
        private String fieldCode;
        private String issueDetails;
        private long idAccount;
        private Date reportedDay;
        private long issueLocalId;
        private Date issueRegistrationDate;
        private String fieldValue;

        public Builder() {

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
