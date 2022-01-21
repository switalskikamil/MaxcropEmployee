package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/*
{
	"report_date":"2021-01-01",
	"cols_definition":
	[
		{"field":"GODZ_OD"},
		{"field":"GODZ_DO"},
		{"field":"ACTION"},
		{"field":"TOTAL_BRUTTO"}
	],
	"rows":
	[
        {
			"row":1,
			"cols":
			[
				{"field":"GODZ_OD", "value":"13:30"},
				{"field":"GODZ_DO", "value":"15:30"},
				{"field":"ACTION", "value":2}
			]
		},
		{
			"row":2,
			"cols":
			[
				{"field":"GODZ_OD", "value":"15:30"},
				{"field":"GODZ_DO", "value":"16:30"},
				{"field":"ACTION", "value":1}
			]
		}
	]
}
 */

// row of a report table
public class ReportRow {
    private int id;
    private HashMap<String, Object> columns;

    public ReportRow(int id) {
        this.id = id;
        columns = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public HashMap<String, Object> getColumns() {
        return columns;
    }

    public ArrayList<ReportRowDetail> listDetails(MainActivity activity) throws ParseException {
        ArrayList<ReportRowDetail> details = new ArrayList<>();
        boolean oddRow = false;
        final int actionType = (Integer) getColumn(ReportColumnType.COL_PAYMENT_FOR);
        final Date rowDate = Helper.DATE_FORMAT.parse(getColumnAsString(ReportColumnType.COL_DATE));

        for (String key : ReportColumnType.getColumnListOrdered(actionType)) {
            String fieldLabel = ReportColumnType.getLabel(key, activity);
            String fieldValue;

            if (!key.equals(ReportColumnType.COL_IS_FINAL)) {
                if (columns.get(key) != null) {
                    if (key.equals(ReportColumnType.COL_AMOUNT)
                    || key.equals(ReportColumnType.COL_WEIGHT)
                    || key.equals(ReportColumnType.COL_WAGE)
                    || key.equals(ReportColumnType.COL_TOTAL)) {
                        fieldValue = Helper.formatValue(getColumnAsString(key));
                    } else if (key.equals(ReportColumnType.COL_HARVEST_PER_QUANTITY)) {
                        if ((Boolean)columns.get(key)) fieldValue = activity.getString(R.string.column_calculation_for_quantity);
                        else fieldValue = activity.getString(R.string.column_calculation_for_weight);
                    } else if (key.equals(ReportColumnType.COL_PAYMENT_FOR)) {
                        fieldValue = ReportActionType.getLabel(actionType, activity);
                    } else fieldValue = getColumnAsString(key);

                    details.add(new ReportRowDetail(fieldLabel, fieldValue, key, rowDate, oddRow));
                    oddRow=!oddRow;
                }
            }
        }
        return details;
    }

    public Object getColumn(String key) {
        if (columns.containsKey(key)) return columns.get(key);
        else return null;
    }

    public String getColumnAsString(String key) {
        if (columns.containsKey(key) && columns.get(key) != null)
            return columns.get(key).toString();
        else return "";
    }
}
