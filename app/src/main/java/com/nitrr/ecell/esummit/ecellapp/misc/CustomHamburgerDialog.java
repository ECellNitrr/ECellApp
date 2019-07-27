package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.AboutUsActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.fragments.ChangeNumberFragment;
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;

public class CustomHamburgerDialog {

    private AlertDialog alertDialog;
    private SharedPref pref = new SharedPref();
    private AppCompatActivity activity;

    public CustomHamburgerDialog() {
    }

    public CustomHamburgerDialog with(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public void build() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View alertView = activity.getLayoutInflater().inflate(R.layout.bottom_hamburger, null);

        TextView item1 = alertView.findViewById(R.id.username);
        CardView verifyNumber = alertView.findViewById(R.id.hamburger_verify_number);
        CardView changeNumber = alertView.findViewById(R.id.hamburger_change_number);
        CardView aboutUs = alertView.findViewById(R.id.hamburger_about_us);
        CardView logOut = alertView.findViewById(R.id.hamburger_log_out);

        if (pref.getMobileVerified(activity))
            verifyNumber.setVisibility(View.GONE);

        String name = "ECellApp Visitor";
        SharedPref pref = new SharedPref();
        if (pref.getFirstName(activity).equals("")) {
            String email = pref.getEmail(activity);
            try {
                name = email.split("@")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        item1.setText(name);

        verifyNumber.setOnClickListener(v -> {
            alertDialog.dismiss();
            showOTPDialog();
            builder.setOnDismissListener(DialogInterface::dismiss);
        });

        changeNumber.setOnClickListener(v -> {
            alertDialog.dismiss();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_parent_layout, new ChangeNumberFragment())
                    .addToBackStack(null)
                    .commit();
        });

        aboutUs.setOnClickListener(v -> {
            alertDialog.dismiss();
            Intent intent = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            alertDialog.dismiss();
            pref.clearPrefs(activity);
            Utils.showLongToast(activity, "Logged Out Successfully!");
            activity.finish();
            Intent i = new Intent(activity, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);
        });

        builder.setView(alertView);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(true);

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
            params.gravity = Gravity.TOP;

            params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

            alertDialog.getWindow().setAttributes(params);
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.MenuDialogAnimation;
        }
        alertDialog.show();
    }

    private void showOTPDialog() {
        OTPDialogFragment fragment = new OTPDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prevfrag", "Home Activity");
        fragment.setArguments(bundle);

        if (activity != null) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_parent_layout, fragment)
                    .addToBackStack(null)
                    .commit();
        }

        alertDialog.dismiss();
    }
}
