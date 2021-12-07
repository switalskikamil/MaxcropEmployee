package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowIssuesFragment extends Fragment {

    private static ShowIssuesFragment instance = new ShowIssuesFragment();

    public static ShowIssuesFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_issues, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("show issues");
        return root;
    }
}