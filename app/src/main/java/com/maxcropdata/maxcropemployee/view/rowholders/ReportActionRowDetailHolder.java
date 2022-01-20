package com.maxcropdata.maxcropemployee.view.rowholders;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.ReportRowDetail;

public class ReportActionRowDetailHolder {
    private LinearLayout actionFieldHolder;
    private TextView actionFieldLabel;
    private TextView actionFieldValue;
    private MainActivity activity;


    public ReportActionRowDetailHolder(View view, MainActivity activity) {
        this.activity = activity;
        this.actionFieldHolder = view.findViewById(R.id.action_field_holder);
        this.actionFieldLabel = view.findViewById(R.id.action_field_label);
        this.actionFieldValue = view.findViewById(R.id.action_field_value);
    }

    public void populate(ReportRowDetail detail) {
        clearFields(detail.isOddRow());

        this.actionFieldLabel.setText(detail.getFieldLabel());
        this.actionFieldValue.setText(detail.getFieldValue());
    }

    private void clearFields(boolean isOddRow) {
        this.actionFieldLabel.setText("");
        this.actionFieldValue.setText("");

        if (isOddRow)
            actionFieldHolder.setBackgroundColor(activity.getColor(R.color.colorVioletishLight));
        else actionFieldHolder.setBackgroundColor(activity.getColor(R.color.white));
    }

}
