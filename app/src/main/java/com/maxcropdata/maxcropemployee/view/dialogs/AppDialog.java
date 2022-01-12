package com.maxcropdata.maxcropemployee.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.maxcropdata.maxcropemployee.MainActivity;

import androidx.annotation.NonNull;

public class AppDialog extends Dialog {

    private MainActivity activity;

    public AppDialog(@NonNull MainActivity activity, int themeResId) {
        super(activity, themeResId);

        this.activity = activity;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        this.setContentView(themeResId);
    }

    public MainActivity getActivity() {
        return activity;
    }
}
