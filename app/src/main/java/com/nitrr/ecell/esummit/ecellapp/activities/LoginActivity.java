package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReciver;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    View signInDialog, registerDialog;
    Context context;
    private boolean isLoggingIn;
    RegisterDetails details;
    ImageView lowerIcon, fbButton, googleButton, upperPoly;
    Button signIn,register;
    TextView toSignIn, toRegister;
    EditText loginEmail, loginPassword;
    EditText firstName, lastName, registerUsername, registerPassword, registerEmail, mobileNumber;
    LinearLayout loginLayout, registerLayout;
    LoginAnimation loginanimation;
    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        initializeUserStatus();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Utils.showLongToast(this, displayMetrics.heightPixels + "");

        context = this;
        loginanimation = new LoginAnimation(this);
        loginanimation.toSignInScreen(this);

        signIn.setOnClickListener((View v) -> {
            signInDialog = Utils.showDialog(this, null, false, "Signing In...", null, null, null, null, null);
            LoginApiCall();
        });

        register.setOnClickListener((View v) -> {
            registerDialog = Utils.showDialog(this, null, false, "Registering User...", null, null, null, null, null);

            if(isNotEmpty(firstName) &&
                    isNotEmpty(lastName) &&
                    isNotEmpty(registerEmail) &&
                    isNotEmpty(registerPassword) &&
                    isNotEmpty(mobileNumber) &&
                    checkEmail(registerEmail) &&
                    checkPassword(registerPassword) &&
                    checkPhone(mobileNumber)){
                register.setEnabled(false);
            RegisterApiCall();
            }
        });

        toRegister.setOnClickListener((View v) -> {
            isLoggingIn = false;
            loginanimation.toRegisterScreen(this);
        });

        toSignIn.setOnClickListener((View v) -> {
            loginanimation.toSignInScreen(this);
            isLoggingIn = true;
        });
//
//        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });
//
//        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReciver();
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

    public void initializeViews(){
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

        googleButton.setOnClickListener((view) -> startActivity(new Intent(context, HomeActivity.class)));
        fbButton.setOnClickListener((view -> Utils.showNotification(this,"This is title","this is message",true)));

        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        registerPassword = findViewById(R.id.register_password);
        registerEmail = findViewById(R.id.register_email);
        mobileNumber = findViewById(R.id.register_number);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);

        toSignIn.setVisibility(View.INVISIBLE);
    }

    public void initializeUserStatus() {

    }

    public void RegisterApiCall() {
        RegisterDetails details = new RegisterDetails(firstName.getText().toString(),
                lastName.getText().toString(),
                registerEmail.getText().toString(),
                registerPassword.getText().toString(),
                mobileNumber.getText().toString(),
                null, null, null);

        Call<AuthResponse> call =  AppClient.getInstance().createService(APIServices.class).postRegisterUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                try {
                    if (LoginActivity.this != null && response.isSuccessful()) {
                        if (response.body() != null) {
                            Utils.showLongToast(LoginActivity.this, response.body().getMessage());

                            //TODO: Intent

                        } else {
                            Log.e("RegisterApiCall =====", "Response Body NULL.");
                            Log.e("RegisterApiCall =====" ,response.errorBody().string() + " ");
                        }
                    }

                } catch (Exception e){
                    Log.e("RegisterApiCall =======", e.getMessage() + " ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Utils.showLongToast(context, "Failed Response " + t.getMessage());
            }
        });
    }

    public void LoginApiCall() {
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

    boolean checkPhone(EditText editText){
        String phoneNo = mobileNumber.getText().toString();
        if(editText.getText().toString().length()==10){
            Character character = phoneNo.charAt(0);
            if(character.compareTo('6')==0 || character.compareTo('7')==0 || character.compareTo('8')==0 || character.compareTo('9')==0){
                try{
                    long no = Long.parseLong(phoneNo);
                    return true;
                }
                catch (Exception e){
                    mobileNumber.setError("Please enter only numbers");
                }
            }
        }
        editText.setError("Invalid number");
        return false;
    }

    boolean checkPassword(EditText editText) {
        if(editText.getText().length()>=8)
            return true;
        editText.setError("Atleast 8 characters required");
        return false;
    }

    boolean isNotEmpty(EditText editText){
        if(!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            editText.setError("This field is necessary to fill");
        return false;
    }

    boolean checkEmail(EditText editText){
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
}
