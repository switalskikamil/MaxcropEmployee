package com.maxcropdata.maxcropemployee.model.pricegroup;

import android.util.Log;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PriceGroupService {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_CALC_TYPE = "calcType";
    private static final String FIELD_MIN = "min";
    private static final String FIELD_AVG_WEIGHT = "avgWeight";
    private static final String FIELD_AVG_QUANTITY = "avgQuantity";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_DATE = "date";

    public static List<PriceGroup> fromJSON(JSONArray jsonArray) throws JSONException {
        final List<PriceGroup> list = new ArrayList<>();

        for (int i=0; i < jsonArray.length(); i++) {
            list.add(parsePriceGroup(jsonArray.getJSONObject(i)));
        }

        return list;
    }

    private static PriceGroup parsePriceGroup(JSONObject o) throws JSONException {
        final PriceGroup priceGroup = new PriceGroup();

        if (o.has(FIELD_ID)) priceGroup.setId(o.getLong(FIELD_ID));
        if (o.has(FIELD_NAME)) priceGroup.setName(o.getString(FIELD_NAME));
        if (o.has(FIELD_CALC_TYPE)) priceGroup.setCalcType(o.getInt(FIELD_CALC_TYPE));
        if (o.has(FIELD_MIN)) priceGroup.setMinimum(new BigDecimal(o.getString(FIELD_MIN)));
        if (o.has(FIELD_AVG_WEIGHT)) priceGroup.setAverageWeight(new BigDecimal(o.getString(FIELD_AVG_WEIGHT)));
        if (o.has(FIELD_AVG_QUANTITY)) priceGroup.setAverageQuantity(new BigDecimal(o.getString(FIELD_AVG_QUANTITY)));
        if (o.has(FIELD_PRICE)) priceGroup.setPrice(new BigDecimal(o.getString(FIELD_PRICE)));
        if (o.has(FIELD_DATE)) priceGroup.setDate(new Date(o.getLong(FIELD_DATE)));
       // Log.d("MCM",priceGroup.toString() );
        return priceGroup;
    }

    public static String getCalculationTypeDescription(int type, MainActivity activity) {
        switch (type) {
            case 0:
                return activity.getString(R.string.price_group_calc_type_0);
            case 1:
                return activity.getString(R.string.price_group_calc_type_1);
            case 2:
                return activity.getString(R.string.price_group_calc_type_2);
            case 3:
                return activity.getString(R.string.price_group_calc_type_3);
            case 4:
                return activity.getString(R.string.price_group_calc_type_4);
            case 5:
                return activity.getString(R.string.price_group_calc_type_5);
            case 6:
                return activity.getString(R.string.price_group_calc_type_6);
            case 7:
                return activity.getString(R.string.price_group_calc_type_7);
            case 8:
                return activity.getString(R.string.price_group_calc_type_8);
            case 9:
                return activity.getString(R.string.price_group_calc_type_9);
            default:
                return "[?]";
        }
    }
}
