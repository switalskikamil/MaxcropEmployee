package com.maxcropdata.maxcropemployee.model.token;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class TokenService implements JSONAble<Token> {

    private static TokenService instance = new TokenService();

    public static TokenService getInstance() {
        return instance;
    }

    @Override
    public String toJSON(Token s) throws IllegalAccessException {
        return JSONService.formatAsJSON(s);
    }


    @Override
    public Token fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        return null;
    }


}
