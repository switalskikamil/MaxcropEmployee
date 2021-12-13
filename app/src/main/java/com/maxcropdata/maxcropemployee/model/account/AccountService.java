package com.maxcropdata.maxcropemployee.model.account;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;
import com.maxcropdata.maxcropemployee.shared.utils.PasswordUtils;

import org.json.JSONException;
import org.json.JSONObject;


public class AccountService implements JSONAble<Account> {



    @Override
    public String toJSON(Account s) throws IllegalAccessException {
        return JSONService.formatAsJSON(s);
    }

    @Override
    public Account fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        Account account = new Account();

        JSONService.readJSONIntoObject(json, account);

        return account;
    }


}
