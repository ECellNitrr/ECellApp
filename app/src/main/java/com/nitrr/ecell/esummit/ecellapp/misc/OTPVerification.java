package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
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
import com.nitrr.ecell.esummit.ecellapp.models.PhoneNumber;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerification {

    private AlertDialog alertDialog;
    SharedPref pref = new SharedPref();
    private static OTPVerification dialog = null;
    private Activity activity;
    private TextView item1;
    private LinearLayout item2,item3,item4;
    EditText otp1,otp2,otp3,otp4, oldNumber,newNumber;
    TextView changeNumber;
    private String otp;
    DialogInterface.OnClickListener changeNumberConfirmListener = (dialog,which) ->{
        if(confirmNumber())
            dialog.dismiss();
    };
    DialogInterface.OnClickListener confirmlistener = (dialog, which) -> {
        otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
        dialog.dismiss(); };
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> dialog.cancel();


    private OTPVerification() {

    }

    public static OTPVerification getInstance() {

        if (dialog == null)
            dialog = new OTPVerification();

        return dialog;
    }

    public OTPVerification with(Activity activity){
        this.activity = activity;
        return this;
    }

    public void build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        View alertView = activity.getLayoutInflater().inflate(R.layout.bottom_hamburger, null);
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
        item4.setOnClickListener(v -> logout());

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

    private void showOTPDialog() {
        AlertDialog v = Utils.showDialog(activity,R.layout.layout_otp,false,null,null,"CONFIRM",confirmlistener,"CANCEL",cancelListener);
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
                if(s.length() ==1 )
                    otp3.requestFocus();
                else if(s.length() ==0 )
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
                else
                    otp4.clearFocus();
            }
        });
        String otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString();
        changeNumber = v.findViewById(R.id.change_number);
        changeNumber.setOnClickListener(view -> {
            AlertDialog alertDialog =Utils.showDialog(activity,R.layout.layout_changenumber,false,
                    "Enter new Number",null,"Confirm",changeNumberConfirmListener,"Cancel",cancelListener);
        });
        oldNumber = alertDialog.findViewById(R.id.old_number);
        newNumber = alertDialog.findViewById(R.id.new_number);
        sendOTP();
    }

    private void logout() {
        pref.getEditor(activity).clear().apply();
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    private boolean confirmNumber() {
        if(checkPhone(oldNumber) && checkPhone(newNumber))
            if(pref.getContact().contentEquals(oldNumber.getText().toString())){
                changeNumber(newNumber.getText().toString());
                return true;
            }
        return false;
    }

    private boolean checkPhone(EditText editText){
        String phoneNo = editText.getText().toString();
        if(!isNotEmpty(editText))
            return false;
        if(phoneNo.length() == 10) {
            if(phoneNo.charAt(0) == '6' || phoneNo.charAt(0) == '7' || phoneNo.charAt(0) == '8' || phoneNo.charAt(0) == '9')
                return true;
            else
                editText.setError("Invalid Number!");
        }
        else
            editText.setError("Enter a 10 digit number");
        return false;
    }

    private boolean isNotEmpty(EditText editText){
        if(!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            editText.setError("Field Required!");
        return false;
    }

    private void changeNumber(String newNumber) {
        Call<PhoneNumber> call= AppClient.getInstance().createService(APIServices.class).changeNumber(newNumber);
        call.enqueue(new Callback<PhoneNumber>() {
            @Override
            public void onResponse(Call<PhoneNumber> call, Response<PhoneNumber> response) {
                if(response.isSuccessful() && !activity.isFinishing()){
                    PhoneNumber number = response.body();

                }
            }

            @Override
            public void onFailure(Call<PhoneNumber> call, Throwable t) {

            }
        });
    }

    private void sendOTP() {
    }
}
