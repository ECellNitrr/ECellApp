package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.AboutUsActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.fragments.ChangeNumberFragment;
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.models.VerifyNumber.PhoneNumber;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomHamburgerDialog {

    private AlertDialog alertDialog;
    private SharedPref pref = new SharedPref();
    private Activity activity;
    private AppCompatActivity act = (AppCompatActivity) activity;
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> dialog.cancel();

    public CustomHamburgerDialog() {
    }

    public CustomHamburgerDialog with(Activity activity) {
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

        String name="ECellApp Visitor";
        SharedPref pref = new SharedPref();
        if(pref.getFirstName(activity).equals("")) {
            String email = pref.getEmail(activity);
            try {
                name = email.split("@")[0];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        item1.setText(name);

        verifyNumber.setOnClickListener(v -> {
            showOTPDialog();
            builder.setOnDismissListener(DialogInterface::dismiss);
        });

        changeNumber.setOnClickListener(v -> {
            alertDialog.dismiss();
            act.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_parent_layout, new ChangeNumberFragment())
                    .addToBackStack(null)
                    .commit();
        });

        aboutUs.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(intent);
        });

        logOut.setOnClickListener(v -> {
            pref.clearPrefs(activity);
            Utils.showLongToast(activity, "Logged Out Successfully!");
            activity.startActivity(new Intent(activity, LoginActivity.class));
            activity.finish();
        });

        builder.setView(alertView);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(true);

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
            params.gravity = Gravity.TOP;
            params.verticalMargin = 0.05f;
            params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;

            alertDialog.getWindow().setAttributes(params);
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.MenuDialogAnimation;
        }
        alertDialog.show();
    }

    private void showOTPDialog() {
        OTPDialogFragment fragment = new OTPDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prevfrag","Home Activity");
        fragment.setArguments(bundle);
        act.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_parent_layout, fragment)
                .addToBackStack(null)
                .commit();
        alertDialog.dismiss();
        sendOTP();
    }

    private boolean checkPhone(EditText editText) {
        String phoneNo = editText.getText().toString();

        if (!isNotEmpty(editText))
            return false;

        if (phoneNo.length() == 10) {
            if (phoneNo.charAt(0) == '6' || phoneNo.charAt(0) == '7' || phoneNo.charAt(0) == '8' || phoneNo.charAt(0) == '9')
                return true;
            else
                editText.setError("Invalid Number!");
        } else
            editText.setError("Enter a 10 digit number.");
        return false;
    }

    private boolean isNotEmpty(EditText editText) {
        if (!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            editText.setError("Field Required!");
        return false;
    }

    private void changeNumber(String newNumber) {
        Call<PhoneNumber> call = AppClient.getInstance().createService(APIServices.class).changeNumber("SDF");
        call.enqueue(new Callback<PhoneNumber>() {
            @Override
            public void onResponse(@NonNull Call<PhoneNumber> call, @NonNull Response<PhoneNumber> response) {
                if (response.isSuccessful() && !activity.isFinishing()) {
                    PhoneNumber number = response.body();

                }
            }

            @Override
            public void onFailure(@NonNull Call<PhoneNumber> call, @NonNull Throwable t) {
            }
        });
    }

    private void sendOTP() {

    }
}
