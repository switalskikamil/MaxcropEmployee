package com.maxcropdata.maxcropemployee.view.mctoast;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcropdata.maxcropemployee.MainActivity;
import com.maxcropdata.maxcropemployee.R;

public class MCToast extends Toast {

    private View view;

    public MCToast(MainActivity activity) {
        super(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.app_toast,
                activity.findViewById(R.id.toast_layout_root));
        super.setDuration(Toast.LENGTH_LONG);
        super.setView(view);
    }

    public MCToast(MainActivity activity, int duration) {
        super(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.app_toast,
                activity.findViewById(R.id.toast_layout_root));
        super.setDuration(duration);
        super.setView(view);
    }


    public static void displayText(MainActivity activity, int duration, String message) {
        new MCToast(activity, duration).showMsg(message);
    }

    public void showMsg(String message) {
        TextView msgView = view.findViewById(R.id.toast_message);

        msgView.setText(message);

        show();
    }
}
