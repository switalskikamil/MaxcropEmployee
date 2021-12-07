package com.maxcropdata.maxcropemployee.shared.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;

public class JSONService {

    /*
        for specific fields
     */
    public static String formatAsJSON(Object o, Field[] listOfFields) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isAtLeastOneElement = false;
        for (Field f : listOfFields) {
            f.setAccessible(true);

            sb.append(
                    JSONService.formatAsJSONNode(f.getName(), f.get(o))
            );

            isAtLeastOneElement = true;
        }
        if (isAtLeastOneElement) sb.setLength(sb.length()-1);
        sb.append("}");

        return sb.toString();
    }

    /*
        for all fields
     */
    public static String formatAsJSON(Object o) throws IllegalAccessException {
        return formatAsJSON(o, o.getClass().getDeclaredFields());
    }

    private static String formatAsJSONNode(String name, Object value) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(name);
        sb.append("\":");

        if (
                value instanceof String
                || value instanceof BigDecimal
        )
            sb.append("\"" + value + "\"");
        else if (value instanceof  Date)
            sb.append(((Date)value).getTime() );
        else sb.append(value);

        sb.append(",");
        return sb.toString();
    }


    public static void readJSONIntoObject(JSONObject readFrom, Object readInto) throws JSONException, IllegalAccessException {
        for (Field f : readInto.getClass().getDeclaredFields()) {

            // skipping filling final fields
            if (Modifier.isFinal(f.getModifiers())) continue;

            f.setAccessible(true);
            if (readFrom.has(f.getName())) {
                // special case for BigDecimal values
                if (f.getType() == BigDecimal.class)
                    f.set(readInto , new BigDecimal(readFrom.get(f.getName()).toString()));
                else if (f.getType() == Date.class)
                    f.set(readInto , new Date(readFrom.getLong(f.getName())));
                else f.set(readInto, readFrom.get(f.getName()));
            }
        }
    }

}
