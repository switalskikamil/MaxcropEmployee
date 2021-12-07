package com.maxcropdata.maxcropemployee.model.issue;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class IssueServiceTest {

    final IssueService underTests = new IssueService();

    @Test
    public void isProducingProperJSONFile() {
        // given
        Issue issue = new Issue.Builder()
                .fieldCode("GODZ_OD")
                .idAccount(23823)
                .issueDetails("Coś tu jest nie tak jak być powinno!")
                .reportedDay(new Date())
                .build();


        try {
            // when
            String jsonFile = underTests.toJSON(issue);

            // then
            new JSONObject(jsonFile);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void isProperlyReadingFromJSON() {

        try {
            // given
            Issue expectedIssue = new Issue.Builder()
                    .fieldCode("GODZ_OD")
                    .idAccount(23823)
                    .issueDetails("Coś tu jest nie tak jak być powinno!")
                    .reportedDay(new Date())
                    .build();

            JSONObject issueJSON = new JSONObject(
                    underTests.toJSON(
                            expectedIssue
                    )
            );


            // when
            Issue actualIssue = underTests.fromJSON(issueJSON);

            // then
            assertEquals(expectedIssue, actualIssue);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}