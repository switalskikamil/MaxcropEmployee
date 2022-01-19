package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReportService  {

    private static final String COLS_DEFINITION = "cols_definition";
    private static final String COLUMNS = "cols";
    private static final String ROWS = "rows";
    private static final String ROW = "row";
    private static final String FIELD = "field";
    private static final String VALUE = "value";
    private static final String REPORT_DATE = "report_date";
    private static final String ACTION = "paymentForInt";

    private static ReportService instance = new ReportService();

    public static ReportService getInstance() {
        return instance;
    }

    public Report fromJSON(JSONObject json, MainActivity activity) throws JSONException {
        final Report report = new Report();

        JSONArray colDefs = json.getJSONArray(COLS_DEFINITION);
        JSONArray rows = json.getJSONArray(ROWS);

        for (int i=0; i < colDefs.length(); i++) {
            report.getColumnDefinition().add(colDefs.getJSONObject(i).getString(FIELD));
        }

        for (int j = 0; j < rows.length(); j++) {
            report.getReportRows().add(reportRowFromJSON(rows.getJSONObject(j), activity));
        }

        return report;
    }

    private ReportRow reportRowFromJSON(JSONObject json, MainActivity activity) throws JSONException {
        final ReportRow reportRow;
        reportRow = new ReportRow(json.getInt(ROW));

        JSONArray columns = json.getJSONArray(COLUMNS);

        for (int i = 0; i < columns.length(); i++) {
            reportRow.getColumns().put(
              columns.getJSONObject(i).getString(FIELD),
              columns.getJSONObject(i).has(VALUE)?columns.getJSONObject(i).get(VALUE):null
            );
        }

        return reportRow;
    }

    /*
        field containing action id will have to be translated on the device
     */
    /*private ReportRow refineRow(ReportRow row, MainActivity activity) {
        if (row.getColumns().containsKey(ACTION)) {
            row.getColumns().put(ACTION, getLabelForActionId((Integer) row.getColumns().get(ACTION), activity));
        }
        return row;
    }*/

    /*private String getLabelForActionId(Integer actionId, MainActivity activity) {
        return ReportActionType.getLabel(actionId, activity);
    }*/
}
