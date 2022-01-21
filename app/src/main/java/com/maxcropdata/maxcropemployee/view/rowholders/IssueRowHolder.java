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
    private ReportActionRowDetailHolder fieldDetailHolder;
    private LinearLayout dateLayout;
    private LinearLayout statusLayout;
    private MainActivity activity;

    public IssueRowHolder(View view, MainActivity activity) {
        this.activity = activity;

        this.issueStatusText = view.findViewById(R.id.issue_registration_status);
        this.issueReportDateText = view.findViewById(R.id.action_date);
        this.issueRegistrationDateText = view.findViewById(R.id.issue_registration_time);
        this.fieldDetailHolder = new ReportActionRowDetailHolder(view, activity);
        this.dateLayout = view.findViewById(R.id.action_date_holder);
        this.statusLayout = view.findViewById(R.id.issue_status_bar);
    }

    public void populate(Issue issue) {
        final boolean issueSent = issue.getIssueDbId() > 0L;
        setStatus(issueSent);

        this.issueRegistrationDateText.setText(
                Helper.DATE_TIME_FORMAT.format(issue.getIssueRegistrationDate())
        );

        this.issueReportDateText.setText(Helper.DATE_FORMAT.format(issue.getReportedDay()));

        this.fieldDetailHolder.populate(new ReportRowDetail(
                ReportColumnType.getLabel(issue.getFieldCode(), activity),
                issue.getFieldValue(),
                issue.getFieldCode()
                ));
    }

    private void setStatus(boolean status) {
        if (status) {
            issueStatusText.setText(R.string.label_issue_sent);
            dateLayout.setBackgroundColor(activity.getColor(R.color.colorGreenishDark));
            statusLayout.setBackgroundColor(activity.getColor(R.color.colorGreenishDark));
        } else {
            issueStatusText.setText(R.string.label_issue_unsent);
            dateLayout.setBackgroundColor(activity.getColor(R.color.colorVioletish));
            statusLayout.setBackgroundColor(activity.getColor(R.color.colorVioletish));
        }
    }

}
