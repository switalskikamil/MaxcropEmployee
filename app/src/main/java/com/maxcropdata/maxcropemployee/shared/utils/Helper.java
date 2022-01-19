package com.maxcropdata.maxcropemployee.shared.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Helper {
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /*
    formats number to two decimal paces
     */
    public static String formatValue(String value) {
        BigDecimal tmp = new BigDecimal(value);
        tmp = tmp.setScale(2);
        return tmp.toString();
    }
}
