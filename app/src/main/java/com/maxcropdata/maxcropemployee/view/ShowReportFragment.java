package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportActionType;
import com.maxcropdata.maxcropemployee.model.report.ReportColumnType;
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

        ArrayList<ReportRow> data = prepareDataForTheView();

        ReportRowArrayAdapter adapter = new ReportRowArrayAdapter(
                this.activity,
                data,
                report
        );


        ListView list = root.findViewById(R.id.report_list_view);

        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            final ReportRow reportRow = adapter.getItem(position);
            if (!(Boolean)reportRow.getColumn(ReportColumnType.COL_IS_FINAL)
                && (int)reportRow.getColumn(ReportColumnType.COL_PAYMENT_FOR) != ReportActionType.ACTION_DAY_BREAK)
                activity.loadFragment(ShowReportRecordDetailFragment.getInstance(reportRow, report));
        });

        return root;
    }

    private ArrayList<ReportRow> prepareDataForTheView() {
        ArrayList<ReportRow> data = (ArrayList<ReportRow>) this.report.getReportRows();
        ArrayList<ReportRow> returnData = new ArrayList<>();

        String previousDate = "";
        boolean reachedFinals;
        //insert day-breakers
        for (ReportRow rw : data) {
            String currentDate = rw.getColumnAsString(ReportColumnType.COL_DATE);
            reachedFinals = (Boolean)rw.getColumn(ReportColumnType.COL_IS_FINAL);
            if (!currentDate.equals(previousDate)
                    && !reachedFinals
            ) {
                ReportRow dayBreaker = new ReportRow(0);
                dayBreaker.getColumns().put(ReportColumnType.COL_DATE, currentDate);
                dayBreaker.getColumns().put(ReportColumnType.COL_PAYMENT_FOR, ReportActionType.ACTION_DAY_BREAK);
                dayBreaker.getColumns().put(ReportColumnType.COL_IS_FINAL, false);
                dayBreaker.getColumns().put(ReportColumnType.COL_IS_PRICE_GROUP, false);
                returnData.add(dayBreaker);

            }
            previousDate = currentDate;
            returnData.add(rw);

        }

        return returnData;
    }
}