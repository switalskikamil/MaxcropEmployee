package com.maxcropdata.maxcropemployee.model.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
public class Report {
    private Date reportGenerationDate;
    private List<String> columnDefinition;
    private List<ReportRow> reportRows;

    public Report() {
        this.columnDefinition = new ArrayList<>();
        this.reportRows = new ArrayList<>();
        this.reportGenerationDate = new Date();
    }

    public Date getReportGenerationDate() {
        return reportGenerationDate;
    }

    public List<String> getColumnDefinition() {
        return columnDefinition;
    }

    public List<ReportRow> getReportRows() {
        return reportRows;
    }
}
