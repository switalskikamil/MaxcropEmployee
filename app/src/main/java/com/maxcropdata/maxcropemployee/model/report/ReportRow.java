package com.maxcropdata.maxcropemployee.model.report;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.pricegroup.PriceGroup;
import com.maxcropdata.maxcropemployee.model.pricegroup.PriceGroupService;
import com.maxcropdata.maxcropemployee.shared.utils.Helper;

import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



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

    public ArrayList<ReportRowDetail> listDetails(MainActivity activity, Report report) throws ParseException {
        ArrayList<ReportRowDetail> details = new ArrayList<>();
        boolean oddRow = false;
        final int actionType = (Integer) getColumn(ReportColumnType.COL_PAYMENT_FOR);
        final Date rowDate = Helper.DATE_FORMAT.parse(getColumnAsString(ReportColumnType.COL_DATE));
        boolean isPriceGroupRecord = (Boolean)columns.get(ReportColumnType.COL_IS_PRICE_GROUP);

        if (isPriceGroupRecord) loadPriceGroupColumns(report, activity);

        for (String key : ReportColumnType.getColumnListOrdered(actionType, isPriceGroupRecord)) {
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
                    oddRow = !oddRow;
                }
            }
        }

        return details;
    }

    private void loadPriceGroupColumns(Report report, MainActivity activity) {
        int priceGroupId = (int) columns.get(ReportColumnType.COL_PRICE_GROUP_ID);
        final PriceGroup priceGroup = report.getPriceGroupById(priceGroupId);

        if (priceGroup != null) {
            columns.put(ReportColumnType.COL_PRICE_GROUP_NAME, priceGroup.getName());
            columns.put(ReportColumnType.COL_PRICE_GROUP_CALC_TYPE,
                    PriceGroupService.getCalculationTypeDescription(
                            priceGroup.getCalcType(),
                            activity));

            if (priceGroup.getCalcType() < 4) {
                columns.put(ReportColumnType.COL_PRICE_GROUP_AVG, priceGroup.getAverageWeight()
                        .setScale(2, RoundingMode.HALF_DOWN).toString());
            } else {
                columns.put(ReportColumnType.COL_PRICE_GROUP_AVG, priceGroup.getAverageQuantity()
                        .setScale(2, RoundingMode.HALF_DOWN).toString());
            }
            columns.put(ReportColumnType.COL_PRICE_GROUP_MIN, priceGroup.getMinimum()
                    .setScale(2, RoundingMode.HALF_DOWN).toString());
        }
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
