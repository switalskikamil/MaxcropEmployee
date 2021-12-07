package com.maxcropdata.maxcropemployee.model.issue;

import java.util.Date;
import java.util.Objects;

public class Issue {
    private long issueDbId;
    private String fieldCode;
    private String issueDetails;
    private long idAccount;
    private Date reportedDay;


    public Issue() {

    }


    private Issue(Builder builder) {
        this.issueDbId = builder.issueDbId;
        this.fieldCode = builder.fieldCode;
        this.issueDetails = builder.issueDetails;
        this.idAccount = builder.idAccount;
        this.reportedDay = builder.reportedDay;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return issueDbId == issue.issueDbId &&
                idAccount == issue.idAccount &&
                Objects.equals(fieldCode, issue.fieldCode) &&
                Objects.equals(issueDetails, issue.issueDetails) &&
                Objects.equals(reportedDay, issue.reportedDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDbId, fieldCode, issueDetails, idAccount, reportedDay);
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

    public static class Builder {
        private long issueDbId;
        private String fieldCode;
        private String issueDetails;
        private long idAccount;
        private Date reportedDay;

        public Builder() {

        }

        public Builder issueDbId(long issueDbId) {
            this.issueDbId = issueDbId;
            return this;
        }

        public Builder fieldCode(String fieldCode) {
            this.fieldCode = fieldCode;
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
