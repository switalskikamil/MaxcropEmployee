package com.maxcropdata.maxcropemployee.view.rowholders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.model.report.ReportColumnType;
import com.maxcropdata.maxcropemployee.model.report.ReportRowDetail;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

public class IssueRowHolder {
    private TextView issueStatusText;
    private TextView issueReportDateText;
    private TextView issueRegistrationDateText;
    private TextView issueApplyToText;
    private TextView issueDescriptionText;
    private TextView issueFieldText;
    private TextView issueFieldValueText;
    private LinearLayout backgroundLayout;
    private MainActivity activity;

    public IssueRowHolder(View view, MainActivity activity) {
        this.activity = activity;

        this.issueStatusText = view.findViewById(R.id.issue_registration_status);
        this.issueApplyToText = view.findViewById(R.id.issue_apply_to);
        this.issueDescriptionText = view.findViewById(R.id.issue_description);
        this.issueReportDateText = view.findViewById(R.id.action_date);
        this.issueFieldText = view.findViewById(R.id.action_field_label);
        this.issueFieldValueText = view.findViewById(R.id.action_field_value);
        this.issueRegistrationDateText = view.findViewById(R.id.issue_registration_time);
        this.backgroundLayout = view.findViewById(R.id.issue_background);
    }

    public void populate(Issue issue) {
        final boolean issueSent = issue.getIssueDbId() > 0L;
        setStatus(issueSent);

        this.issueRegistrationDateText.setText(
                Helper.DATE_TIME_FORMAT.format(issue.getIssueRegistrationDate())
        );

        this.issueReportDateText.setText(Helper.DATE_FORMAT.format(issue.getReportedDay()));

        this.issueFieldText.setText(ReportColumnType.getLabel(issue.getFieldCode(), activity));

        this.issueFieldValueText.setText(issue.getFieldValue());

        this.issueApplyToText.setText(issue.getApplyToRecordDescription(activity));

        this.issueDescriptionText.setText(issue.getIssueDetails());
    }

    private void setStatus(boolean status) {
        if (status) {
            issueStatusText.setText(R.string.label_issue_sent);
            backgroundLayout.setBackgroundColor(activity.getColor(R.color.colorPrimary));
        } else {
            issueStatusText.setText(R.string.label_issue_unsent);
            backgroundLayout.setBackgroundColor(activity.getColor(R.color.colorVioletish));
        }
    }

}
