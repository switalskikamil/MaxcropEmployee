package com.maxcropdata.maxcropemployee.view.dialogs;

import android.content.Context;

import com.maxcropdata.maxcropemployee.R;

import androidx.annotation.NonNull;

public class LoginOrRegisterDialog extends AppDialog {

    private static final int LAYOUT_ID = R.layout.dialog_login_or_update;


    public LoginOrRegisterDialog(@NonNull Context context) {
        super(context, LAYOUT_ID);
    }
}
