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
import com.maxcropdata.maxcropemployee.view.mctoast.MCToast;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AccountSettingsFragment extends Fragment {

    private static AccountSettingsFragment instance = new AccountSettingsFragment();

    public static AccountSettingsFragment getInstance() {
        instance.refreshData();
        return instance;
    }
public static final String PSWD_PLACEHOLDER = "*******";

    private Account userAccount;
    private EditText loginEdit;
    private EditText passwordEdit;
    private Button cancelBtn;
    private Button saveBtn;
    private Button serverSettingsBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_account_settings, container, false);

        loginEdit = root.findViewById(R.id.loginEdit);
        passwordEdit = root.findViewById(R.id.passwordEdit);

        cancelBtn = root.findViewById(R.id.btn_cancel_acc);
        saveBtn = root.findViewById(R.id.btn_save_acc);
        serverSettingsBtn = root.findViewById(R.id.btn_server_settings);

        cancelBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).loadFragment(MainMenuFragment.getInstance());
        });

        saveBtn.setOnClickListener(v -> {
            try {
                if (AccountController.loginAccount(
                        loginEdit.getText().toString(),
                        passwordEdit.getText().toString(),
                        this.userAccount)) {
                    AccountController.saveAccountToFileSystem(getContext(), this.userAccount);
                } else {
                    MCToast.displayText((MainActivity)getActivity(), Toast.LENGTH_SHORT, getActivity().getString(R.string.login_or_password_too_short));
                }
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        serverSettingsBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).loadFragment(ServerSettingsFragment.getInstance());
        });


        this.userAccount = ((MainActivity)getActivity()).getUserAccount();

        return root;
    }

    private void refreshData() {
        if (this.userAccount != null) {
            loginEdit.setText(this.userAccount.getLogin());
            passwordEdit.setText(PSWD_PLACEHOLDER);
        } else {
            loginEdit.setText("");
            passwordEdit.setText("");
        }
    }
}