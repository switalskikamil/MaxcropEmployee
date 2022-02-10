package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

import java.util.ArrayList;

public class ReportColumnType {

    public static final String COL_DATE = "data";
    public static final String COL_AREA = "area";
    public static final String COL_PRODUCT_CLASS = "productClass";
    public static final String COL_WORK_TYPE = "workType";
    public static final String COL_PAYMENT_FOR = "paymentForInt";
    public static final String COL_DAY_START = "dayStart";
    public static final String COL_DAY_STOP = "dayStop";
    public static final String COL_WAGE = "wage";
    public static final String COL_TIME = "timeAsString";
    public static final String COL_AMOUNT = "amount";
    public static final String COL_WEIGHT = "weight";
    public static final String COL_TOTAL = "total";
    public static final String COL_HARVEST_PER_QUANTITY = "liczoneZaIlosc";
    public static final String COL_IS_FINAL = "finalValue";
    public static final String COL_IS_PRICE_GROUP = "isPriceGroupRecord";
    public static final String COL_PRICE_GROUP_ID = "idGrupyCenowej";
    public static final String COL_PRICE_GROUP_NAME = "pgGroupName";
    public static final String COL_PRICE_GROUP_CALC_TYPE = "pgGroupCalcType";
    public static final String COL_PRICE_GROUP_MIN = "pgGroupMin";
    public static final String COL_PRICE_GROUP_AVG = "pgGroupAvg";


    public static String getLabel(String column, MainActivity activity) {
        if (column.equals(COL_DATE)) return activity.getString(R.string.column_label_date);
        else if (column.equals(COL_AREA)) return activity.getString(R.string.column_label_area);
        else if (column.equals(COL_PRODUCT_CLASS)) return activity.getString(R.string.column_label_product_class);
        else if (column.equals(COL_WORK_TYPE)) return activity.getString(R.string.column_label_work_type);
        else if (column.equals(COL_PAYMENT_FOR)) return activity.getString(R.string.column_label_payment_for);
        else if (column.equals(COL_DAY_START)) return activity.getString(R.string.column_label_day_start);
        else if (column.equals(COL_DAY_STOP)) return activity.getString(R.string.column_label_day_stop);
        else if (column.equals(COL_WAGE)) return activity.getString(R.string.column_label_wage);
        else if (column.equals(COL_TIME)) return activity.getString(R.string.column_label_time);
        else if (column.equals(COL_AMOUNT)) return activity.getString(R.string.column_label_amount);
        else if (column.equals(COL_WEIGHT)) return activity.getString(R.string.column_label_weight);
        else if (column.equals(COL_TOTAL)) return activity.getString(R.string.column_label_total);
        else if (column.equals(COL_HARVEST_PER_QUANTITY)) return activity.getString(R.string.column_label_calculation_type);
        else if (column.equals(COL_PRICE_GROUP_CALC_TYPE)) return activity.getString(R.string.column_label_calculation_type);
        else if (column.equals(COL_IS_PRICE_GROUP)) return activity.getString(R.string.column_label_price_group);
        else if (column.equals(COL_PRICE_GROUP_NAME)) return activity.getString(R.string.column_label_price_group);
        else if (column.equals(COL_PRICE_GROUP_MIN)) return activity.getString(R.string.column_label_price_group_min);
        else if (column.equals(COL_PRICE_GROUP_AVG)) return activity.getString(R.string.column_label_price_group_avg);
        else return "?";
    }

    public static ArrayList<String> getColumnListOrdered(int actionType, boolean isPriceGroupRecord) {
        ArrayList<String> list = new ArrayList<>();

        list.add(COL_PAYMENT_FOR);
        list.add(COL_DATE);
        if (!isPriceGroupRecord) list.add(COL_AREA);
        if (!isPriceGroupRecord) list.add(COL_PRODUCT_CLASS);
        if (isPriceGroupRecord) list.add(COL_PRICE_GROUP_NAME);
        if (isPriceGroupRecord) list.add(COL_PRICE_GROUP_CALC_TYPE);
        if (isPriceGroupRecord) list.add(COL_PRICE_GROUP_MIN);
        if (isPriceGroupRecord) list.add(COL_PRICE_GROUP_AVG);
        list.add(COL_WORK_TYPE);
        list.add(COL_WAGE);
        if (actionType == ReportActionType.ACTION_PIECEWORK_HARVEST
                && !isPriceGroupRecord) list.add(COL_HARVEST_PER_QUANTITY);
        if (actionType == ReportActionType.ACTION_PIECEWORK_HARVEST) list.add(COL_AMOUNT);
        if (actionType == ReportActionType.ACTION_PIECEWORK_HARVEST) list.add(COL_WEIGHT);
        if (actionType != ReportActionType.ACTION_PIECEWORK_HARVEST) list.add(COL_DAY_START);
        if (actionType != ReportActionType.ACTION_PIECEWORK_HARVEST) list.add(COL_DAY_STOP);
        if (actionType != ReportActionType.ACTION_PIECEWORK_HARVEST) list.add(COL_TIME);
        list.add(COL_TOTAL);

        return list;
    }

}
