package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.AboutUsActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.models.PhoneNumber;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomHamburgerDialog {

    private AlertDialog alertDialog;
    private static CustomHamburgerDialog dialog = null;
    private SharedPref pref = new SharedPref();
    private Activity activity;
    private EditText oldNumber, newNumber;
    private DialogInterface.OnClickListener changeNumberConfirmListener = (dialog, which) ->{
        if(confirmNumber())
            dialog.dismiss();
    };
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> dialog.cancel();
    private DialogInterface.OnClickListener confirmlistener = (dialog,which) -> {
        //TODO add confirm code
    };


    private CustomHamburgerDialog(){
    }

    public static CustomHamburgerDialog getInstance() {

        if (dialog == null)
            dialog = new CustomHamburgerDialog();

        return dialog;
    }

    public CustomHamburgerDialog with(Activity activity){
        this.activity = activity;
        return this;
    }

    public void build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        View alertView = activity.getLayoutInflater().inflate(R.layout.bottom_hamburger, null);

        TextView item1 = alertView.findViewById(R.id.username);
        CardView item2 = alertView.findViewById(R.id.item2);
        CardView item3 = alertView.findViewById(R.id.item3);
        CardView item4 = alertView.findViewById(R.id.item4);

        item1.setText("Username");

        item2.setOnClickListener(v -> {
            showOTPDialog();
            builder.setOnDismissListener(DialogInterface::dismiss);
        });

        item3.setOnClickListener(v -> {
            Intent intent = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(intent);
        });

        item4.setOnClickListener(v -> logout());

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
        OTPDialogFragment fragment = new OTPDialogFragment().getInstance("",confirmlistener);
        fragment.setArguments(new Bundle());
        AppCompatActivity act = (AppCompatActivity) activity;
        act.getSupportFragmentManager().beginTransaction().replace(R.id.parentLayout, fragment).commit();
        alertDialog.dismiss();
        TextView changeNumber=null;
        if (changeNumber != null) {
            changeNumber.setOnClickListener(view -> {
                AlertDialog alertDialog = Utils.showDialog(activity, R.layout.layout_changenumber, false,
                        "Enter new Number", null, "Confirm", changeNumberConfirmListener, "Cancel", cancelListener);
                });
        }
        oldNumber = alertDialog.findViewById(R.id.old_number);
        newNumber = alertDialog.findViewById(R.id.new_number);
        sendOTP();
    }

    private void logout() {
        pref.getEditor(activity).clear().apply();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

    private boolean confirmNumber() {
        if(checkPhone(oldNumber) && checkPhone(newNumber))
            if(pref.getContact().contentEquals(oldNumber.getText().toString())){
                changeNumber(newNumber.getText().toString());
                return true;
            }
        return false;
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
        Call<PhoneNumber> call = AppClient.getInstance().createService(APIServices.class).changeNumber(newNumber);
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
