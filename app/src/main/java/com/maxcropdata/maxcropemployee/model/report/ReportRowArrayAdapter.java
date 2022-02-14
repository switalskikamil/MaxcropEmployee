package com.maxcropdata.maxcropemployee.model.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.view.rowholders.ReportActionRowHolder;

import java.util.ArrayList;

public class ReportRowArrayAdapter extends ArrayAdapter<ReportRow> {
    private ArrayList<ReportRow> data;
    MainActivity activity;
    Report report;
    static final int layoutResourceId = R.layout.row_report_generic_action;

    public ReportRowArrayAdapter(MainActivity activity, ArrayList<ReportRow> data, Report report) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.data = data;
        this.report = report;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReportActionRowHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ReportActionRowHolder(row, activity, report);
            row.setTag(holder);
        }
        else
        {
            holder = (ReportActionRowHolder)row.getTag();
        }

        ReportRow reportRow = data.get(position);

        holder.populate(reportRow);

        return row;
    }


}
