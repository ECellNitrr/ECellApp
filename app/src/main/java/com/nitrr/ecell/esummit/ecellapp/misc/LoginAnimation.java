package com.nitrr.ecell.esummit.ecellapp.misc;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.Login;

public class LoginAnimation {

    LinearLayout upperlinearLayout;
    LinearLayout lowerLinearLayout;
    ImageView lowerpoly;

    //Constructor to initilize all the objects that are to be animated
    public LoginAnimation(Login view){
        upperlinearLayout = view.findViewById(R.id.linearLayout);
        lowerLinearLayout = view.findViewById(R.id.linearLayoutLowerPolygon);
        lowerpoly = view.findViewById(R.id.imageView);
    }

    public void doanimation(Context context){
        ObjectAnimator animator= ObjectAnimator.ofFloat(lowerpoly,"translationY",-800f);
        animator.setDuration(1000);
        animator.start();
    }
}
