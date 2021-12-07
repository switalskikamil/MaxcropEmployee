package com.maxcropdata.maxcropemployee.shared.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONAble<T> {

    String toJSON(T s) throws IllegalAccessException, NoSuchFieldException;

    T fromJSON(JSONObject json) throws JSONException, IllegalAccessException;

}
