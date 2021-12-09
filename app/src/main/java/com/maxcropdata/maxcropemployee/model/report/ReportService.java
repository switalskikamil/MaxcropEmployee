package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.shared.interfaces.JSONAble;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public class ReportService implements JSONAble<Report> {

    private static final String COLS_DEFINITION = "cols_definition";
    private static final String COLUMNS = "cols";
    private static final String ROWS = "rows";
    private static final String ROW = "row";
    private static final String FIELD = "field";
    private static final String VALUE = "value";
    private static final String REPORT_DATE = "report_date";
    private static final String ACTION = "ACTION";

    private static ReportService instance = new ReportService();

    public static ReportService getInstance() {
        return instance;
    }

    @Override
    public String toJSON(Report s) throws IllegalAccessException, NoSuchFieldException {
        return null;
    }

    @Override
    public Report fromJSON(JSONObject json) throws JSONException, IllegalAccessException {
        final Report report;
        try {
            report = new Report(
                    Helper.DATE_FORMAT.parse(json.getString(REPORT_DATE))
            );

            JSONArray colDefs = json.getJSONArray(COLS_DEFINITION);
            JSONArray rows = json.getJSONArray(ROWS);

            for (int i=0; i < colDefs.length(); i++) {
                report.getColumnDefinition().add(colDefs.getJSONObject(i).getString(FIELD));
            }

            for (int j = 0; j < rows.length(); j++) {
                report.getReportRows().add(reportRowFromJSON(rows.getJSONObject(j)));
            }

        } catch (ParseException e) {
            throw new JSONException(e.toString());
        }

        return report;
    }

    private ReportRow reportRowFromJSON(JSONObject json) throws JSONException {
        final ReportRow reportRow;
        reportRow = new ReportRow(json.getInt(ROW));

        JSONArray columns = json.getJSONArray(COLUMNS);

        for (int i = 0; i < columns.length(); i++) {
            reportRow.getColumns().put(
              columns.getJSONObject(i).getString(FIELD),
              columns.getJSONObject(i).get(VALUE)
            );
        }

        return refineRow(reportRow);
    }

    /*
        field containing action id will have to be translated on the device
     */
    private ReportRow refineRow(ReportRow row) {
        if (row.getColumns().containsKey(ACTION)) {
            row.getColumns().put(ACTION, getLabelForActionId((Integer) row.getColumns().get(ACTION)));
        }
        return row;
    }

    private String getLabelForActionId(Integer actionId) {
        if (actionId == 0) return "?";
        else if (actionId == 1) return "START_TIME";
        else if (actionId == 2) return "START_PIECEWORK";
        else if (actionId == 3) return "BREAK";
        else if (actionId == 4) return "STOP";
        else return "?";
        //TODO add others - collection, holiday, pay outs, bonus etc
    }
}
