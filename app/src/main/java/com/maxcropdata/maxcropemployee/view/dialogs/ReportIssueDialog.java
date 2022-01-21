package com.maxcropdata.maxcropemployee.view.dialogs;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.issue.Issue;
import com.maxcropdata.maxcropemployee.model.issue.IssueController;
import com.maxcropdata.maxcropemployee.model.issue.IssueService;
import com.maxcropdata.maxcropemployee.model.report.Report;
import com.maxcropdata.maxcropemployee.model.report.ReportRowDetail;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.request.IssueRegistrationServerRequest;
import com.maxcropdata.maxcropemployee.model.token.TokenController;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import androidx.annotation.NonNull;

public class ReportIssueDialog extends AppDialog {
    private static final int LAYOUT_ID = R.layout.dialog_report_issue;
    private Button cancelbtn;
    private Button okButton;
    private EditText issueDetails;
    private TextView textCharacterCounter;
    private TextView fieldDate;
    private TextView fieldLabel;
    private TextView fieldValue;
    private static  final int MAX_LENGTH = 500;
    private ReportRowDetail detailField;

    public ReportIssueDialog(@NonNull MainActivity activity, ReportRowDetail detailField) {
        super(activity, LAYOUT_ID);

        this.setCancelable(true);

        this.detailField = detailField;

        registerControls();
    }

    private void registerControls() {
        cancelbtn = this.findViewById(R.id.btn_cancel);
        okButton = this.findViewById(R.id.btn_send);
        issueDetails = this.findViewById(R.id.issue_details);
        textCharacterCounter = this.findViewById(R.id.issue_character_counter);
        fieldDate = this.findViewById(R.id.issue_report_date);
        fieldLabel = this.findViewById(R.id.action_field_label);
        fieldValue = this.findViewById(R.id.action_field_value);

        prepareControls();
    }

    private void prepareControls() {

        fieldDate.setText(Helper.DATE_FORMAT.format(detailField.getReportRowDate()));
        fieldLabel.setText(detailField.getFieldLabel());
        fieldValue.setText(detailField.getFieldValue());

        issueDetails.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String counter = s.toString().length() + "/" + MAX_LENGTH;
                textCharacterCounter.setText(counter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancelbtn.setOnClickListener(v -> {
            dismiss();
        });

        okButton.setOnClickListener(v -> {
            try {

                final Issue issue = prepareIssue();

                //execute server request
                ServerController.getInstance().executeServerRequest(generateServerRequest(issue));

                //add this issue to local saved issue list -
                //it will be updated with DB id once server response will arrive
                getActivity().getSavedIssues().add(issue);

                //save file with issues
                IssueController.saveIssuesToFileSystem(getActivity(), getActivity().getSavedIssues());

            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | IllegalAccessException e) {
                MCToast.displayText(getActivity(), Toast.LENGTH_SHORT, getActivity().getString(R.string.error_problem_registering_issue));
                e.printStackTrace();
            }

            dismiss();
        });
    }

    private Issue prepareIssue() {
        return new Issue.Builder()
                .reportedDay(detailField.getReportRowDate())
                .fieldCode(detailField.getFieldId())
                .fieldValue(detailField.getFieldValue())
                .issueDetails(issueDetails.getText().toString())
                .idAccount(getActivity().getUserAccount().getAccountId())
                .issueLocalId(new Date().getTime())
                .issueRegistrationDate(new Date())
                .build();
    }

    private String createIssuePayload(Issue issue) throws IllegalAccessException {
        return new IssueService().toJSON(issue);
    }

    private IssueRegistrationServerRequest generateServerRequest(Issue issue) throws
            UnsupportedEncodingException, NoSuchAlgorithmException, IllegalAccessException {
        final IssueRegistrationServerRequest request = new IssueRegistrationServerRequest(
                TokenController.generateToken(getActivity().getUserAccount()),
                createIssuePayload(issue),
                getActivity().getServer(),
                getActivity()
        );

        return request;
    }
}
