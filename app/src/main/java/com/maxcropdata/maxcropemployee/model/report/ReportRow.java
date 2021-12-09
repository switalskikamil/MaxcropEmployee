package com.maxcropdata.maxcropemployee.model.report;

import java.util.HashMap;


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
}
