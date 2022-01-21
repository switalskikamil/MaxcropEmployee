package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.view.arrayadapters.IssueArrayAdapter;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowIssuesFragment extends Fragment {

    public static ShowIssuesFragment getInstance() {
        return new ShowIssuesFragment();
    }

    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_issues, container, false);
        this.activity = (MainActivity)getActivity();

        IssueArrayAdapter adapter = new IssueArrayAdapter(
                this.activity,
                (ArrayList<Issue>) this.activity.getSavedIssues()
        );


        ListView list = root.findViewById(R.id.report_list_view);

        list.setAdapter(adapter);

        list.setOnItemClickListener((parent, view, position, id) -> {
            final Issue issue = adapter.getItem(position);

        });
        return root;
    }
}