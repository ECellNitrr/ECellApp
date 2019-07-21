package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;

public class LoginAnimation {

    private LinearLayout upperLinearLayout, lowerLinearLayout;
//    private ImageView google, facebook;
    private ImageView lowerPoly;
    private ImageView upperPoly; /*downArrow, upArrow,*/ private ImageView lowerECell;
    private ImageView upperECell;
    private TextView toRegister, toSignIn, forgot;
    private TextView toRegisterText, toSignInText;
    private Button signInButton, registerButton;
    private float distance;
    private EditText loginEmail, loginPassword, firstName, lastName, registerEmail, registerPassword, registerNumber;
    private TextInputLayout loginEmailLayout, loginPasswordLayout, registerEmailLayout, registerPasswordLayout,
            firstNameLayout , lastNameLayout, registerNumberLayout;

    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(LoginActivity view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        view.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.distance = displayMetrics.heightPixels * 0.71f;
        upperLinearLayout = view.findViewById(R.id.login_linear_layout);
        lowerLinearLayout = view.findViewById(R.id.register_linear_layout);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
//        google = view.findViewById(R.id.google_button);
//        facebook = view.findViewById(R.id.fb_button);
        forgot = view.findViewById(R.id.forgot);
        signInButton = view.findViewById(R.id.sign_in_button);
        registerButton = view.findViewById(R.id.register_button);
        toRegisterText = view.findViewById(R.id.to_register_text);
        toRegister = view.findViewById(R.id.to_register);
        toSignInText = view.findViewById(R.id.to_sign_in_text);
        toSignIn = view.findViewById(R.id.to_sign_in);
        upperECell = view.findViewById(R.id.ic_upper_ecell);
        lowerECell = view.findViewById(R.id.ic_lower_ecell);

        loginEmail = view.findViewById(R.id.login_email);
        loginPassword = view.findViewById(R.id.login_password);
        firstName = view.findViewById(R.id.register_first_name);
        lastName = view.findViewById(R.id.register_last_name);
        registerEmail = view.findViewById(R.id.register_email);
        registerPassword = view.findViewById(R.id.register_password);
        registerNumber = view.findViewById(R.id.register_number);

        loginEmailLayout = view.findViewById(R.id.login_email_layout);
        loginPasswordLayout = view.findViewById(R.id.login_password_layout);
        firstNameLayout = view.findViewById(R.id.register_first_name_layout);
        lastNameLayout = view.findViewById(R.id.register_last_name_layout);
        registerEmailLayout = view.findViewById(R.id.register_email_layout);
        registerPasswordLayout = view.findViewById(R.id.register_password_layout);
        registerNumberLayout = view.findViewById(R.id.register_number_layout);
    }

    public void toSignInScreen(){
        doTranslationY(lowerPoly, distance);
        doTranslationY(upperPoly, distance);
        doTranslationY(lowerLinearLayout, distance);
        doTranslationY(upperLinearLayout, distance); //-1400f
//        doTranslationY(google, distance); //-1280f
//        doTranslationY(facebook, distance);
        doTranslationY(forgot, distance); //-1500f
        forgot.setEnabled(true);
        doTranslationY(signInButton, distance); //-1600f
        doAlphaTransition(signInButton, true);
        doTranslationY(registerButton, distance);

//        doAlphaTransition(google, true);
//        doAlphaTransition(facebook, true);

        doAlphaTransition(upperECell, true);
        doTranslationY(upperECell, distance);
        doAlphaTransition(lowerECell, false);
        doTranslationY(lowerECell, distance);

//        google.setEnabled(true);
//        facebook.setEnabled(true);
        lowerLinearLayout.setEnabled(false);
        upperLinearLayout.setEnabled(true);

        doTranslationY(toSignInText, distance);
        toSignInText.setEnabled(false);
        doAlphaTransition(toSignInText, false);
        doTranslationY(toSignIn, distance);
        toSignIn.setEnabled(false);
        doAlphaTransition(toSignIn, false);

        doAlphaTransition(toRegisterText, true);
        doTranslationY(toRegisterText, distance);
        toRegisterText.setEnabled(true);
        doAlphaTransition(toRegister, true);
        doTranslationY(toRegister, distance);
        toRegister.setEnabled(true);

        clearAll(registerEmail, registerEmailLayout);
        clearAll(registerPassword, registerPasswordLayout);
        clearAll(firstName, firstNameLayout);
        clearAll(lastName, lastNameLayout);
        clearAll(registerNumber, registerNumberLayout);
        loginPassword.setEnabled(true);
        loginEmail.setEnabled(true);
    }

    public void toRegisterScreen() {
        doTranslationY(lowerPoly, 0f);
        doTranslationY(upperPoly, 0f);
        doTranslationY(lowerLinearLayout, 0f);
        doTranslationY(upperLinearLayout, 0f);
        doTranslationY(forgot, 0f);
        forgot.setEnabled(false);
        doTranslationY(signInButton, 0f);
        doAlphaTransition(signInButton, false);
        doTranslationY(registerButton, 0f);

        upperLinearLayout.setEnabled(false);
        lowerLinearLayout.setEnabled(true);

        doTranslationY(upperECell, 0f);
        doTranslationY(lowerECell, 0f);
        doAlphaTransition(lowerECell, true);
        doAlphaTransition(upperECell, false);

        toRegisterText.setEnabled(false);
        doAlphaTransition(toRegisterText, false);
        doTranslationY(toRegisterText, 0f);
        toRegister.setEnabled(false);
        doAlphaTransition(toRegister, false);
        doTranslationY(toRegister, 0f);

        doAlphaTransition(toSignInText, true);
        toSignInText.setEnabled(true);
        doTranslationY(toSignInText, 0f);
        doAlphaTransition(toSignIn, true);
        toSignIn.setEnabled(true);
        doTranslationY(toSignIn, 0f);

        clearAll(loginEmail, loginEmailLayout);
        clearAll(loginPassword, loginPasswordLayout);
        registerPassword.setEnabled(true);
        registerEmail.setEnabled(true);
        firstName.setEnabled(true);
        lastName.setEnabled(true);
        registerNumber.setEnabled(true);

//        FACEBOOK IMPLEMENTATION
//        doTranslationY(google, 0f);
//        doTranslationY(facebook, 0f);
//        doAlphaTransition(google, false);
//        doAlphaTransition(facebook, false);
//        google.setEnabled(false);
//        facebook.setEnabled(false);
    }

    private void doTranslationY(View view, float value) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, value);
        animator.setDuration(800);
        animator.start();
    }

    private void doAlphaTransition(View view, boolean appear) {
        if(appear) {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f);
            animator.setDuration(700);
            animator.start();
        } //Appear = true

        else {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0);
            animator.setDuration(700);
            animator.start();
        } //Disappear = false
    }

    private void clearAll(EditText text, TextInputLayout layout) {
        text.setText("");
        text.clearFocus();
        layout.setError(null);
        text.setEnabled(false);
    }
}