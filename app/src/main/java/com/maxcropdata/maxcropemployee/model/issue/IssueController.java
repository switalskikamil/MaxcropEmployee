package com.maxcropdata.maxcropemployee.model.issue;

import android.content.Context;
import com.maxcropdata.maxcropemployee.shared.utils.FileManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.List;

public class IssueController {
    public static final String FILE_NAME = "issues.json";

    public static void saveIssuesToFileSystem(
            Context context,
            List<Issue> issues)
            throws IllegalAccessException {
        saveIssuesToFileSystem(context, issues, FILE_NAME);
    }

    public static void saveIssuesToFileSystem(
            Context context,
            List<Issue> issues,
            String fileName)
            throws IllegalAccessException {
        IssueService service = new IssueService();
        FileManager.writeFileToStorage(context, fileName, service.allToJSON(issues));
    }

    public static List<Issue> readIssuesFromFileSystem(Context context) throws IllegalAccessException, JSONException, InstantiationException {
        return readIssuesFromFileSystem(context, FILE_NAME);
    }

    public static List<Issue> readIssuesFromFileSystem(Context context, String fileName) throws IllegalAccessException, JSONException, InstantiationException {
        IssueService service = new IssueService();

        String file = FileManager.readFileFromStorage(context, fileName);

        JSONArray jArray = (JSONArray) new JSONTokener(file).nextValue();

        return service.allFromJSON(jArray);
    }
}
