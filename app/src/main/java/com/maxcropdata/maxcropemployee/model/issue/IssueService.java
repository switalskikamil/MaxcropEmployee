package com.maxcropdata.maxcropemployee.model.issue;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONException;
import org.json.JSONObject;

public class IssueService implements JSONAble<Issue> {
    @Override
    public String toJSON(Issue s) throws IllegalAccessException {
        return JSONService.formatAsJSON(s);
    }

    @Override
    public Issue fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        Issue issue = new Issue();

        JSONService.readJSONIntoObject(json, issue);

        return issue;
    }
}
