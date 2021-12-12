package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.account.Account;

public class MainMenuFragment extends Fragment {

    private static MainMenuFragment instance = new MainMenuFragment();

    public static MainMenuFragment getInstance() {
        instance.refreshData();
        return instance;
    }
    private Account userAccount;
    private TextView loginText;
    private TextView nameText;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);

        this.userAccount = ((MainActivity)getActivity()).getUserAccount();

        loginText = root.findViewById(R.id.user_login_txt);
        nameText = root.findViewById(R.id.user_name_txt);



        return root;
    }


    public void refreshData() {
        if (userAccount != null) {
            nameText.setText(userAccount.toShortString() );
            loginText.setText(userAccount.getLogin());
        }
    }
}