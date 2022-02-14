package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportRow;
import com.maxcropdata.maxcropemployee.model.report.ReportRowDetail;
import com.maxcropdata.maxcropemployee.model.report.ReportRowDetailsArrayAdapter;
import com.maxcropdata.maxcropemployee.view.dialogs.ReportIssueDialog;
import com.maxcropdata.maxcropemployee.view.rowholders.ReportActionRowHolder;

import java.text.ParseException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowReportRecordDetailFragment extends Fragment {


    private MainActivity activity;
    private ReportRow reportRow;
    private Report report;
    private ReportActionRowHolder headerHolder;
    private ReportRowDetailsArrayAdapter adapter;

    public static ShowReportRecordDetailFragment getInstance(ReportRow reportRow, Report report) {
        ShowReportRecordDetailFragment instance = new ShowReportRecordDetailFragment();
        instance.setReport(report);
        instance.setReportRow(reportRow);
        return instance;
    }

    private void setReportRow(ReportRow reportRow) {
        this.reportRow = reportRow;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_report_record_details, container, false);

        this.activity = (MainActivity)getActivity();
        this.headerHolder = new ReportActionRowHolder(root, activity, report);
        this.headerHolder.populate(reportRow);

        try {
            adapter = new ReportRowDetailsArrayAdapter(
                    this.activity,
                    this.reportRow.listDetails(activity, report)
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }


        ListView list = root.findViewById(R.id.report_list_view);

        list.setAdapter(adapter);


        list.setOnItemClickListener((parent, view, position, id) -> {
            ReportRowDetail detailField = adapter.getItem(position);
            ReportIssueDialog issueDialog = new ReportIssueDialog(activity, detailField);
            issueDialog.show();
        });

        return root;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}