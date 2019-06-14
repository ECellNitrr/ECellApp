package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.ActivityLogin;

public class LoginAnimation {

    private LinearLayout upperLinearLayout, lowerLinearLayout;
    private ImageView lowerPoly, upperPoly, downArrow, upArrow, lowerECell;
    private ImageButton google, facebook, toRegisterButton, toSignInButton;
    private TextView forgot, registerText, signInText;
    private Button signInButton, registerButton;


    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(ActivityLogin view){
        upperLinearLayout = view.findViewById(R.id.upper_linear_layout);
        lowerLinearLayout = view.findViewById(R.id.lower_polygon);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
        google = view.findViewById(R.id.google_button);
        facebook = view.findViewById(R.id.fb_button);
        forgot = view.findViewById(R.id.forgot);
        registerText = view.findViewById(R.id.register_text);
        signInButton = view.findViewById(R.id.sign_in_button);
        toRegisterButton = view.findViewById(R.id.to_register_button);
        downArrow = view.findViewById(R.id.down_arrow);
        registerButton = view.findViewById(R.id.register_button);
        upArrow = view.findViewById(R.id.up_arrow);
        toSignInButton = view.findViewById(R.id.to_sign_in_button);
        signInText = view.findViewById(R.id.sign_in_text);
        lowerECell = view.findViewById(R.id.ic_lower_ecell);
    }

    public void toRegisterScreen(Context context){
        doTranslationY(lowerPoly, -1300f);
        doTranslationY(upperPoly, -1300);
        doTranslationY(lowerLinearLayout, -1300f);
        doTranslationY(upperLinearLayout, -1400f);
        doTranslationY(google, -1280f);
        doTranslationY(facebook, -1280f);
        doTranslationY(forgot, -1500f);
        doTranslationY(signInButton, -1600f);
        doTranslationY(registerButton, -1300f);

        doAlphaTransition(registerText, false);
        doAlphaTransition(downArrow, false);
        doAlphaTransition(upArrow, true);
        doAlphaTransition(toRegisterButton, false);
        doAlphaTransition(toSignInButton, true);
        doAlphaTransition(signInText, true);
        doAlphaTransition(lowerECell, true);

        AnimatorSet eCellIconAnimations = new AnimatorSet();
        eCellIconAnimations.playTogether(ObjectAnimator.ofFloat(lowerECell,View.ALPHA,0,1f),ObjectAnimator.ofFloat(lowerECell,"translationY",-1300f));
        eCellIconAnimations.setDuration(700);

        upArrow.setVisibility(View.VISIBLE);
        toSignInButton.setVisibility(View.VISIBLE);
        signInText.setVisibility(View.VISIBLE);
        lowerECell.setVisibility(View.VISIBLE);
        eCellIconAnimations.start();
    }

    public void toSignInScreen(ActivityLogin activityLogin) {
        doTranslationY(lowerPoly, 0f);
        doTranslationY(upperPoly, 0f);
        doTranslationY(lowerLinearLayout, 0f);
        doTranslationY(upperLinearLayout, 0f);
        doTranslationY(google, 0f);
        doTranslationY(facebook, 0f);
        doTranslationY(forgot, 0f);
        doTranslationY(signInButton, 0f);
        doTranslationY(registerButton, 0f);

        doAlphaTransition(registerText, true);
        doAlphaTransition(downArrow, true);
        doAlphaTransition(upArrow, false);
        doAlphaTransition(toRegisterButton, true);
        doAlphaTransition(toSignInButton, false);
        doAlphaTransition(signInText, false);
        doAlphaTransition(lowerECell, false);

        AnimatorSet eCellIconAnimations = new AnimatorSet();
        eCellIconAnimations.playTogether(
                ObjectAnimator.ofFloat(lowerECell,View.ALPHA,1,0),
                ObjectAnimator.ofFloat(lowerECell,"translationY",0));

        eCellIconAnimations.setDuration(700);

        upArrow.setVisibility(View.VISIBLE);
        toSignInButton.setVisibility(View.VISIBLE);
        signInText.setVisibility(View.VISIBLE);
        eCellIconAnimations.start();
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
        }

        else {
            ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0);
            animator.setDuration(700);
            animator.start();
        }

    }
}

//        ObjectAnimator lowerPolyAnimator = ObjectAnimator.ofFloat(lowerPoly,"translationY",-1300f);
//        ObjectAnimator upperPolyAnimator = ObjectAnimator.ofFloat(upperPoly,"translationY",-1300f);
//        ObjectAnimator lowerLinearLayoutAnimator = ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",-1300f);
//        ObjectAnimator upperLinearLayoutAnimator = ObjectAnimator.ofFloat(upperLinearLayout,"translationY",-1400f);
//        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",-1280f);
//        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",-1280f);
//        ObjectAnimator forgotAnimator = ObjectAnimator.ofFloat(forgot,"translationY",-1500);
//        ObjectAnimator registerfadeout = ObjectAnimator.ofFloat(registerText,"alpha", 1f,0);
//        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signInButton,"translationY", -1600);
//        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(toRegisterButton, "alpha",1f,0);
//        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(downArrow, "alpha",1f,0);
//        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(registerButton,"translationY",-1300f);
//        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(toSignInButton,View.ALPHA,0,1f);
//        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(upArrow,View.ALPHA,0,1f);
//        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signInText,"alpha",0,1f);

//        ObjectAnimator lowerPolyAnimator= ObjectAnimator.ofFloat(lowerPoly,"translationY",0f);
//        ObjectAnimator upperPolyAnimator= ObjectAnimator.ofFloat(upperPoly,"translationY",0f);
//        ObjectAnimator lowerLinearLayoutAnimator= ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",0f);
//        ObjectAnimator upperLinearLayoutAnimator= ObjectAnimator.ofFloat(upperLinearLayout,"translationY",0f);
//        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",0f);
//        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",0f);
//        ObjectAnimator forgotAnimator = ObjectAnimator.ofFloat(forgot,"translationY",0);
//        ObjectAnimator registerfadein = ObjectAnimator.ofFloat(registerText,"alpha", 0,1);
//        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signInButton,"translationY", 0);
//        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(toRegisterButton, "alpha",0,1);
//        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(downArrow, "alpha",0,1);
//        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(registerButton,"translationY",0f);
//        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(toSignInButton,View.ALPHA,1,0);
//        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(upArrow,View.ALPHA,1,0);
//        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signInText,"alpha",1,0);
