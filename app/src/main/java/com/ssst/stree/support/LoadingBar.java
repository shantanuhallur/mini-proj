package com.ssst.stree.support;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.ssst.stree.R;

public class LoadingBar {
    Activity activity;
    AlertDialog dialog;

    public LoadingBar(Activity activity) {
        this.activity = activity;
    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_layout,null));
        dialog = builder.create();
        dialog.show();
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}
