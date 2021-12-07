package com.maxcropdata.maxcropemployee.model.token;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenService implements JSONAble<Token> {
    @Override
    public String toJSON(Token s) throws IllegalAccessException {
        return JSONService.formatAsJSON(s);
    }


    @Override
    public Token fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        return null;
    }


}
