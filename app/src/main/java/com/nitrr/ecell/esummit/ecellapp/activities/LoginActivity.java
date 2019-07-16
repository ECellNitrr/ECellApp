package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HEAD;

public class LoginActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    private View signInDialog, registerDialog;
    private Context context;
    private RegisterDetails details;
    private ImageView lowerIcon, fbButton, googleButton, upperPoly;
    private Button signIn,register;
    private TextView toSignIn, toRegister;
    private EditText loginEmail, loginPassword;
    private EditText firstName, lastName, registerUsername, registerPassword, registerEmail, registerPhone;
    private LinearLayout loginLayout, registerLayout;
    private LoginAnimation loginanimation;
    private BroadcastReceiver receiver;
    private ConstraintLayout constraintLayout;
    TextInputLayout textInputLayout;

    private static final String EMAIL = "email";
    CallbackManager callbackManager;

    private AuthResponse authResponse;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Utils.showLongToast(this, displayMetrics.heightPixels + "");

        context = this;
        loginanimation = new LoginAnimation(this);
        loginanimation.toSignInScreen(this);

        signIn.setOnClickListener((View v) -> {
            signInDialog = Utils.showDialog(this, null, false, "Signing In...", "", null, null, null, null);
            LoginApiCall();
        });

        register.setOnClickListener((View v) -> {
//            registerDialog = Utils.showDialog(this, null, false, "Registering User...", "", null, null, null, null);
            firstName.setTag(isNotEmpty(firstName));
            lastName.setTag(isNotEmpty(lastName));
            registerEmail.setTag(checkEmail(registerEmail));
            registerPassword.setTag(checkPassword(registerPassword));
            registerPhone.setTag(checkPhone(registerPhone));

            if(isNotEmpty(firstName) &&
                    isNotEmpty(lastName) &&
                    checkEmail(registerEmail) &&
                    checkPassword(registerPassword) &&
                    checkPhone(registerPhone)) {
                register.setEnabled(false);
                RegisterApiCall();
            }
        });

        toRegister.setOnClickListener((View v) -> {
            loginanimation.toRegisterScreen(this);
        });

        toSignIn.setOnClickListener((View v) -> {
            loginanimation.toSignInScreen(this);
        });

        googleButton.setOnClickListener((view) -> startActivity(new Intent(context, HomeActivity.class)));

        fbButton.setOnClickListener((view -> {

            callbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    });
        }));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.e("focus changed====","view id is "+ v.getId());
        switch (v.getId()){
            case R.id.register_first_name:
                if(!firstName.hasFocus())
                isNotEmpty(firstName);
                break;
            case  R.id.register_last_name:
                if(!lastName.hasFocus())
                isNotEmpty(lastName);
                break;
            case R.id.register_email:
                if(!registerEmail.hasFocus())
                    checkEmail(registerEmail);
                break;
            case R.id.register_password:
                if(!registerPassword.hasFocus())
                    checkPassword(registerPassword);
                break;
            case R.id.register_number:
                if(!registerPhone.hasFocus())
                    checkPhone(registerPhone);
                break;
            case R.id.login_email:
                if(!loginEmail.hasFocus())
                    checkEmail(loginEmail);
                break;
            case R.id.login_password:
                if(!loginPassword.hasFocus())
                    checkPassword(loginPassword);
                break;
        }
    }

    private void initializeViews(){
        textInputLayout = findViewById(R.id.register_password_layout);
        constraintLayout = findViewById(R.id.login_outer_constraint);
        upperPoly = findViewById(R.id.upper_poly);

        toSignIn = findViewById(R.id.to_sign_in);
        toRegister = findViewById(R.id.to_register);

        loginLayout = findViewById(R.id.login_linear_layout);
        registerLayout = findViewById(R.id.register_linear_layout);
        lowerIcon = findViewById(R.id.ic_lower_ecell);

        signIn = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);

        fbButton = findViewById(R.id.fb_button);
        googleButton = findViewById(R.id.google_button);

        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        registerPassword = findViewById(R.id.register_password);
        registerEmail = findViewById(R.id.register_email);
        registerPhone = findViewById(R.id.register_number);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);

        toSignIn.setVisibility(View.INVISIBLE);

        firstName.setOnFocusChangeListener(this);
        lastName.setOnFocusChangeListener(this);
        registerEmail.setOnFocusChangeListener(this);
        registerPassword.setOnFocusChangeListener(this);
        registerPhone.setOnFocusChangeListener(this);
        loginEmail.setOnFocusChangeListener(this);
        loginPassword.setOnFocusChangeListener(this);
    }

    private void RegisterApiCall() {
        ProgressDialog dialog = ProgressDialog.show(this, "Registering User",
                "Please wait...", true);

        RegisterDetails details = new RegisterDetails(firstName.getText().toString(),
                lastName.getText().toString(),
                registerEmail.getText().toString(),
                registerPassword.getText().toString(),
                registerPhone.getText().toString(),
                null, null, null);

        Call<AuthResponse> call =  AppClient.getInstance().createService(APIServices.class).postRegisterUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                dialog.dismiss();
                try {
                    if (getApplicationContext() != null && response.isSuccessful()) {
                        if (response.body() != null) {
                            dialog.dismiss();
                            Utils.showLongToast(LoginActivity.this, response.body().getMessage());
                            authResponse = response.body();
                            SharedPref.setSharedPref(LoginActivity.this,
                                    authResponse.getToken(),
                                    details.getFirstName(),
                                    details.getLastName(),
                                    details.getEmail(),
                                    details.getContact(),
                                    details.getAvatar(),
                                    details.getFacebook(),
                                    details.getLinkedin());
                            if(details.getFacebook()!= null)
                                SharedPref.setIsLoggedIn(false,true,false);
                            else if(details.getLinkedin() != null)
                                SharedPref.setIsLoggedIn(false,false,true);
                            else
                                SharedPref.setIsLoggedIn(true,false,false);
                            startActivity(new Intent(context, HomeActivity.class));
                        }
                        else {
                            dialog.dismiss();
                            Log.e("RegisterApiCall =====", "Response Body NULL.");
                            Log.e("RegisterApiCall =====" , Objects.requireNonNull(response.errorBody()).string() + " ");
                        }
                    }

                } catch (Exception e){
                    dialog.dismiss();
                    Log.e("RegisterApiCall =======", e.getMessage() + " ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                dialog.dismiss();
                Utils.showLongToast(context, "Failed Response " + t.getMessage());
            }
        });
    }

    private void LoginApiCall() {
        LoginDetails details = new LoginDetails(loginEmail.getText().toString(), loginPassword.getText().toString());

        Call<AuthResponse> call =  AppClient.getInstance().createService(APIServices.class).postLoginUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {

                Log.e("response",response.toString());
                if(response.code() == 400) {
                    Utils.showLongToast(context, "Wrong username or password!");
                }

                else {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            if(!response.body().getMessage().equals("Login successful!")) {
                                Utils.showLongToast(context, "There was an error in logging in " + response.code());
                            }
                            else {
                                Utils.showLongToast(context, "User Logged In Successfully with token " + response.body().getToken());
                                SharedPref.setAccessToken(response.body().getToken());
//                                startActivity(new Intent(context, HomeActivity.class));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Utils.showLongToast(context, "There was an error " + t.getMessage());
            }
        });
    }

    private boolean checkPhone(EditText editText){
        if(!isNotEmpty(editText))
            return false;
        String phoneNo = registerPhone.getText().toString();
        if(editText.getText().toString().length() == 10){
            Character character = phoneNo.charAt(0);
            if(character.compareTo('6')==0 || character.compareTo('7')==0 || character.compareTo('8')==0 || character.compareTo('9')==0){
                try{
                    long no = Long.parseLong(phoneNo);
                    return true;
                }
                catch (Exception e){
                    registerPhone.setError("Please enter only numbers");
                }
            }
        }
        editText.setError("Invalid number");
        return false;
    }

    private boolean checkPassword(EditText editText) {
        if(!isNotEmpty(editText)) {
            return false;
        }
        if(editText.getText().length() >= 8)
            return true;
        editText.setError("Atleast 8 characters required");
        return false;
    }

    private boolean checkEmail(EditText editText){
        if(!isNotEmpty(editText))
            return false;
        String email = editText.getText().toString();
        int check = email.length()-1;
        boolean dot=false;
        Character character;
        while (check>=0){
            character = email.charAt(check);
            if(character.compareTo('.')==0 && !dot) {
                dot=true;
                check--;
            }

            if(dot)
                if(character.compareTo('@')==0)
                    return true;
            check--;
        }
        editText.setError("Enter email correctly");
        return false;
    }

    private boolean isNotEmpty(EditText editText){
        if(!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            editText.setError("This field is necessary to fill");
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        if(receiver !=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }

}
