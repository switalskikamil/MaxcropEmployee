package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ServerSettingsFragment extends Fragment {

    private static ServerSettingsFragment instance = new ServerSettingsFragment();

    public static ServerSettingsFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_server_settings, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        textView.setText("server settings");
        return root;
    }
}