package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.Account;
import com.maxcropdata.maxcropemployee.view.dialogs.LoginOrRegisterDialog;

public class MainMenuFragment extends Fragment {

    private static MainMenuFragment instance = new MainMenuFragment();

    public static MainMenuFragment getInstance() {
        instance.refreshData();
        return instance;
    }
    private Account userAccount;
    private TextView loginText;
    private TextView nameText;
    private Button gotoAccBtn;
    private Button gotoIssuesBtn;
    private Button gotoReportsBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        this.userAccount = ((MainActivity)getActivity()).getUserAccount();

        loginText = root.findViewById(R.id.user_login_txt);
        nameText = root.findViewById(R.id.user_name_txt);

        gotoAccBtn = root.findViewById(R.id.goto_account);
        gotoIssuesBtn = root.findViewById(R.id.goto_issues);
        gotoReportsBtn = root.findViewById(R.id.goto_reports);

        gotoAccBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).loadFragment(AccountSettingsFragment.getInstance());
        });

        gotoIssuesBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).loadFragment(ShowIssuesFragment.getInstance());
        });

        gotoReportsBtn.setOnClickListener(v -> {
            ((MainActivity)getActivity()).loadFragment(ShowDataFilterFragment.getInstance());
        });

        return root;
    }


    public void refreshData() {
        if (userAccount != null) {
            nameText.setText(userAccount.toShortString() );
            loginText.setText(userAccount.getLogin());
        } else {
            LoginOrRegisterDialog.popDialog((MainActivity) getActivity());
        }
    }
}