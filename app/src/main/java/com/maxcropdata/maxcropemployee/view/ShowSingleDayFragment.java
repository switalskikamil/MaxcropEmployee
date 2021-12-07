package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowSingleDayFragment extends Fragment {

    private static ShowSingleDayFragment instance = new ShowSingleDayFragment();

    public static ShowSingleDayFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_single_day, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("show single day");
        return root;
    }
}