package com.maxcropdata.maxcropemployee.model.report.reportrequest;

import java.util.ArrayList;
import java.util.Queue;

public class LatestReportRequests {

    public static final int MAX_SIZE = 5;
    // 0 - newest , MAX_SIZE - oldest
    private ReportRequest[] list = new ReportRequest[MAX_SIZE];

    public void add(ReportRequest request) {
        for (int i=MAX_SIZE-1; i>0; i--) {
            list[i] = list[i-1];
        }
        list[0] = request;
    }



    public ReportRequest[] getList() {
        return list;
    }

    public int getListSize() {
        int size = 0;
        for (int i=0; i<MAX_SIZE; i++) {
            if (list[i] != null) size++;
        }
        return size;
    }

    public ArrayList<ReportRequest> getAsArrayList() {
        final ArrayList<ReportRequest> l = new ArrayList<>();
        for (int i=0; i<MAX_SIZE; i++) {
            if (list[i] != null) l.add(list[i]);
        }
        return l;
    }
}
