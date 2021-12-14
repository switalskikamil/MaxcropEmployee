package com.maxcropdata.maxcropemployee.view.dialogs;

import android.content.Context;
import android.widget.Button;
import android.widget.DatePicker;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;

public class AppDatePickerDialog extends AppDialog {

    private static final int LAYOUT_ID = R.layout.dialog_login_or_update;
    private Date dateToPickTo;
    private DatePicker datePicker;
    private Button cancelbtn;
    private Button okButton;
    private OnSelectedListener listener;

    public AppDatePickerDialog(@NonNull MainActivity activity, Date dateToPickTo, OnSelectedListener listener) {
        super(activity, LAYOUT_ID);

        this.dateToPickTo = dateToPickTo;

        cancelbtn = this.findViewById(R.id.btn_cancel_datepicker);
        okButton = this.findViewById(R.id.btn_ok_datepicker);
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

            listener.onSelected();

            dismiss();
        });

    }

    private void initDatePicker() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.dateToPickTo);
        datePicker.updateDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public static void popDialog(MainActivity activity, Date dateToPickTo, OnSelectedListener listener) {
        if (dateToPickTo == null) dateToPickTo = new Date();
        new AppDatePickerDialog(activity, dateToPickTo, listener).show();
    }

    public interface OnSelectedListener {
         void onSelected();
    }
}
