package com.maxcropdata.maxcropemployee.view.dialogs;

import android.widget.Button;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.view.AccountSettingsFragment;
import com.maxcropdata.maxcropemployee.view.RegisterFragment;

import androidx.annotation.NonNull;

public class LoginOrRegisterDialog extends AppDialog {

    private static final int LAYOUT_ID = R.layout.dialog_login_or_register;
    private Button loginBtn;
    private Button registerBtn;
    private Button serverSettingsBtn;

    public LoginOrRegisterDialog(@NonNull MainActivity activity) {
        super(activity, LAYOUT_ID);


        loginBtn = this.findViewById(R.id.do_login_btn);
        registerBtn = this.findViewById(R.id.do_register_btn);
        serverSettingsBtn = this.findViewById(R.id.do_serversettings_btn);

        loginBtn.setOnClickListener(v -> {
            activity.loadFragment(AccountSettingsFragment.getInstance());
            dismiss();
        });

        registerBtn.setOnClickListener(v -> {
            activity.loadFragment(RegisterFragment.getInstance());
            dismiss();
        });

    }

    public static void popDialog(MainActivity activity) {
        new LoginOrRegisterDialog(activity).show();

    }
}
