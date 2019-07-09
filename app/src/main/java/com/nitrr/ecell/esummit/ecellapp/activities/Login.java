package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity{

    Context context;
    private boolean isLoggingIn = true;
    ImageView lowerIcon, upArrow, downArrow, fbButton, googleButton;
    ImageButton toSignInButton, toRegisterButton;
    Button signin,register;
    TextView signInText;
    EditText loginusername, loginPassword;
    EditText firstName, lastName, registerUsername, registerPassword, email, mobileNumber;
    LinearLayout registerlayout;
    LoginAnimation loginanimation;
    EditText otp1,otp2,otp3,otp4;
    DialogInterface.OnClickListener confirmlistener = (dialog, which) -> dialog.cancel();
    DialogInterface.OnClickListener cancellistener = (dialog, which) -> dialog.cancel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        context = this;
        loginanimation = new LoginAnimation(this);

        signin.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
        });

        register.setOnClickListener((View v) -> {
            register.setEnabled(false);
            RegisterApiCall();
        });

        toRegisterButton.setOnClickListener((View v) ->
        {
            loginanimation.toRegisterScreen(this);
            isLoggingIn = false;
        });

        toSignInButton.setOnClickListener((View v) -> {
            loginanimation.toSignInScreen(this);
            isLoggingIn = true;
        });

        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });

        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    void initialize(){
        upArrow = findViewById(R.id.up_arrow);
        downArrow = findViewById(R.id.down_arrow);
        signInText = findViewById(R.id.to_sign_in_text);
        registerlayout = findViewById(R.id.lower_linear_layout);
        toSignInButton = findViewById(R.id.to_sign_in_button);
        toRegisterButton = findViewById(R.id.to_register_button);
        lowerIcon = findViewById(R.id.ic_lower_ecell);
        signin = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);
        toRegisterButton = findViewById(R.id.to_register_button);
        toSignInButton = findViewById(R.id.to_sign_in_button);
        fbButton = findViewById(R.id.fb_button);
        googleButton = findViewById(R.id.google_button);

        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        registerUsername = findViewById(R.id.register_username);
        registerPassword = findViewById(R.id.register_password);
        email = findViewById(R.id.register_email);
        mobileNumber = findViewById(R.id.register_number);

        signInText.setVisibility(View.INVISIBLE);
        toSignInButton.setVisibility(View.INVISIBLE);
        toSignInButton.setEnabled(false);
        toRegisterButton.setEnabled(true);
        upArrow.setVisibility(View.INVISIBLE);

        lowerIcon.animate().translationY(300f).setDuration(10).setInterpolator(new AccelerateInterpolator()).start();
    }


    void showOTPDialog() {
        View v =Utils.showDialog(this,R.layout.layout_otp,false,null,null,"CONFIRM",confirmlistener,"CANCEL",cancellistener);
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


    public void RegisterApiCall() {
        RegisterDetails details = new RegisterDetails(firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                registerPassword.getText().toString(),
                mobileNumber.getText().toString(),
                null, null, null);

        Call<RegisterResponse> call =  AppClient.getRetrofitInstance().postRegisterUser(details);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if(!response.isSuccessful()) {
                    Utils.showToast(context, "There was an error in post request");
                    return;
                }

                RegisterResponse response1 = response.body();

                if (response1 != null) {
                    if(response1.getMessage().equals("Registration failed!")) {
                        Utils.showToast(context, "there was an error in registering User");
                    }
                    else {
                        Utils.showToast(context, "User Registered Successfully with token " + response1.getToken());
                        Intent i = new Intent(context, Home.class);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Utils.showToast(context, "There was an error " + t.getMessage());
                register.setEnabled(true);
            }
        });
    }

    public void LoginApiCall() {
        RegisterDetails details = new RegisterDetails(firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                registerPassword.getText().toString(),
                mobileNumber.getText().toString(),
                null, null, null);

        Call<RegisterResponse> call =  AppClient.getRetrofitInstance().postRegisterUser(details);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if(!response.isSuccessful()) {
                    Utils.showToast(context, "There was an error in post request");
                    return;
                }

                RegisterResponse response1 = response.body();

                if (response1 != null) {
                    if(response1.getMessage().equals("Registration failed!")) {
                        Utils.showToast(context, "there was an error in registering User");
                    }
                    else {
                        Utils.showToast(context, "User Registered Successfully with token " + response1.getToken());
                        Intent i = new Intent(context, Home.class);
                        startActivity(i);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Utils.showToast(context, "There was an error " + t.getMessage());
                register.setEnabled(true);
            }
        });
    }
}
