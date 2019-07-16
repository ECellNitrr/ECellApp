package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.AboutUsActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;

public class MenuCustomAlertDialog {

    private AlertDialog alertDialog;
    private static MenuCustomAlertDialog dialog = null;
    private Activity activity;
    private TextView item1;
    private LinearLayout item2,item3,item4;
    EditText otp1,otp2,otp3,otp4;
    private String otp;
    DialogInterface.OnClickListener confirmlistener = (dialog, which) -> {
        otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
        dialog.dismiss(); };
    DialogInterface.OnClickListener cancellistener = (dialog, which) -> dialog.cancel();


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
        item1 = alertView.findViewById(R.id.username);
        item2 = alertView.findViewById(R.id.item2);
        item3 = alertView.findViewById(R.id.item3);
        item4 = alertView.findViewById(R.id.item4);
        item1.setText("Username");
        item2.setOnClickListener(v -> {
            showOTPDialog();
            builder.setOnDismissListener(dialog -> dialog.dismiss());
        });
        item3.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(intent);
        });
        //item4.setOnClickListener(v -> logout());

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

    private void logout() {
        SharedPref.clearPrefs();
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    void showOTPDialog() {
        View v =Utils.showDialog(activity,R.layout.layout_otp,false,null,null,"CONFIRM",confirmlistener,"CANCEL",cancellistener);
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp2.requestFocus();
            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp3.requestFocus();
                else if(s.length()==0)
                    otp1.requestFocus();
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp4.requestFocus();
                else if(s.length()==0)
                    otp2.requestFocus();
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(otp4.length()==0)
                    otp3.requestFocus();
            }
        });
    }
}
