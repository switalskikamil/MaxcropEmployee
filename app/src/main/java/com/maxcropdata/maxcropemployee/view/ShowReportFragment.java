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
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
            if (!(boolean)reportRow.getColumn(ReportColumnType.COL_IS_FINAL))
                if ((int)reportRow.getColumn(ReportColumnType.COL_PAYMENT_FOR) != ReportActionType.ACTION_DAY_BREAK)
                    activity.loadFragment(ShowReportRecordDetailFragment.getInstance(reportRow, report));
                else if ((boolean)reportRow.getColumn(ReportColumnType.COL_DAY_IS_EMPTY))
                    activity.loadFragment(ShowReportRecordDetailFragment.getInstance(reportRow, report));
        });

        return root;
    }

    private ArrayList<ReportRow> prepareDataForTheView() {
        ArrayList<ReportRow> data = (ArrayList<ReportRow>) this.report.getReportRows();
        ArrayList<ReportRow> returnData = new ArrayList<>();
        int recordsInDay;

        ArrayList<String> daysInScope = listDaysInScope(report);

        for (String date : daysInScope) {
            //day breaker row
            returnData.add(genDayBreakRow(date));

            //all day records
            recordsInDay = 0;
            for (ReportRow rw : data) {
                if (!(Boolean)rw.getColumn(ReportColumnType.COL_IS_FINAL)
                && rw.getColumn(ReportColumnType.COL_DATE).equals(date)) {
                    returnData.add(rw);
                    recordsInDay++;
                }
            }

            if (recordsInDay == 0)
                if (returnData.get(returnData.size()-1) != null)
                    returnData.get(returnData.size()-1).getColumns().put(ReportColumnType.COL_DAY_IS_EMPTY, true);
        }

        //finals
        for (ReportRow rw : data) {
            if ((Boolean)rw.getColumn(ReportColumnType.COL_IS_FINAL)) {
                returnData.add(rw);
            }
        }

        return returnData;
    }

    private ReportRow genDayBreakRow(String date) {
        ReportRow dayBreaker = new ReportRow(0);
        dayBreaker.getColumns().put(ReportColumnType.COL_DATE, date);
        dayBreaker.getColumns().put(ReportColumnType.COL_PAYMENT_FOR, ReportActionType.ACTION_DAY_BREAK);
        dayBreaker.getColumns().put(ReportColumnType.COL_IS_FINAL, false);
        dayBreaker.getColumns().put(ReportColumnType.COL_IS_PRICE_GROUP, false);
        dayBreaker.getColumns().put(ReportColumnType.COL_DAY_IS_EMPTY, false);

        return dayBreaker;
    }

    private ArrayList<String> listDaysInScope(Report report) {
        ArrayList<String> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        Date dFrom = Helper.sanitizeDate(report.getReportFromDate());
        Date dTo = Helper.sanitizeDate(report.getReportToDate());

        while (dFrom.compareTo(dTo) <= 0) {
            list.add(Helper.DATE_FORMAT.format(dFrom));
            c.setTime(dFrom);
            c.add(Calendar.DATE, 1);
            dFrom = c.getTime();
        }

        return list;
    }
}