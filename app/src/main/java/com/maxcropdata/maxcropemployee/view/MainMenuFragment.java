package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.maxcropdata.maxcropemployee.R;

public class MainMenuFragment extends Fragment {

    private static MainMenuFragment instance = new MainMenuFragment();

    public static MainMenuFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_menu, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("main menu");
        return root;
    }
}