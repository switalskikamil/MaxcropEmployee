package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.reportform.DateFromOlderThanDateToException;
import com.maxcropdata.maxcropemployee.model.reportform.ReportForm;
import com.maxcropdata.maxcropemployee.model.reportform.ReportFormController;
import com.maxcropdata.maxcropemployee.model.reportform.ReportFormService;
import com.maxcropdata.maxcropemployee.model.reportform.TooLongTimeSpanRequested;
import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.request.ReportsForDatesServerRequest;
import com.maxcropdata.maxcropemployee.model.server.response.ReportsForDatesServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.RequestUnathorizedException;
import com.maxcropdata.maxcropemployee.model.server.response.ResponseMalformedException;
import com.maxcropdata.maxcropemployee.model.server.response.ServerResponse;
import com.maxcropdata.maxcropemployee.model.server.response.UexpectedResponseStatusException;
import com.maxcropdata.maxcropemployee.model.token.TokenController;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;
import com.maxcropdata.maxcropemployee.view.dialogs.AppDatePickerDialog;
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ShowDataFilterFragment extends Fragment {

    public static ShowDataFilterFragment getInstance() {
        return new ShowDataFilterFragment();
    }

    private MainActivity activity;
    private TextView dateFromText;
    private TextView dateToText;
    private Date dateFrom = new Date();
    private Date dateTo = new Date();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_show_data_filter, container, false);

        activity = (MainActivity)getActivity();

        final Button requestReportBtn = root.findViewById(R.id.btn_request_data_filter);
        final Button cancelBtn = root.findViewById(R.id.btn_cancel_data_filter);
        dateFromText = root.findViewById(R.id.data_filter_datefrom);
        dateToText = root.findViewById(R.id.data_filter_dateto);

        dateFromText.setOnClickListener(v -> AppDatePickerDialog.popDialog(
                activity,
                dateFrom,
                (date) -> {
                    dateFromText.setText(Helper.DATE_FORMAT.format(date));
                    dateFrom = date;
                })
        );

        dateToText.setOnClickListener(v -> AppDatePickerDialog.popDialog(
                activity,
                dateTo,
                (date) -> {
                    dateToText.setText(Helper.DATE_FORMAT.format(date));
                    dateTo = date;
                })
        );

        cancelBtn.setOnClickListener(v -> {
            activity.loadFragment(MainMenuFragment.getInstance());
        });

        requestReportBtn.setOnClickListener(v -> {
            verifyReportRequest();
        });

        return root;
    }

    private void verifyReportRequest() {
        final ReportForm form = new ReportForm();
        form.setWorkerId(activity.getUserAccount().getWorkerId());
        form.setDateFrom(dateFrom);
        form.setDateTo(dateTo);

        try {
            if (ReportFormController.verify(form)) {
                requestReportsForDates(
                        form,
                        activity.getUserAccount(),
                        activity.getServer());
            } else {
                Log.d("MCM", "Failed to verify report request. Reason: worker id = " + activity.getUserAccount().getWorkerId());
                MCToast.displayText(activity, Toast.LENGTH_LONG, getString(R.string.error_unathorized));
            }
        } catch (DateFromOlderThanDateToException e) {
            MCToast.displayText(
                    activity,
                    Toast.LENGTH_SHORT,
                    getString(R.string.date_from_cant_be_older)
            );
        } catch (TooLongTimeSpanRequested tooLongTimeSpanRequested) {
            MCToast.displayText(
                    activity,
                    Toast.LENGTH_SHORT,
                    getString(R.string.too_long_time_span)
            );
        }
    }

    private void requestReportsForDates(
            ReportForm form,
            Account account,
            Server server) {
        try {
            final ReportsForDatesServerRequest request = new ReportsForDatesServerRequest(
                    TokenController.generateToken(account),
                    ReportFormController.generateReportRequest(form),
                    server,
                    ((MainActivity) getActivity())
            );

            ServerController.getInstance().executeServerRequest(request);

        } catch (Exception e) {
            MCToast.displayText((MainActivity) getActivity(), Toast.LENGTH_SHORT, e.getMessage());
        }


    }
}