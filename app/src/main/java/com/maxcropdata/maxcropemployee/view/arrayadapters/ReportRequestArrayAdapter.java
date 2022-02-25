package com.maxcropdata.maxcropemployee.view.arrayadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.reportrequest.ReportRequest;
import com.maxcropdata.maxcropemployee.view.rowholders.ReportRequestRowHolder;

import java.util.ArrayList;

public class ReportRequestArrayAdapter extends ArrayAdapter<ReportRequest> {

    private ArrayList<ReportRequest> data;
    MainActivity activity;
    static final int layoutResourceId = R.layout.row_latest_report_request;


    public ReportRequestArrayAdapter(MainActivity activity, ArrayList<ReportRequest> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReportRequestRowHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ReportRequestRowHolder(row, activity);
            row.setTag(holder);
        }
        else
        {
            holder = (ReportRequestRowHolder)row.getTag();
        }

        ReportRequest reportRequest = data.get(position);

        holder.populate(reportRequest);

        return row;
    }
}
