package com.maxcropdata.maxcropemployee.view.dialogs;

import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;

public class AppDatePickerDialog extends AppDialog {

    private static final int LAYOUT_ID = R.layout.dialog_data_picker;
    private Date dateToPickTo;
    private LinearLayout selectYearlayout;
    private DatePicker datePicker;
    private Button cancelbtn;
    private Button okButton;
    private OnSelectedListener listener;

    public AppDatePickerDialog(@NonNull MainActivity activity, Date dateToPickTo, OnSelectedListener listener) {
        super(activity, LAYOUT_ID);


        this.dateToPickTo = dateToPickTo;

        cancelbtn = this.findViewById(R.id.btn_cancel_datepicker);
        okButton = this.findViewById(R.id.btn_ok_datepicker);
        selectYearlayout = this.findViewById(R.id.datepicker_datepicker_year_layout);
        datePicker = this.findViewById(R.id.datepicker_datepicker);

        initDatePicker();

        cancelbtn.setOnClickListener(v -> {
            dismiss();
        });

        okButton.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, datePicker.getYear());
            c.set(Calendar.MONTH, datePicker.getMonth());
            c.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
            this.dateToPickTo = c.getTime();

            listener.onSelected(this.dateToPickTo);

            dismiss();
        });

    }

    private void initDatePicker() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateToPickTo);
        datePicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));

        selectYearlayout.setOnClickListener(v -> callYearSelect());

    }

    private void callYearSelect() {
        if (datePicker != null) datePicker.getTouchables().get(0).performClick();
    }

    public static void popDialog(
            MainActivity activity,
            Date dateToPickTo,
            OnSelectedListener listener,
            boolean callYearSelect) {
        if (dateToPickTo == null) dateToPickTo = new Date();

        final AppDatePickerDialog dialog = new AppDatePickerDialog(activity, dateToPickTo, listener);

        if (callYearSelect) dialog.callYearSelect();

        dialog.show();
    }

    public interface OnSelectedListener {
         void onSelected(Date date);
    }
}
