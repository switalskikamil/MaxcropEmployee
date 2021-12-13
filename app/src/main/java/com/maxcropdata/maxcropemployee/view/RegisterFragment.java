package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.R;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private static RegisterFragment instance = new RegisterFragment();

    public static RegisterFragment getInstance() {
        return instance;
    }

    private EditText nameEdit;
    private EditText lastNameEdit;
    private EditText employerCodeEdit;
    private EditText passwordEdit;
    private EditText repeatPasswordEdit;
    private Date birthDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_register, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("register");
        return root;
    }

}