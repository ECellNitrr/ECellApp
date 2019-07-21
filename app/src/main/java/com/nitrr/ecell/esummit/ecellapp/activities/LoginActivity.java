package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.forgotPassword.EmailFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnFocusChangeListener {

    private Context context;
    private Button signIn,register;
    private TextView toSignIn, toRegister, forgotPassword;
    private EditText loginEmail, loginPassword;
    private EditText firstName, lastName, registerPassword, registerEmail, registerNumber;
    private TextInputLayout loginEmailLayout, loginPasswordLayout, registerEmailLayout, registerPasswordLayout,
            firstNameLayout , lastNameLayout, registerNumberLayout;
    private LoginAnimation loginanimation;
    private AuthResponse authResponse;
    EmailFragment fragment = new EmailFragment();

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeViews();

        loginanimation = new LoginAnimation(this);
        loginanimation.toSignInScreen();

        loginEmail.clearFocus();

        signIn.setOnClickListener((View v) -> {
            //Validation for Sign In
            loginEmail.setTag(checkEmail(loginEmail, loginEmailLayout));
            loginPassword.setTag(checkPassword(loginPassword, loginPasswordLayout));

            if (loginEmail.getText().toString().equals("DebugMode"))
                startActivity(new Intent(this, HomeActivity.class));
            else if ((boolean) loginEmail.getTag() && (boolean) loginPassword.getTag())
                LoginApiCall();
        });

        forgotPassword.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.login_outer_constraint, fragment).addToBackStack(null).commit();
            signIn.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            loginEmail.setEnabled(false);
            loginPassword.setEnabled(false);
            signIn.setEnabled(false);
            toRegister.setEnabled(false);
        });

        register.setOnClickListener((View v) -> {
            //Validation for Register
            firstName.setTag(isNotEmpty(firstName, firstNameLayout));
            lastName.setTag(isNotEmpty(lastName, lastNameLayout));
            registerEmail.setTag(checkEmail(registerEmail, registerEmailLayout));
            registerPassword.setTag(checkPassword(registerPassword, registerPasswordLayout));
            registerNumber.setTag(checkPhone(registerNumber, registerNumberLayout));

            if((boolean)firstName.getTag() &&
                    (boolean)lastName.getTag() &&
                    (boolean)registerEmail.getTag() &&
                    (boolean)registerPassword.getTag() &&
                    (boolean)registerNumber.getTag()){
                register.setEnabled(false);
                RegisterApiCall();
            }
        });

        toRegister.setOnClickListener((View v) -> loginanimation.toRegisterScreen());

        toSignIn.setOnClickListener((View v) -> loginanimation.toSignInScreen());
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.register_first_name:
                if(!firstName.hasFocus())
                    isNotEmpty(firstName, firstNameLayout);
                break;

            case R.id.register_last_name:
                if(!lastName.hasFocus())
                    isNotEmpty(lastName, lastNameLayout);
                break;

            case R.id.register_email:
                if(!registerEmail.hasFocus())
                    checkEmail(registerEmail, registerEmailLayout);
                break;

            case R.id.register_password:
                if(!registerPassword.hasFocus())
                    checkPassword(registerPassword, registerPasswordLayout);
                break;

            case R.id.register_number:
                if(!registerNumber.hasFocus())
                    checkPhone(registerNumber, registerNumberLayout);
                break;

            case R.id.login_email:
                if(!loginEmail.hasFocus())
                    checkEmail(loginEmail, loginEmailLayout);
                break;

            case R.id.login_password:
                if(!loginPassword.hasFocus())
                    checkPassword(loginPassword, loginPasswordLayout);
                break;
        }
    }

    private void initializeViews() {
        context = this;
        toSignIn = findViewById(R.id.to_sign_in);
        toRegister = findViewById(R.id.to_register);
        forgotPassword = findViewById(R.id.forgot);

        signIn = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);

        loginEmailLayout = findViewById(R.id.login_email_layout);
        loginPasswordLayout = findViewById(R.id.login_password_layout);
        firstNameLayout = findViewById(R.id.register_first_name_layout);
        lastNameLayout = findViewById(R.id.register_last_name_layout);
        registerEmailLayout = findViewById(R.id.register_email_layout);
        registerPasswordLayout = findViewById(R.id.register_password_layout);
        registerNumberLayout = findViewById(R.id.register_number_layout);

        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        registerPassword = findViewById(R.id.register_password);
        registerEmail = findViewById(R.id.register_email);
        registerNumber = findViewById(R.id.register_number);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);

        firstName.setOnFocusChangeListener(this);
        lastName.setOnFocusChangeListener(this);
        registerEmail.setOnFocusChangeListener(this);
        registerPassword.setOnFocusChangeListener(this);
        registerNumber.setOnFocusChangeListener(this);
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
                registerNumber.getText().toString(),
                null, null, null);

        Call<AuthResponse> call = AppClient.getInstance().createService(APIServices.class).postRegisterUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                try {
                    if (getApplicationContext() != null && response.isSuccessful()) {
                        if (response.body() != null) {
                            dialog.dismiss();

                            Utils.showLongToast(LoginActivity.this, response.body().getMessage());
                            authResponse = response.body();

                            SharedPref pref = new SharedPref();
                            pref.setSharedPref(LoginActivity.this,
                                    authResponse.getToken(),
                                    details.getFirstName(),
                                    details.getLastName(),
                                    details.getEmail(),
                                    details.getContact(),
                                    details.getAvatar(),
                                    details.getFacebook(),
                                    details.getLinkedin());

                            if (details.getFacebook() != null)
                                pref.setIsLoggedIn(false, true, false);
                            else if (details.getLinkedin() != null)
                                pref.setIsLoggedIn(false, false, true);
                            else
                                pref.setIsLoggedIn(true, false, false);
                            startActivity(new Intent(context, HomeActivity.class));
                        } else {
                            dialog.dismiss();
                            Utils.showLongToast(getApplicationContext(), "Registration Failed");
                            Log.e("RegisterApiCall =====", "Response Body NULL.");
                            Log.e("RegisterApiCall =====", Objects.requireNonNull(response.errorBody()).string() + " ");
                        }
                    } else {
                        dialog.cancel();
                        Utils.showLongToast(getApplicationContext(), "Registration Failed");
                    }

                } catch (Exception e) {
                    dialog.cancel();
                    Log.e("RegisterApiCall =======", e.getMessage() + " ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                dialog.cancel();
                Utils.showLongToast(context, "Registration Failed" + t.getMessage());
            }
        });
    }

    private void LoginApiCall() {
        ProgressDialog loginDialog = ProgressDialog.show(this, "Signing in",
                "Please wait...", true);
        LoginDetails details = new LoginDetails(loginEmail.getText().toString(), loginPassword.getText().toString());

        Call<AuthResponse> call = AppClient.getInstance().createService(APIServices.class).postLoginUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                try {
                    if (getApplicationContext() != null && response.isSuccessful()) {
                        if (response.body() != null) {
                            loginDialog.dismiss();
                            Utils.showLongToast(LoginActivity.this, response.body().getMessage());
                            authResponse = response.body();
                            startActivity(new Intent(context, HomeActivity.class));
                        } else {
                            loginDialog.dismiss();
                            if (response.errorBody() != null) {
                                Utils.showLongToast(getApplicationContext(), response.errorBody().string());
                            }
                            Log.e("LoginApiCall =====", "Response Body NULL.");
                            Log.e("LoginApiCall =====", Objects.requireNonNull(response.errorBody()).string() + " ");
                        }
                    } else {
                        loginDialog.cancel();
                        if (response.errorBody() != null) {
                            Utils.showLongToast(getApplicationContext(), response.errorBody().string().split("\"")[7]);
                        }
                    }

                } catch (Exception e) {
                    loginDialog.cancel();
                    Log.e("LoginApiCall =======", e.getMessage() + " ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Utils.showLongToast(context, "There was an error " + t.getMessage());
            }
        });
    }

    private boolean checkPhone(EditText editText, TextInputLayout layout) {
        String phoneNo = editText.getText().toString();
        if(!isNotEmpty(editText, layout))
            return false;
        if (phoneNo.length() == 10) {
            if (phoneNo.charAt(0) == '6' || phoneNo.charAt(0) == '7' || phoneNo.charAt(0) == '8' || phoneNo.charAt(0) == '9')
                return true;
            else
                editText.setError("Invalid Number!");
        }
        else
            layout.setError("Enter a 10 digit number");
        return false;
    }

    private boolean checkPassword(EditText editText, TextInputLayout layout) {
        if(!isNotEmpty(editText, layout)) {
            return false;
        }
        if (editText.getText().length() >= 8)
            return true;
        editText.setError("Required Min 8 Characters!");
        return false;
    }

    private boolean checkEmail(EditText editText, TextInputLayout layout){
        if(!isNotEmpty(editText, layout))
            return false;
        String email = editText.getText().toString();
        int check = email.length() - 1;
        boolean dot = false;
        Character character;
        while (check >= 0) {
            character = email.charAt(check);
            if (character.compareTo('.') == 0 && !dot) {
                dot = true;
                check--;
            }

            if (dot)
                if (character.compareTo('@') == 0)
                    return true;
            check--;
        }
        layout.setError("Invalid Email!");
        return false;
    }

    private boolean isNotEmpty(EditText editText, TextInputLayout layout){
        if(!TextUtils.isEmpty(editText.getText()))
            return true;
        else
            layout.setError("Field Required!");
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        signIn.setVisibility(View.VISIBLE);
        register.setVisibility(View.VISIBLE);
        loginEmail.setEnabled(true);
        loginPassword.setEnabled(true);
        signIn.setEnabled(true);
        toRegister.setEnabled(true);
    }
}


//                        ****************** FACEBOOK IMPLEMENTATION! ******************
//
//        fbButton = findViewById(R.id.fb_button);
//        googleButton = findViewById(R.id.google_button);
//
//    private static final String EMAIL = "email";
//    CallbackManager callbackManager;
//
//        googleButton.setOnClickListener((view) -> startActivity(new Intent(context, HomeActivity.class)));
//
//        fbButton.setOnClickListener((view -> {
//
//            callbackManager = CallbackManager.Factory.create();
//
//            LoginManager.getInstance().registerCallback(callbackManager,
//                    new FacebookCallback<LoginResult>() {
//                        @Override
//                        public void onSuccess(LoginResult loginResult) {
//                            // App code
//                        }
//
//                        @Override
//                        public void onCancel() {
//                            // App code
//                        }
//
//                        @Override
//                        public void onError(FacebookException exception) {
//                            // App code
//                        }
//                    });
//        }));
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }