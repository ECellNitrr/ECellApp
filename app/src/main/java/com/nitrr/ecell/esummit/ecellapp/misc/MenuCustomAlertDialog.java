package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.nitrr.ecell.esummit.ecellapp.R;

public class MenuCustomAlertDialog {

    private AlertDialog alertDialog;
    private static MenuCustomAlertDialog dialog = null;
    private Activity activity;


    private MenuCustomAlertDialog() {
    }

    public static MenuCustomAlertDialog getInstance() {

        if (dialog == null)
            dialog = new MenuCustomAlertDialog();

        return dialog;
    }

    public MenuCustomAlertDialog with(Activity activity){
        this.activity = activity;
        return this;
    }

    public void build() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View alertView = activity.getLayoutInflater().inflate(R.layout.custom_alert_menu, null);

        builder.setView(alertView);

        alertDialog = builder.create();

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(true);

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
            params.gravity = Gravity.BOTTOM;
            params.verticalMargin = 0.05f;
            params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

            alertDialog.getWindow().setAttributes(params);
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.MenuDialogAnimation;
        }

        alertDialog.show();
    }
}
