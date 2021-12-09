package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.server.response.ReportsForDatesServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.RequestUnathorizedException;
import com.maxcropdata.maxcropemployee.model.server.response.ResponseMalformedException;
import com.maxcropdata.maxcropemployee.model.server.response.ServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.UexpectedResponseStatusException;
import com.maxcropdata.maxcropemployee.shared.interfaces.AsyncResponseProcessor;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowDataByDayFragment extends Fragment {

    private static ShowDataByDayFragment instance = new ShowDataByDayFragment();

    public static ShowDataByDayFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_data_by_day, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("data by day");
        return root;
    }

}