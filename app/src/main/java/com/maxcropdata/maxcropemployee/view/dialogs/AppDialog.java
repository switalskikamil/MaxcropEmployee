package com.maxcropdata.maxcropemployee.view.dialogs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

public class AppDialog extends Dialog {

    public AppDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        setCancelable(false);
    }
}
