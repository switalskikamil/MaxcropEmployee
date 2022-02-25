package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import org.json.JSONException;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class LatestReportRequestsServiceTest {

    LatestReportRequests subject = new LatestReportRequests();

    @Test
    public void toJSON() {
        //GIVEN
        subject.add(new ReportRequest(new Date(0), new Date(0)));
        subject.add(new ReportRequest(new Date(100), new Date(100)));
        String recived = "";
        String expected = "[{\"dateTo\":100,\"dateFrom\":100},{\"dateTo\":0,\"dateFrom\":0}]";

        //WHEN
        try {
            recived = LatestReportRequestsService.toJSON(subject);
        } catch (Exception e) {
            fail(e.toString());
        }

        //THEN
        assertEquals(expected, recived);

    }

    @Test
    public void fromJSON() {
        //GIVEN
        String json = "[{\"dateTo\":100,\"dateFrom\":100},{\"dateTo\":0,\"dateFrom\":0}]";
        int expectedSize = 2;

        //WHEN
        try {
            subject = LatestReportRequestsService.fromJSON(json);
        } catch (Exception e) {
            fail(e.toString());
        }

        //THEN
        assertEquals(expectedSize, subject.getListSize());
    }
}