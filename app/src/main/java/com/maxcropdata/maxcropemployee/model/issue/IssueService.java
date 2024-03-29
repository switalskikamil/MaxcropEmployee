package com.maxcropdata.maxcropemployee.model.issue;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.JSONService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    String allToJSON(List<Issue> issues) throws IllegalAccessException {
        return JSONService.listToJSON(issues);
    }

    List<Issue> allFromJSON(JSONArray jsonArray)  {
        List<Issue> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++ ) {
            Issue o = new Issue();

            try {
                JSONService.readJSONIntoObject(jsonArray.getJSONObject(i), o);

                list.add(o);
            } catch (Exception e) {
                //e.printStackTrace();
            }


        }
        return list;
    }
}
