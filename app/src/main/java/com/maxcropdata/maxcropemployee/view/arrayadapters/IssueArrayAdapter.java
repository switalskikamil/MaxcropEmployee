package com.maxcropdata.maxcropemployee.view.arrayadapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.view.rowholders.IssueRowHolder;

import java.util.ArrayList;

public class IssueArrayAdapter extends ArrayAdapter<Issue> {

    private ArrayList<Issue> data;
    MainActivity activity;
    static final int layoutResourceId = R.layout.row_issue_main;

    public IssueArrayAdapter(MainActivity activity, ArrayList<Issue> data) {
        super(activity, layoutResourceId, data);
        this.activity = activity;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        IssueRowHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = activity.getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new IssueRowHolder(row, activity);
            row.setTag(holder);
        }
        else
        {
            holder = (IssueRowHolder)row.getTag();
        }

        Issue issue = data.get(position);

        holder.populate(issue);

        return row;
    }
}
