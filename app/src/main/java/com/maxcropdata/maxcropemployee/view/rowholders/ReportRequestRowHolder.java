package com.maxcropdata.maxcropemployee.view.rowholders;

import android.view.View;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.reportrequest.ReportRequest;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

public class ReportRequestRowHolder {
    private TextView fromText;
    private TextView toText;
    private MainActivity activity;

    public ReportRequestRowHolder(View view, MainActivity activity) {
        this.activity = activity;

        this.fromText = view.findViewById(R.id.report_request_from);
        this.toText = view.findViewById(R.id.report_request_to);
    }

    public void populate(ReportRequest reportRequest) {

        this.fromText.setText(
                Helper.DATE_FORMAT.format(reportRequest.getDateFrom())
        );

        this.toText.setText(
                Helper.DATE_FORMAT.format(reportRequest.getDateTo())
        );
    }
}
