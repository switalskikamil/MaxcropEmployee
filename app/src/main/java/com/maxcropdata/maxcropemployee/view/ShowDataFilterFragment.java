package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.reportform.ReportForm;
import com.maxcropdata.maxcropemployee.model.reportform.ReportFormController;
import com.maxcropdata.maxcropemployee.model.reportform.ReportFormService;
import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.request.ReportsForDatesServerRequest;
import com.maxcropdata.maxcropemployee.model.server.response.ReportsForDatesServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.RequestUnathorizedException;
import com.maxcropdata.maxcropemployee.model.server.response.ResponseMalformedException;
import com.maxcropdata.maxcropemployee.model.server.response.ServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.UexpectedResponseStatusException;
import com.maxcropdata.maxcropemployee.model.token.TokenController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowDataFilterFragment extends Fragment {

    private static ShowDataFilterFragment instance = new ShowDataFilterFragment();

    public static ShowDataFilterFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_data_filter, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("data filter");
        return root;
    }



    public void requestReportsForDates(
            Account account,
            Date dateFrom,
            Date dateTo,
            Server server)
            throws
            UnsupportedEncodingException, NoSuchAlgorithmException,
            NoSuchFieldException, IllegalAccessException {

        ReportForm form = new ReportForm(
                account.getWorkerId(),
                dateFrom,
                dateTo
        );

        final ReportsForDatesServerRequest request = new ReportsForDatesServerRequest(
                TokenController.generateToken(account),
                ReportFormController.generateReportRequest(form),
                server,
                ((MainActivity)getActivity())
        );

        ServerController.getInstance().executeServerRequest(request);
    }
}