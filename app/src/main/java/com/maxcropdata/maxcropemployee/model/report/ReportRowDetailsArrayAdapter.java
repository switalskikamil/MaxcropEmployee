package com.maxcropdata.maxcropemployee.model.report;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.view.rowholders.ReportActionRowDetailHolder;

import java.util.ArrayList;

public class ReportRowDetailsArrayAdapter extends ArrayAdapter<ReportRowDetail> {
    private ArrayList<ReportRowDetail> data;
    MainActivity activity;
    static final int layoutResourceId = R.layout.row_report_action_detail;

    public ReportRowDetailsArrayAdapter(MainActivity activity, ArrayList<ReportRowDetail> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ReportActionRowDetailHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ReportActionRowDetailHolder(row, activity);
            row.setTag(holder);
        }
        else
        {
            holder = (ReportActionRowDetailHolder)row.getTag();
        }

        ReportRowDetail reportRowDetail = data.get(position);

        holder.populate(reportRowDetail);

        return row;
    }
}
