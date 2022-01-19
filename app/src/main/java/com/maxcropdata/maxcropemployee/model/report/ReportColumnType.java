package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

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
        else return "?";
    }

}
