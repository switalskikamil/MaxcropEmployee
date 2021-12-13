package com.maxcropdata.maxcropemployee.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

public class AppDialog extends Dialog {

    public AppDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
    }
}
