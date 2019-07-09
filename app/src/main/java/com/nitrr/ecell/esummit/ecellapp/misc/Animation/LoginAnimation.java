package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.Login;

public class LoginAnimation {

    private LinearLayout upperLinearLayout, lowerLinearLayout;
    private ImageView google, facebook, lowerPoly, upperPoly, downArrow, upArrow, lowerECell, upperECell;
    private ImageButton toRegisterButton, toSignInButton;
    private TextView forgot, toRegisterText, toSignInText;
    private Button signInButton, registerButton;
    private float pHeight, nHeight;

    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(Login view){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        view.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.pHeight = displayMetrics.heightPixels * 0.6310f;
        this.nHeight = -1 * pHeight;
        upperLinearLayout = view.findViewById(R.id.upper_linear_layout);
        lowerLinearLayout = view.findViewById(R.id.lower_linear_layout);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
        google = view.findViewById(R.id.google_button);
        facebook = view.findViewById(R.id.fb_button);
        forgot = view.findViewById(R.id.forgot);
        toRegisterText = view.findViewById(R.id.to_register_text);
        signInButton = view.findViewById(R.id.sign_in_button);
        toRegisterButton = view.findViewById(R.id.to_register_button);
        downArrow = view.findViewById(R.id.down_arrow);
        registerButton = view.findViewById(R.id.register_button);
        upArrow = view.findViewById(R.id.up_arrow);
        toSignInButton = view.findViewById(R.id.to_sign_in_button);
        toSignInText = view.findViewById(R.id.to_sign_in_text);
        upperECell = view.findViewById(R.id.ic_upper_ecell);
        lowerECell = view.findViewById(R.id.ic_lower_ecell);
    }

    public void toRegisterScreen(Context context){
        doTranslationY(lowerPoly, nHeight*1.05f);
        doTranslationY(upperPoly, nHeight);
        doTranslationY(lowerLinearLayout, nHeight*1.05f);
        doTranslationY(upperLinearLayout, nHeight * 1.0769f); //-1400f
        doTranslationY(google, nHeight * 0.9846f); //-1280f
        doTranslationY(facebook, nHeight * 0.9846f);
        doTranslationY(forgot, nHeight * 1.1538f); //-1500f
        doTranslationY(signInButton, nHeight * 1.2307f); //-1600f
        doTranslationY(registerButton, nHeight*1.05f);

        doAlphaTransition(google, false);
        doAlphaTransition(facebook, false);
        doAlphaTransition(toRegisterText, false);
        doAlphaTransition(downArrow, false);
        doAlphaTransition(upArrow, true);
        doAlphaTransition(toRegisterButton, false);
        doAlphaTransition(toSignInButton, true);
        doAlphaTransition(toSignInText, true);

        doAlphaTransition(upperECell, false);
        doTranslationY(upperECell, nHeight);

        doAlphaTransition(lowerECell, true);
        doTranslationY(lowerECell, nHeight*1.05f);

        google.setEnabled(false);
        facebook.setEnabled(false);
        upArrow.setVisibility(View.VISIBLE);
        toSignInButton.setVisibility(View.VISIBLE);
        toSignInButton.setEnabled(true);
        toSignInText.setVisibility(View.VISIBLE);
        lowerECell.setVisibility(View.VISIBLE);

        downArrow.setVisibility(View.INVISIBLE);
        toRegisterButton.setVisibility(View.INVISIBLE);
        toRegisterButton.setEnabled(false);
        toRegisterText.setVisibility(View.INVISIBLE);
    }


    public void toSignInScreen(Context context) {
        doTranslationY(lowerPoly, 0f);
        doTranslationY(upperPoly, 0f);
        doTranslationY(lowerLinearLayout, 0f);
        doTranslationY(upperLinearLayout, 0f);
        doTranslationY(google, 0f);
        doTranslationY(facebook, 0f);
        doTranslationY(forgot, 0f);
        doTranslationY(signInButton, 0f);
        doTranslationY(registerButton, 0f);

        doAlphaTransition(google, true);
        doAlphaTransition(facebook, true);
        doAlphaTransition(toRegisterText, true);
        doAlphaTransition(downArrow, true);
        doAlphaTransition(upArrow, false);
        doAlphaTransition(toRegisterButton, true);
        doAlphaTransition(toSignInButton, false);
        doAlphaTransition(toSignInText, false);
        doAlphaTransition(lowerECell, false);

        doAlphaTransition(upperECell, true);
        doTranslationY(upperECell, 0f);

        doAlphaTransition(lowerECell, false);
        doTranslationY(lowerECell, 300f);

        google.setEnabled(true);
        facebook.setEnabled(true);
        upArrow.setVisibility(View.INVISIBLE);
        toSignInButton.setVisibility(View.INVISIBLE);
        toSignInButton.setEnabled(false);
        toSignInText.setVisibility(View.INVISIBLE);

        toRegisterButton.setEnabled(true);
        toRegisterButton.setVisibility(View.VISIBLE);
        downArrow.setVisibility(View.VISIBLE);
        toRegisterText.setVisibility(View.VISIBLE);
    }

    private void doTranslationY(View view, float value) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, value);
        animator.setDuration(700);
        animator.start();
    }

    private void doAlphaTransition(View view, boolean direction) {
        if(direction) {
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
}