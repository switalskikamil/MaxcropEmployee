package com.maxcropdata.maxcropemployee.model.issue;

import android.content.Context;

import org.json.JSONException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.test.core.app.ApplicationProvider;

import static org.junit.Assert.*;

public class IssueControllerTest {

    final String testFile = "test_issues.json";
    final Issue issueA = new Issue.Builder()
            .fieldCode("GODZ_OD")
            .idAccount(23823)
            .issueDetails("Coś tu jest nie tak jak być powinno!")
            .reportedDay("2021-12-07")
            .build();

    final Issue issueB = new Issue.Builder()
            .fieldCode("WARTOSC_BRUTTO")
            .idAccount(23823)
            .issueDetails("Za niska kwota!")
            .reportedDay("2021-12-07")
            .build();

    @Test
    public void isProperlySavingIssuesToFileSystem() {
        // given
        List<Issue> expectedIssues = new ArrayList<>();
        expectedIssues.add(issueA);
        expectedIssues.add(issueB);
        Context context = ApplicationProvider.getApplicationContext();

        // when
        try {
            IssueController.saveIssuesToFileSystem(context, expectedIssues, testFile);
            List<Issue> actualIssues = IssueController.readIssuesFromFileSystem(context, testFile);

            // then
            assertEquals(expectedIssues.get(0), actualIssues.get(0));
            assertEquals(expectedIssues.get(1), actualIssues.get(1));


        } catch (IllegalAccessException | JSONException | InstantiationException e) {
            fail(e.getMessage());
        }

    }

}