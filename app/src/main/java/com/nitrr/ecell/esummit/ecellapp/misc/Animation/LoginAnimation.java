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
import com.nitrr.ecell.esummit.ecellapp.activities.Login;

public class LoginAnimation {

    LinearLayout upperlinearLayout;
    LinearLayout lowerLinearLayout;
    ImageView lowerPoly;
    ImageView upperPoly;
    ImageView  registerScreenTransitionArrow;
    ImageView signinScreenTransitionArrow;
    ImageView lowerpolyEcell;
    ImageButton google;
    ImageButton facebook;
    ImageButton registerTransitionButtoBackground;
    ImageButton signinTransitionButtonBackground;
    TextView forgot;
    TextView registertext;
    TextView signinText;
    Button signin;
    Button register;


    //Constructor to initialize all the objects that are to be animated
    public LoginAnimation(Login view){
        upperlinearLayout = view.findViewById(R.id.linearLayout);
        lowerLinearLayout = view.findViewById(R.id.linearLayoutLowerPolygon);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
        google = view.findViewById(R.id.googleButton);
        facebook = view.findViewById(R.id.fbButton);
        forgot = view.findViewById(R.id.forgot);
        registertext = view.findViewById(R.id.register_text);
        signin = view.findViewById(R.id.signinbutton);
        registerTransitionButtoBackground = view.findViewById(R.id.imageButton2);
        registerScreenTransitionArrow = view.findViewById(R.id.registerscreen_transitionbutton_arrow);
        register = view.findViewById(R.id.registerbutton);
        signinScreenTransitionArrow = view.findViewById(R.id.signinscreen_transitionbutton_arrow);
        signinTransitionButtonBackground = view.findViewById(R.id.imageButton1);
        signinText = view.findViewById(R.id.signinText);
        lowerpolyEcell = view.findViewById(R.id.ic_lower_ecell);
    }

