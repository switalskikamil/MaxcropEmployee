package com.maxcropdata.maxcropemployee.view.rowholders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.pricegroup.PriceGroup;
import com.maxcropdata.maxcropemployee.model.pricegroup.PriceGroupService;
import com.maxcropdata.maxcropemployee.model.report.Report;
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
    private TextView priceGroupTitleText;
    private LinearLayout actionHolder;
    private LinearLayout actionDateHolder;
    private LinearLayout titleSectionHolder;
    private LinearLayout middleSectionHolder;
    private LinearLayout priceGroupTitleHolder;
    private ImageView rowTagIcon;
    private MainActivity activity;
    private Report report;

    public ReportActionRowHolder(View view, MainActivity activity, Report report) {
        actionDate = view.findViewById(R.id.action_date);
        actionLabel = view.findViewById(R.id.action_labour_label);
        actionDetailOne = view.findViewById(R.id.action_labour_detail_one);
        actionDetailTwo = view.findViewById(R.id.action_labour_detail_two);
        actionLabourValue = view.findViewById(R.id.action_labour_value);
        actionLabourWage = view.findViewById(R.id.action_labour_wage);
        actionMoneyValue = view.findViewById(R.id.action_money_value);
        actionHolder  =view.findViewById(R.id.action_holder);
        actionDateHolder  =view.findViewById(R.id.action_date_holder);
        titleSectionHolder  =view.findViewById(R.id.report_row_title_section);
        middleSectionHolder  =view.findViewById(R.id.report_row_midle_section);
        priceGroupTitleHolder  =view.findViewById(R.id.report_row_price_group_title);
        priceGroupTitleText  =view.findViewById(R.id.report_row_price_group_title_text);
        rowTagIcon = view.findViewById(R.id.row_tag_icon);
        this.activity = activity;
        this.report = report;
    }

    public void populate(ReportRow reportRow) {
        clearFields();
        final int actionTypeId = Integer.valueOf(reportRow.getColumn(ReportColumnType.COL_PAYMENT_FOR).toString());
        final boolean isTotal = (Boolean)reportRow.getColumn(ReportColumnType.COL_IS_FINAL);
        String actionLabelStr = ReportActionType.getLabel(actionTypeId, activity);

        if (isTotal && actionLabelStr.equals("?")) {
            actionLabelStr = activity.getString(R.string.column_label_total);
            markAsTotalRecord(reportRow);
        }

        actionDate.setText(reportRow.getColumnAsString(ReportColumnType.COL_DATE));
        actionLabel.setText(actionLabelStr);

        if (actionTypeId == ReportActionType.ACTION_DAY_BREAK) {
          populateDayBreakRecord(reportRow);
        } else if (actionTypeId == ReportActionType.ACTION_TIMEWORK
            || actionTypeId == ReportActionType.ACTION_BREAK
            || actionTypeId == ReportActionType.ACTION_PIECEWORK) {
            populateTimeRecord(reportRow);
            if (isTotal) markAsTotalRecord(reportRow);
        } else if (actionTypeId == ReportActionType.ACTION_PIECEWORK_HARVEST) {
            populateHarvestRecord(reportRow);
            if (isTotal) markAsTotalRecord(reportRow);
        } else if (actionTypeId == ReportActionType.ACTION_PAYMENT
            || actionTypeId == ReportActionType.ACTION_PAYMENT_OUT
            || actionTypeId == ReportActionType.ACTION_ACCOUNT_BALANCE) {

            populateAccountRecord(reportRow);
        }

        actionMoneyValue.setText(
                Helper.formatValue(reportRow.getColumnAsString(ReportColumnType.COL_TOTAL))
        );
    }

    private void populateDayBreakRecord(ReportRow reportRow) {
        actionDetailOne.setText("");
        actionDateHolder.setBackgroundColor(activity.getColor(R.color.colorPrimary));
        ViewGroup.LayoutParams params =  actionDateHolder.getLayoutParams();

        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        if (middleSectionHolder != null) middleSectionHolder.setVisibility(View.GONE);
        if (titleSectionHolder != null) titleSectionHolder.setVisibility(View.GONE);
        if (actionMoneyValue != null) actionMoneyValue.setVisibility(View.GONE);
        actionDateHolder.setLayoutParams(params);
        actionDetailOne.setText("");
        actionLabourValue.setText("");
        actionLabourWage.setText("");

    }

    private void markAsTotalRecord(ReportRow reportRow) {
        actionDetailOne.setText(R.string.column_label_total);
        rowTagIcon.setImageResource(R.drawable.icon_sum);
        actionHolder.setBackgroundColor(activity.getColor(R.color.colorGreenish));
        actionDateHolder.setBackgroundColor(activity.getColor(R.color.colorGreenishDark));
    }

    private void populateAccountRecord(ReportRow reportRow) {
        actionHolder.setBackgroundColor(activity.getColor(R.color.colorBlueish));
        actionDateHolder.setBackgroundColor(activity.getColor(R.color.colorBlueishDark));
        rowTagIcon.setImageResource(R.drawable.icon_sum);
        actionDetailOne.setText("");
        actionDetailTwo.setText("");
    }

    private void populateTimeRecord(ReportRow reportRow) {
        String wage = Helper.formatValue(reportRow.getColumnAsString(ReportColumnType.COL_WAGE))
                + "/h";

        String actionTime = reportRow.getColumn(ReportColumnType.COL_DAY_START) + " - " + reportRow.getColumn(ReportColumnType.COL_DAY_STOP);

        actionDetailOne.setText(actionTime);
        actionLabourValue.setText(reportRow.getColumn(ReportColumnType.COL_TIME).toString());
        actionLabourWage.setText(wage);
        actionDateHolder.setBackgroundColor(activity.getColor(R.color.colorVioletishDark));
        actionHolder.setBackgroundColor(activity.getColor(R.color.colorVioletish));
    }

    private void populateHarvestRecord(ReportRow reportRow) {
        Object areaColumn = reportRow.getColumn(ReportColumnType.COL_AREA);
        String actionArea ;
        if (areaColumn != null) actionArea = areaColumn.toString();
        else actionArea = "";

        String actionProductClass = reportRow.getColumn(ReportColumnType.COL_PRODUCT_CLASS).toString();

        String harvestType;
        if ((Boolean)reportRow.getColumn(ReportColumnType.COL_HARVEST_PER_QUANTITY)) {
            harvestType = activity.getString(R.string.label_quantity);
            actionLabourValue.setText(Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_AMOUNT).toString()));
        } else {
            harvestType = activity.getString(R.string.label_weight);
            actionLabourValue.setText(Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_WEIGHT).toString()));
        }

        String wage = Helper.formatValue(reportRow.getColumn(ReportColumnType.COL_WAGE).toString()) +
                "/" + harvestType;

        actionDetailOne.setText(actionArea);
        actionDetailTwo.setText(actionProductClass);
        actionLabourWage.setText(wage);
        actionDateHolder.setBackgroundColor(activity.getColor(R.color.colorBrownishDark));
        actionHolder.setBackgroundColor(activity.getColor(R.color.colorBrownishLight));

        int priceGroupParent = (int)reportRow.getColumn(ReportColumnType.COL_PRICE_GROUP_PARENT);
        if (priceGroupParent > 0) {
            String groupName = report.getPriceGroupById(priceGroupParent).getName();
            actionLabel.setText(actionLabel.getText().toString() + ": " + groupName );

        }
        if ((boolean)reportRow.getColumn(ReportColumnType.COL_IS_PRICE_GROUP)) {
            populatePriceGroupRecord(reportRow);
        }
    }

    private void populatePriceGroupRecord(ReportRow reportRow) {
        if (priceGroupTitleHolder != null) priceGroupTitleHolder.setVisibility(View.VISIBLE);
        if (priceGroupTitleText != null) {
            PriceGroup priceGroup = report.getPriceGroupById(
                    (int)reportRow.getColumn(ReportColumnType.COL_PRICE_GROUP_ID)
            );
            priceGroupTitleText.setText(
                    activity.getString(R.string.column_label_price_group)
                            + ": " + priceGroup.getName());

            actionDetailOne.setText(PriceGroupService.getCalculationTypeDescription(priceGroup.getCalcType(), activity));
            actionDetailTwo.setText("");
        }
    }

    private void clearFields() {
        actionDate.setText("");
        actionLabel.setText("");
        actionDetailOne.setText("");
        actionDetailTwo.setText("");
        actionLabourValue.setText("");
        actionLabourWage.setText("");
        actionMoneyValue.setText("");
        actionHolder.setBackgroundColor(activity.getColor(R.color.colorVioletish));
        rowTagIcon.setImageResource(R.drawable.calendar);
        ViewGroup.LayoutParams params =  actionDateHolder.getLayoutParams();

        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;

        if (middleSectionHolder!=null) middleSectionHolder.setVisibility(View.VISIBLE);
        if (titleSectionHolder!=null) titleSectionHolder.setVisibility(View.VISIBLE);
        if (actionMoneyValue!=null) actionMoneyValue.setVisibility(View.VISIBLE);
        if (priceGroupTitleHolder!=null) priceGroupTitleHolder.setVisibility(View.GONE);
        actionDateHolder.setLayoutParams(params);
    }
}
