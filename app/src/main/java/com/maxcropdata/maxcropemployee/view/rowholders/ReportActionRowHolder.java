package com.maxcropdata.maxcropemployee.view.rowholders;

import android.view.View;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.report.ReportActionType;
import com.maxcropdata.maxcropemployee.model.report.ReportColumnType;
import com.maxcropdata.maxcropemployee.model.report.ReportRow;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

public class ReportActionRowHolder {
    private TextView actionDate;
    private TextView actionLabel;
    private TextView actionDetailOne;
    private TextView actionDetailTwo;
    private TextView actionLabourValue;
    private TextView actionLabourWage;
    private TextView actionMoneyValue;
    private MainActivity activity;

    public ReportActionRowHolder(View view, MainActivity activity) {
        actionDate = view.findViewById(R.id.action_date);
        actionLabel = view.findViewById(R.id.action_labour_label);
        actionDetailOne = view.findViewById(R.id.action_labour_detail_one);
        actionDetailTwo = view.findViewById(R.id.action_labour_detail_two);
        actionLabourValue = view.findViewById(R.id.action_labour_value);
        actionLabourWage = view.findViewById(R.id.action_labour_wage);
        actionMoneyValue = view.findViewById(R.id.action_money_value);
        this.activity = activity;
    }

    public void populate(ReportRow reportRow) {
        clearFields();
        final int actionTypeId = (Integer)reportRow.getColumn(ReportColumnType.COL_PAYMENT_FOR);

        actionDate.setText(reportRow.getColumn(ReportColumnType.COL_DATE).toString());
        actionLabel.setText(ReportActionType.getLabel(actionTypeId, activity));

        if (actionTypeId == ReportActionType.ACTION_TIMEWORK
        || actionTypeId == ReportActionType.ACTION_BREAK
        || actionTypeId == ReportActionType.ACTION_PIECEWORK) {
            populateTimeRecord(reportRow);
        } else if (actionTypeId == ReportActionType.ACTION_PIECEWORK_HARVEST) {
            populateHarvestRecord(reportRow);
        }

        actionMoneyValue.setText(
                Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_TOTAL).toString())
        );
    }

    private void populateTimeRecord(ReportRow reportRow) {
        String actionFrom = activity.getString(R.string.column_label_day_start)
                + ": " + reportRow.getColumn(ReportColumnType.COL_DAY_START);

        String actionTo = activity.getString(R.string.column_label_day_stop)
                + ": " + reportRow.getColumn(ReportColumnType.COL_DAY_STOP);

        String wage = Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_WAGE).toString())
                + "/h";

        actionDetailOne.setText(actionFrom);
        actionDetailTwo.setText(actionTo);
        actionLabourValue.setText(reportRow.getColumn(ReportColumnType.COL_TIME).toString());
        actionLabourWage.setText(wage);
    }

    private void populateHarvestRecord(ReportRow reportRow) {
        String actionArea = reportRow.getColumn(ReportColumnType.COL_AREA).toString();

        String actionProductClass = reportRow.getColumn(ReportColumnType.COL_PRODUCT_CLASS).toString();

        String harvestType;
        if ((Boolean)reportRow.getColumn(ReportColumnType.COL_HARVEST_PER_QUANTITY)) {
            harvestType = activity.getString(R.string.label_quantity);
            actionLabourValue.setText(reportRow.getColumn(ReportColumnType.COL_AMOUNT).toString());
        } else {
            harvestType = activity.getString(R.string.label_weight);
            actionLabourValue.setText(reportRow.getColumn(ReportColumnType.COL_WEIGHT).toString());
        }

        String wage = Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_WAGE).toString()) +
                "/" + harvestType;

        actionDetailOne.setText(actionArea);
        actionDetailTwo.setText(actionProductClass);
        actionLabourWage.setText(wage);
    }

    private void clearFields() {
        actionDate.setText("");
        actionLabel.setText("");
        actionDetailOne.setText("");
        actionDetailTwo.setText("");
        actionLabourValue.setText("");
        actionLabourWage.setText("");
        actionMoneyValue.setText("");
    }
}
