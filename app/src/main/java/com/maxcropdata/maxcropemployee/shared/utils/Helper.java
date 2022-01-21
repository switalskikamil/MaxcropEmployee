package com.maxcropdata.maxcropemployee.shared.utils;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Helper {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /*
    formats number to two decimal paces
     */
    public static String formatValue(String value) {
        if (value != null && value.length() > 0) {
            BigDecimal tmp = new BigDecimal(value);
            tmp = tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
            return tmp.toString();
        } else return "0.00";
    }
}
