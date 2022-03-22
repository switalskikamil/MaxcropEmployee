package com.maxcropdata.maxcropemployee.model.issue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class IssueServiceTest {

    final IssueService underTests = new IssueService();
    final Issue issueA = new Issue.Builder()
            .fieldCode("GODZ_OD")
            .idAccount(23823)
            .issueDetails("Coś tu jest nie tak jak być powinno!")
            .reportedDay("2021-12-07")
            .fieldValue("20:00")
            .issueRegistrationDate(new Date(1638891908721L))
            .priceGroupId(0)
            .area("")
            .timeSpan("")
            .productClass("")
            .workType("")
            .build();

    final Issue issueB = new Issue.Builder()
            .fieldCode("WARTOSC_BRUTTO")
            .idAccount(23823)
            .issueDetails("Za niska kwota!")
            .reportedDay("2021-12-07")
            .fieldValue("200")
            .issueRegistrationDate(new Date(1638891908721L))
            .priceGroupId(0)
            .area("")
            .timeSpan("")
            .productClass("")
            .workType("")
            .build();

    final String expectedJson = "[{\"issueDbId\":0,\"fieldCode\":\"GODZ_OD\",\"fieldValue\":\"20:00\",\"issueDetails\":\"Coś tu jest nie tak jak być powinno!\",\"idAccount\":23823,\"reportedDay\":\"2021-12-07\",\"issueLocalId\":0,\"issueRegistrationDate\":1638891908721,\"reportRowPaymentForInt\":0,\"priceGroupId\":0,\"productClass\":\"\",\"area\":\"\",\"workType\":\"\",\"timeSpan\":\"\"},{\"issueDbId\":0,\"fieldCode\":\"WARTOSC_BRUTTO\",\"fieldValue\":\"200\",\"issueDetails\":\"Za niska kwota!\",\"idAccount\":23823,\"reportedDay\":\"2021-12-07\",\"issueLocalId\":0,\"issueRegistrationDate\":1638891908721,\"reportRowPaymentForInt\":0,\"priceGroupId\":0,\"productClass\":\"\",\"area\":\"\",\"workType\":\"\",\"timeSpan\":\"\"}]";

    @Test
    public void isProducingProperJSONFile() {
        // given issueA


        try {
            // when
            String jsonFile = underTests.toJSON(issueA);

            // then
            new JSONObject(jsonFile);
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void isProperlyReadingFromJSON() {

        try {
            // given issueA

            JSONObject issueJSON = new JSONObject(
                    underTests.toJSON(
                            issueA
                    )
            );


            // when
            Issue actualIssue = underTests.fromJSON(issueJSON);

            // then
            assertEquals(issueA, actualIssue);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isProperlyWritingListIntoJSONFile() {
        // given
        List<Issue> list = new ArrayList<>();
        list.add(issueA);
        list.add(issueB);



        try {
            // when
            String actualJson = underTests.allToJSON(list);

            // then
            assertEquals(expectedJson, actualJson);

        } catch (IllegalAccessException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void isProperluReadingListFromJSONArray() throws JSONException {
        // given
        JSONArray jArray = (JSONArray) new JSONTokener(expectedJson).nextValue();


        try {
            // when
            List<Issue> issues = underTests.allFromJSON(jArray);

            assertEquals(2, issues.size());
            assertEquals(issueA, issues.get(0));
            assertEquals(issueB, issues.get(1));
        } catch (Exception e) {
            fail(e.getMessage());
        }

    }
}