package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.model.account.AccountController;
import com.maxcropdata.maxcropemployee.model.account.LoginTooShortException;
import com.maxcropdata.maxcropemployee.model.account.PasswordTooShortException;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.request.AccountLoginServerRequest;
import com.maxcropdata.maxcropemployee.model.token.Token;
import com.maxcropdata.maxcropemployee.model.token.TokenController;
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AccountSettingsFragment extends Fragment {

    public static final String PSWD_PLACEHOLDER = "*******";

    public static AccountSettingsFragment getInstance() {
        return new AccountSettingsFragment();
    }

    private MainActivity activity;
    private EditText loginEdit;
    private EditText passwordEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account_settings, container, false);
        this.activity = ((MainActivity)getActivity());

        loginEdit = root.findViewById(R.id.loginEdit);
        passwordEdit = root.findViewById(R.id.passwordEdit);

        final Button cancelBtn = root.findViewById(R.id.btn_cancel_acc);
        final Button saveBtn = root.findViewById(R.id.btn_save_acc);
        final Button serverSettingsBtn = root.findViewById(R.id.btn_server_settings);

        cancelBtn.setOnClickListener(v -> {
            activity.loadFragment(MainMenuFragment.getInstance());
        });

        saveBtn.setOnClickListener(v -> {
            saveChanges();
        });

        serverSettingsBtn.setOnClickListener(v -> {
            activity.loadFragment(ServerSettingsFragment.getInstance());
        });


        refreshData();

        return root;
    }

    private void saveChanges() {
        try {
            AccountController.loginAccount(
                    loginEdit.getText().toString(),
                    passwordEdit.getText().toString(),
                    activity);

            AccountController.saveAccountToFileSystem(getContext(), activity.getUserAccount());

            runServerLogin(activity.getUserAccount());

            //activity.loadFragment(MainMenuFragment.getInstance());

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (LoginTooShortException | PasswordTooShortException e ) {
            MCToast.displayText(activity, Toast.LENGTH_SHORT, getString(R.string.login_or_password_too_short));
        }
    }

    private void runServerLogin(Account account) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        // prepare request
        final AccountLoginServerRequest request = new AccountLoginServerRequest(
                TokenController.generateToken(account),
                activity.getServer(),
                activity
        );

        // perform server request
        ServerController.getInstance().executeServerRequest(request);
    }

    private void refreshData() {
        if (activity.getUserAccount() != null) {
            loginEdit.setText(activity.getUserAccount().getLogin());
            passwordEdit.setText(PSWD_PLACEHOLDER);
        } else {
            loginEdit.setText("");
            passwordEdit.setText("");
        }
    }
}