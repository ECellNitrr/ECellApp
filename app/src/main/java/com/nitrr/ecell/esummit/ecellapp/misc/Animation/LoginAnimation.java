package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;

public class LoginAnimation {

    private LinearLayout upperLinearLayout, lowerLinearLayout;
    private ImageView google, facebook, lowerPoly, upperPoly, /*downArrow, upArrow,*/ lowerECell, upperECell;
    private TextView toRegister, toSignIn;
    private TextView forgot;
    private Button signInButton, registerButton;
    private float distance;
    private EditText loginEmail, loginPassword, firstName, lastName, registerEmail, registerPassword, contact;

    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(LoginActivity view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        view.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.distance = displayMetrics.heightPixels * 0.71f;
        upperLinearLayout = view.findViewById(R.id.login_linear_layout);
        lowerLinearLayout = view.findViewById(R.id.register_linear_layout);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
        google = view.findViewById(R.id.google_button);
        facebook = view.findViewById(R.id.fb_button);
        forgot = view.findViewById(R.id.forgot);
        signInButton = view.findViewById(R.id.sign_in_button);
        registerButton = view.findViewById(R.id.register_button);
        toRegister = view.findViewById(R.id.to_register);
        toSignIn = view.findViewById(R.id.to_sign_in);
        upperECell = view.findViewById(R.id.ic_upper_ecell);
        lowerECell = view.findViewById(R.id.ic_lower_ecell);

        loginEmail = view.findViewById(R.id.login_email);
        loginPassword = view.findViewById(R.id.login_password);
        firstName = view.findViewById(R.id.register_first_name);
        lastName = view.findViewById(R.id.register_last_name);
        registerEmail = view.findViewById(R.id.register_email);
        registerPassword = view.findViewById(R.id.register_password);
        contact = view.findViewById(R.id.register_number);
    }

    public void toSignInScreen(Context context){
        doTranslationY(lowerPoly, distance);
        doTranslationY(upperPoly, distance);
        doTranslationY(lowerLinearLayout, distance);
        doTranslationY(upperLinearLayout, distance); //-1400f
        doTranslationY(google, distance); //-1280f
        doTranslationY(facebook, distance);
        doTranslationY(forgot, distance); //-1500f
        doTranslationY(signInButton, distance); //-1600f
        doAlphaTransition(signInButton, true);
        doTranslationY(registerButton, distance);

        doAlphaTransition(google, true);
        doAlphaTransition(facebook, true);
        doAlphaTransition(toRegister, true);
        doAlphaTransition(toSignIn, false);

        doAlphaTransition(upperECell, true);
        doTranslationY(upperECell, distance);
        doAlphaTransition(lowerECell, false);
        doTranslationY(lowerECell, distance);

        google.setEnabled(true);
        facebook.setEnabled(true);
        lowerLinearLayout.setEnabled(false);
        upperLinearLayout.setEnabled(true);

        doTranslationY(toSignIn, distance);
        toSignIn.setEnabled(false);

        doAlphaTransition(toRegister, true);
        doTranslationY(toRegister, distance);
        toRegister.setEnabled(true);

        clearAll(registerEmail);
        clearAll(registerPassword);
        clearAll(firstName);
        clearAll(lastName);
        clearAll(contact);
        loginPassword.setEnabled(true);
        loginEmail.setEnabled(true);
    }

    public void toRegisterScreen(Context context) {
        doTranslationY(lowerPoly, 0f);
        doTranslationY(upperPoly, 0f);
        doTranslationY(lowerLinearLayout, 0f);
        doTranslationY(upperLinearLayout, 0f);
        doTranslationY(google, 0f);
        doTranslationY(facebook, 0f);
        doTranslationY(forgot, 0f);
        doTranslationY(signInButton, 0f);
        doAlphaTransition(signInButton, false);
        doTranslationY(registerButton, 0f);

        doAlphaTransition(google, false);
        doAlphaTransition(facebook, false);
        upperLinearLayout.setEnabled(false);
        lowerLinearLayout.setEnabled(true);

        doTranslationY(upperECell, 0f);
        doTranslationY(lowerECell, 0f);
        doAlphaTransition(lowerECell, true);
        doAlphaTransition(upperECell, false);

        google.setEnabled(false);
        facebook.setEnabled(false);
        toSignIn.setVisibility(View.VISIBLE);
        toSignIn.setEnabled(true);

        toRegister.setEnabled(false);
        doAlphaTransition(toRegister, false);
        doTranslationY(toRegister, 0f);

        doAlphaTransition(toSignIn, true);
        toSignIn.setEnabled(true);
        doTranslationY(toSignIn, 0f);

        clearAll(loginEmail);
        clearAll(loginPassword);
        registerPassword.setEnabled(true);
        registerEmail.setEnabled(true);
        firstName.setEnabled(true);
        lastName.setEnabled(true);
        contact.setEnabled(true);
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

    private void clearAll(EditText text) {
        text.setText("");
        text.clearFocus();
        text.setEnabled(false);
    }
}