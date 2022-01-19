package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportRow;
import com.maxcropdata.maxcropemployee.model.report.ReportRowArrayAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowReportFragment extends Fragment {

    private MainActivity activity;
    private Report report;

    public static ShowReportFragment getInstance(Report report) {
        ShowReportFragment fragment = new ShowReportFragment();
        fragment.showReport(report);
        return  fragment;
    }

    public ShowReportFragment() {

    }

    private void showReport(Report report) {
        this.report = report;
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_report, container, false);

        this.activity = (MainActivity)getActivity();

        ReportRowArrayAdapter adapter = new ReportRowArrayAdapter(
                this.activity,
                (ArrayList<ReportRow>) this.report.getReportRows()
        );


        ListView list = root.findViewById(R.id.report_list_view);

        list.setAdapter(adapter);

        return root;
    }
}