    public void toregisterscreen(Context context){

        ObjectAnimator lowerPolyAnimator= ObjectAnimator.ofFloat(lowerPoly,"translationY",-1300f);
        ObjectAnimator upperPolyAnimator= ObjectAnimator.ofFloat(upperPoly,"translationY",-1300f);
        ObjectAnimator lowerLinearLayoutAnimator= ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",-1300f);
        ObjectAnimator upperLinearLayoutAnimator= ObjectAnimator.ofFloat(upperlinearLayout,"translationY",-1400f);
        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",-1280f);
        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",-1280f);
        ObjectAnimator forgotAnimator = ObjectAnimator.ofFloat(forgot,"translationY",-1500);
        ObjectAnimator registerfadeout = ObjectAnimator.ofFloat(registertext,"alpha", 1f,0);
        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signin,"translationY", -1600);
        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(registerTransitionButtoBackground, "alpha",1f,0);
        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(registerScreenTransitionArrow, "alpha",1f,0);
        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(register,"translationY",-1300f);
        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(signinTransitionButtonBackground,View.ALPHA,0,1f);
        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(signinScreenTransitionArrow,View.ALPHA,0,1f);
        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signinText,"alpha",0,1f);
        ObjectAnimator Ecelliconfade = ObjectAnimator.ofFloat(lowerpolyEcell,View.ALPHA,0,1f);
        ObjectAnimator EcelliconAnimator = ObjectAnimator.ofFloat(lowerpolyEcell,"translationY",-1300f);
        AnimatorSet ecellIconAnimations = new AnimatorSet();
        ecellIconAnimations.playTogether(Ecelliconfade,EcelliconAnimator);

        lowerPolyAnimator.setDuration(700);
        upperPolyAnimator.setDuration(700);
        lowerLinearLayoutAnimator.setDuration(700);
        upperLinearLayoutAnimator.setDuration(700);
        googleAnimator.setDuration(700);
        facebookAnimator.setDuration(700);
        forgotAnimator.setDuration(700);
        registerfadeout.setDuration(700);
        signinAnimator.setDuration(700);
        registerTransitionButtonBackground.setDuration(700);
        registerTransitionButtonArrow.setDuration(700);
        registerButtonAnimator.setDuration(700);
        signinTransitionButtonBackgroundAnimator.setDuration(700);
        signinScreenTransitionArrowAnimator.setDuration(700);
        signinfadein.setDuration(700);
        ecellIconAnimations.setDuration(700);

        lowerPolyAnimator.start();
        upperPolyAnimator.start();
        lowerLinearLayoutAnimator.start();
        upperLinearLayoutAnimator.start();
        googleAnimator.start();
        facebookAnimator.start();
        forgotAnimator.start();
        registerfadeout.start();
        signinAnimator.start();
        registerTransitionButtonBackground.start();
        registerTransitionButtonArrow.start();
        registerButtonAnimator.start();
        signinScreenTransitionArrow.setVisibility(View.VISIBLE);
        signinTransitionButtonBackgroundAnimator.start();
        signinTransitionButtonBackground.setVisibility(View.VISIBLE);
        signinScreenTransitionArrowAnimator.start();
        signinText.setVisibility(View.VISIBLE);
        signinfadein.start();
        lowerpolyEcell.setVisibility(View.VISIBLE);
        ecellIconAnimations.start();
    }

    public void tosigninscreen(Login login) {
        ObjectAnimator lowerPolyAnimator= ObjectAnimator.ofFloat(lowerPoly,"translationY",0f);
        ObjectAnimator upperPolyAnimator= ObjectAnimator.ofFloat(upperPoly,"translationY",0f);
        ObjectAnimator lowerLinearLayoutAnimator= ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",0f);
        ObjectAnimator upperLinearLayoutAnimator= ObjectAnimator.ofFloat(upperlinearLayout,"translationY",0f);
        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",0f);
        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",0f);
        ObjectAnimator forgotAnimator = ObjectAnimator.ofFloat(forgot,"translationY",0);
        ObjectAnimator registerfadein = ObjectAnimator.ofFloat(registertext,"alpha", 0,1);
        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signin,"translationY", 0);
        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(registerTransitionButtoBackground, "alpha",0,1);
        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(registerScreenTransitionArrow, "alpha",0,1);
        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(register,"translationY",0f);
        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(signinTransitionButtonBackground,View.ALPHA,1,0);
        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(signinScreenTransitionArrow,View.ALPHA,1,0);
        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signinText,"alpha",1,0);
        AnimatorSet ecellIconAnimations = new AnimatorSet();
        ecellIconAnimations.playTogether(
                ObjectAnimator.ofFloat(lowerpolyEcell,View.ALPHA,1,0),
                ObjectAnimator.ofFloat(lowerpolyEcell,"translationY",0));

        lowerPolyAnimator.setDuration(700);
        upperPolyAnimator.setDuration(700);
        lowerLinearLayoutAnimator.setDuration(700);
        upperLinearLayoutAnimator.setDuration(700);
        googleAnimator.setDuration(700);
        facebookAnimator.setDuration(700);
        forgotAnimator.setDuration(700);
        registerfadein.setDuration(700);
        signinAnimator.setDuration(700);
        registerTransitionButtonBackground.setDuration(700);
        registerTransitionButtonArrow.setDuration(700);
        registerButtonAnimator.setDuration(700);
        signinTransitionButtonBackgroundAnimator.setDuration(700);
        signinScreenTransitionArrowAnimator.setDuration(700);
        signinfadein.setDuration(700);
        ecellIconAnimations.setDuration(700);

        lowerPolyAnimator.start();
        upperPolyAnimator.start();
        lowerLinearLayoutAnimator.start();
        upperLinearLayoutAnimator.start();
        googleAnimator.start();
        facebookAnimator.start();
        forgotAnimator.start();
        registerfadein.start();
        signinAnimator.start();
        registerTransitionButtonBackground.start();
        registerTransitionButtonArrow.start();
        registerButtonAnimator.start();
        signinScreenTransitionArrow.setVisibility(View.VISIBLE);
        signinTransitionButtonBackgroundAnimator.start();
        signinTransitionButtonBackground.setVisibility(View.VISIBLE);
        signinScreenTransitionArrowAnimator.start();
        signinText.setVisibility(View.VISIBLE);
        signinfadein.start();
        ecellIconAnimations.start();
    }
}
