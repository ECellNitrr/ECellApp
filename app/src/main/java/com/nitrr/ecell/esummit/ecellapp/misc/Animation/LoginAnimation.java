package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.transition.Fade;
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
    ImageButton google;
    ImageButton facebook;
    ImageButton registerTransitionButtoBackground;
    ImageButton signinTransitionButtonBackground;
    TextView forgot;
    TextView registertext;
    TextView signinText;
    Button signin;
    Button register;


    //Constructor to initilize all the objects that are to be animated
    public LoginAnimation(Login view){
        upperlinearLayout = view.findViewById(R.id.linearLayout);
        lowerLinearLayout = view.findViewById(R.id.linearLayoutLowerPolygon);
        lowerPoly = view.findViewById(R.id.lower_poly);
        upperPoly = view.findViewById(R.id.upper_poly);
        google = view.findViewById(R.id.googleButton);
        facebook = view.findViewById(R.id.fbbutton);
        forgot = view.findViewById(R.id.forgot);
        registertext = view.findViewById(R.id.register_text);
        signin = view.findViewById(R.id.signinbutton);
        registerTransitionButtoBackground = view.findViewById(R.id.imageButton2);
        registerScreenTransitionArrow = view.findViewById(R.id.registerscreen_transitionbutton_arrow);
        register = view.findViewById(R.id.registerbutton);
        signinScreenTransitionArrow = view.findViewById(R.id.signinscreen_transitionbutton_arrow);
        signinTransitionButtonBackground = view.findViewById(R.id.imageButton1);
        signinText = view.findViewById(R.id.signinText);
    }

    public void toregisterscreen(Context context){

        ObjectAnimator lowerPolyAnimator= ObjectAnimator.ofFloat(lowerPoly,"translationY",-1300f);
        ObjectAnimator upperPolyAnimator= ObjectAnimator.ofFloat(upperPoly,"translationY",-1300f);
        ObjectAnimator lowerLinearLayoutAnimator= ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",-1300f);
        ObjectAnimator upperLinearLayoutAnimator= ObjectAnimator.ofFloat(upperlinearLayout,"translationY",-1300f);
        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",-1280f);
        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",-1280f);
        ObjectAnimator forgotfadeout = ObjectAnimator.ofFloat(forgot,"alpha",1f,0);
        ObjectAnimator registerfadeout = ObjectAnimator.ofFloat(registertext,"alpha", 1f,0);
        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signin,"alpha", 1f,0);
        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(registerTransitionButtoBackground, "alpha",1f,0);
        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(registerScreenTransitionArrow, "alpha",1f,0);
        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(register,"translationY",-1300f);
        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(signinTransitionButtonBackground,View.ALPHA,0,1f);
        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(signinScreenTransitionArrow,View.ALPHA,0,1f);
        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signinText,"alpha",0,1f);

        lowerPolyAnimator.setDuration(1000);
        upperPolyAnimator.setDuration(1000);
        lowerLinearLayoutAnimator.setDuration(1000);
        upperLinearLayoutAnimator.setDuration(1000);
        googleAnimator.setDuration(1000);
        facebookAnimator.setDuration(1000);
        forgotfadeout.setDuration(1000);
        registerfadeout.setDuration(1000);
        signinAnimator.setDuration(1000);
        registerTransitionButtonBackground.setDuration(1000);
        registerTransitionButtonArrow.setDuration(1000);
        registerButtonAnimator.setDuration(1000);
        signinTransitionButtonBackgroundAnimator.setDuration(1000);
        signinScreenTransitionArrowAnimator.setDuration(1000);
        signinfadein.setDuration(1000);

        lowerPolyAnimator.start();
        upperPolyAnimator.start();
        lowerLinearLayoutAnimator.start();
        upperLinearLayoutAnimator.start();
        googleAnimator.start();
        facebookAnimator.start();
        forgotfadeout.start();
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
    }

    public void tosigninscreen(Login login) {
        ObjectAnimator lowerPolyAnimator= ObjectAnimator.ofFloat(lowerPoly,"translationY",0f);
        ObjectAnimator upperPolyAnimator= ObjectAnimator.ofFloat(upperPoly,"translationY",0f);
        ObjectAnimator lowerLinearLayoutAnimator= ObjectAnimator.ofFloat(lowerLinearLayout,"translationY",0f);
        ObjectAnimator upperLinearLayoutAnimator= ObjectAnimator.ofFloat(upperlinearLayout,"translationY",0f);
        ObjectAnimator googleAnimator = ObjectAnimator.ofFloat(google,"translationY",0f);
        ObjectAnimator facebookAnimator = ObjectAnimator.ofFloat(facebook,"translationY",0f);
        ObjectAnimator forgotfadeout = ObjectAnimator.ofFloat(forgot,"alpha",0,1);
        ObjectAnimator registerfadein = ObjectAnimator.ofFloat(registertext,"alpha", 0,1);
        ObjectAnimator signinAnimator = ObjectAnimator.ofFloat(signin,"alpha", 0,1);
        ObjectAnimator registerTransitionButtonBackground = ObjectAnimator.ofFloat(registerTransitionButtoBackground, "alpha",0,1);
        ObjectAnimator registerTransitionButtonArrow = ObjectAnimator.ofFloat(registerScreenTransitionArrow, "alpha",0,1);
        ObjectAnimator registerButtonAnimator = ObjectAnimator.ofFloat(register,"translationY",0f);
        ObjectAnimator signinTransitionButtonBackgroundAnimator = ObjectAnimator.ofFloat(signinTransitionButtonBackground,View.ALPHA,1,0);
        ObjectAnimator signinScreenTransitionArrowAnimator = ObjectAnimator.ofFloat(signinScreenTransitionArrow,View.ALPHA,1,0);
        ObjectAnimator signinfadein = ObjectAnimator.ofFloat(signinText,"alpha",1,0);

        lowerPolyAnimator.setDuration(1000);
        upperPolyAnimator.setDuration(1000);
        lowerLinearLayoutAnimator.setDuration(1000);
        upperLinearLayoutAnimator.setDuration(1000);
        googleAnimator.setDuration(1000);
        facebookAnimator.setDuration(1000);
        forgotfadeout.setDuration(1000);
        registerfadein.setDuration(1000);
        signinAnimator.setDuration(1000);
        registerTransitionButtonBackground.setDuration(1000);
        registerTransitionButtonArrow.setDuration(1000);
        registerButtonAnimator.setDuration(1000);
        signinTransitionButtonBackgroundAnimator.setDuration(1000);
        signinScreenTransitionArrowAnimator.setDuration(1000);
        signinfadein.setDuration(1000);

        lowerPolyAnimator.start();
        upperPolyAnimator.start();
        lowerLinearLayoutAnimator.start();
        upperLinearLayoutAnimator.start();
        googleAnimator.start();
        facebookAnimator.start();
        forgotfadeout.start();
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
    }
}
