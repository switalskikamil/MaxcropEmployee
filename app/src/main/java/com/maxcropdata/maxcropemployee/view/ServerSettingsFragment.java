package com.maxcropdata.maxcropemployee.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;
import com.maxcropdata.maxcropemployee.model.server.Server;
import com.maxcropdata.maxcropemployee.model.server.ServerController;
import com.maxcropdata.maxcropemployee.model.server.ServerService;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ServerSettingsFragment extends Fragment {

    public static ServerSettingsFragment getInstance() {
        return new ServerSettingsFragment();
    }

    private Button saveSettingsBtn;
    private Button cancelSettingsBtn;
    private EditText serverAddressText;
    private EditText serverProtocolText;
    private EditText serverAPIText;
    private EditText serverDatabaseText;

    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.activity = (MainActivity)getActivity();
        final Server server = activity.getServer();

        View root = inflater.inflate(R.layout.fragment_server_settings, container, false);

        findViews(root);

        loadData(server);

        saveSettingsBtn.setOnClickListener(v -> {
            server.setAddress(serverAddressText.getText().toString());
            server.setDatabase(serverDatabaseText.getText().toString());
            server.setProtocol(serverProtocolText.getText().toString());
            server.setWebserviceAddress(serverAPIText.getText().toString());

            ServerController.validate(server);

            try {
                ServerController.saveServerToFileSystem(activity, server);
                activity.loadFragment(MainMenuFragment.getInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        cancelSettingsBtn.setOnClickListener(v -> {
            activity.loadFragment(MainMenuFragment.getInstance());
        });

        return root;
    }

    private void loadData(Server server) {
        serverAddressText.setText(server.getAddress());
        serverDatabaseText.setText(server.getDatabase());
        serverAPIText.setText(server.getWebserviceAddress());
        serverProtocolText.setText(server.getProtocol());
    }


    private void findViews(View v) {
        saveSettingsBtn = v.findViewById(R.id.btn_save_server_settings);
        cancelSettingsBtn = v.findViewById(R.id.btn_cancel_settings);

        serverAddressText = v.findViewById(R.id.serverAddressText);
        serverDatabaseText = v.findViewById(R.id.serverDatabaseText);
        serverAPIText = v.findViewById(R.id.serverAPIText);
        serverProtocolText = v.findViewById(R.id.serverProtocolText);
    }
}