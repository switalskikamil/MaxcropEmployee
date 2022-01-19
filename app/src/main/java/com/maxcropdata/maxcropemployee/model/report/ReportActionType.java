package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

public class ReportActionType {
    public static final int ACTION_TIMEWORK = 1;
    public static final int ACTION_PIECEWORK = 2;
    public static final int ACTION_PIECEWORK_HARVEST = 22;
    public static final int ACTION_SWEDEN_OB = 31;
    public static final int ACTION_HOLIDAY_PAY = 32;
    public static final int ACTION_SURCHARGE = 6;
    public static final int ACTION_BREAK = 3;
    public static final int ACTION_DIFFERENCE = 34;
    public static final int ACTION_PAYMENT_OUT= 52;
    public static final int ACTION_PAYMENT = 53;
    public static final int ACTION_ACCOUNT_BALANCE = 54;

    public static String getLabel(int actionId, MainActivity activity) {
        switch (actionId) {
            case ACTION_TIMEWORK:
                return activity.getString(R.string.report_action_timework);

            case ACTION_PIECEWORK:
                return activity.getString(R.string.report_action_piecework);

            case ACTION_PIECEWORK_HARVEST:
                return activity.getString(R.string.report_action_harvest);

            case ACTION_SWEDEN_OB:
                return activity.getString(R.string.report_action_sweden_ob);

            case ACTION_HOLIDAY_PAY:
                return activity.getString(R.string.report_action_holiday);

            case ACTION_SURCHARGE:
                return activity.getString(R.string.report_action_surcharge);

            case ACTION_BREAK:
                return activity.getString(R.string.report_action_break);

            case ACTION_DIFFERENCE:
                return activity.getString(R.string.report_action_difference);

            case ACTION_PAYMENT_OUT:
                return activity.getString(R.string.report_action_pay_out);

            case ACTION_PAYMENT:
                return activity.getString(R.string.report_action_payment);

            case ACTION_ACCOUNT_BALANCE:
                return activity.getString(R.string.report_action_acc_balance);

            default:
                return "?";
        }
    }
}
