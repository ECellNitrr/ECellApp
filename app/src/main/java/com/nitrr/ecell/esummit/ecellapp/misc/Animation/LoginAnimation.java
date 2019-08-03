package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.textfield.TextInputLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;

public class LoginAnimation {

    private LinearLayout upperLinearLayout, lowerLinearLayout;
//    private ImageView google, facebook;
    private ImageView upperPoly, lowerECell, upperECell,lowerPoly ;
    private TextView toRegister, toSignIn, forgot;
    private TextView toRegisterText, toSignInText;
    private Button signInButton, registerButton;
    private float distance;
    private EditText loginEmail, loginPassword, firstName, lastName, registerEmail, registerPassword, registerNumber;
    private TextInputLayout loginEmailLayout, loginPasswordLayout, registerEmailLayout, registerPasswordLayout,
            firstNameLayout , lastNameLayout, registerNumberLayout;

    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(LoginActivity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.distance = displayMetrics.heightPixels * 0.71f;
        upperLinearLayout = activity.findViewById(R.id.login_linear_layout);
        lowerLinearLayout = activity.findViewById(R.id.register_linear_layout);
        lowerPoly = activity.findViewById(R.id.lower_poly);
        upperPoly = activity.findViewById(R.id.upper_poly);
        forgot = activity.findViewById(R.id.forgot);
        signInButton = activity.findViewById(R.id.sign_in_button);
        registerButton = activity.findViewById(R.id.register_button);
        toRegisterText = activity.findViewById(R.id.to_register_text);
        toRegister = activity.findViewById(R.id.to_register);
        toSignInText = activity.findViewById(R.id.to_sign_in_text);
        toSignIn = activity.findViewById(R.id.to_sign_in);
        upperECell = activity.findViewById(R.id.ic_upper_ecell);
        lowerECell = activity.findViewById(R.id.ic_lower_ecell);

        loginEmail = activity.findViewById(R.id.login_email);
        loginPassword = activity.findViewById(R.id.login_password);
        firstName = activity.findViewById(R.id.register_first_name);
        lastName = activity.findViewById(R.id.register_last_name);
        registerEmail = activity.findViewById(R.id.register_email);
        registerPassword = activity.findViewById(R.id.register_password);
        registerNumber = activity.findViewById(R.id.register_number);

        loginEmailLayout = activity.findViewById(R.id.login_email_layout);
        loginPasswordLayout = activity.findViewById(R.id.login_password_layout);
        firstNameLayout = activity.findViewById(R.id.register_first_name_layout);
        lastNameLayout = activity.findViewById(R.id.register_last_name_layout);
        registerEmailLayout = activity.findViewById(R.id.register_email_layout);
        registerPasswordLayout = activity.findViewById(R.id.register_password_layout);
        registerNumberLayout = activity.findViewById(R.id.register_number_layout);

        //adding upper poly
        ConstraintLayout layout = activity.findViewById(R.id.login_outer_constraint);
        layout.removeView(upperPoly);
        layout.addView(upperPoly);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
        constraintSet.connect(upperPoly.getId(), ConstraintSet.START, layout.getId(), ConstraintSet.START, (int)((displayMetrics.density * 20f) + 0.5f));
        constraintSet.connect(upperPoly.getId(), ConstraintSet.END, layout.getId(), ConstraintSet.END, (int)((displayMetrics.density * 20f) + 0.5f));
        constraintSet.connect(upperPoly.getId(), ConstraintSet.BOTTOM, R.id.login_guide_17, ConstraintSet.TOP);
//        constraintSet.constrainDefaultHeight();
        constraintSet.constrainHeight(upperPoly.getId(), (int)(displayMetrics.heightPixels * 0.78));
        constraintSet.applyTo(layout);

        upperLinearLayout.bringToFront();
        upperECell.bringToFront();
        toSignInText.bringToFront();
        toSignIn.bringToFront();
        forgot.bringToFront();
    }

    public void toSignInScreen(){
        doTranslationY(lowerPoly, distance);
        doTranslationY(upperPoly, distance);
        doTranslationY(lowerLinearLayout, distance);
        doTranslationY(upperLinearLayout, distance);
        doTranslationY(forgot, distance);
        forgot.setEnabled(true);
        doTranslationY(signInButton, distance);
        doAlphaTransition(signInButton, true);
        doTranslationY(registerButton, distance);

        doAlphaTransition(upperECell, true);
        doTranslationY(upperECell, distance);
        doAlphaTransition(lowerECell, false);
        doTranslationY(lowerECell, distance);

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
        doTranslationY(signInButton, -150f);
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