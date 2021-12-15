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

    public static MainMenuFragment getInstance() {
        return new MainMenuFragment();
    }
    private TextView loginText;
    private TextView nameText;
    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        this.activity = ((MainActivity)getActivity());

        loginText = root.findViewById(R.id.user_login_txt);
        nameText = root.findViewById(R.id.user_name_txt);

        final Button gotoAccBtn = root.findViewById(R.id.goto_account);
        final Button gotoIssuesBtn = root.findViewById(R.id.goto_issues);
        final Button gotoReportsBtn = root.findViewById(R.id.goto_reports);

        gotoAccBtn.setOnClickListener(v -> {
            activity.loadFragment(AccountSettingsFragment.getInstance());
        });

        gotoIssuesBtn.setOnClickListener(v -> {
            activity.loadFragment(ShowIssuesFragment.getInstance());
        });

        gotoReportsBtn.setOnClickListener(v -> {
            activity.loadFragment(ShowDataFilterFragment.getInstance());
        });

        refreshData();

        return root;
    }


    public void refreshData() {
        if (activity.getUserAccount() != null) {
            nameText.setText(activity.getUserAccount().toShortString() );
            loginText.setText(activity.getUserAccount().getLogin());
        } else {
            LoginOrRegisterDialog.popDialog(activity);
        }
    }
}