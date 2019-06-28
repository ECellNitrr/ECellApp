package com.nitrr.ecell.esummit.ecellapp.misc.Animation;

import android.animation.ObjectAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.ESummitActivity;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class ESummitAnimaiton {
    private ConstraintLayout esInner;
    private ImageView upperPoly, lowerPoly, esTriangle;
    private ViewPager viewPager;
    private Guideline uGuidline, lGuidline;
    private TextView toSpeaker, toAboutES, aboutES;
    private float distOfScroll;

    public ESummitAnimaiton(ESummitActivity view) {
        esInner = view.findViewById(R.id.es_inner_constraint);
        int height = esInner.getHeight();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        view.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.distOfScroll = -displayMetrics.heightPixels * 0.47f;
        Utils.showToast(view, height + "");
        upperPoly = view.findViewById(R.id.es_upper_poly);
        lowerPoly = view.findViewById(R.id.es_lower_poly);
        viewPager = view.findViewById(R.id.pager);
        uGuidline = view.findViewById(R.id.guide_upoly);
        lGuidline = view.findViewById(R.id.guide_lpoly);
        toSpeaker = view.findViewById(R.id.to_speaker);
        toAboutES = view.findViewById(R.id.to_about_es);
        aboutES = view.findViewById(R.id.es_about);
        esTriangle = view.findViewById(R.id.es_triangle);
    }

    public void toSpeakers() {
        doTranslationY(upperPoly, true);
        doTranslationY(aboutES, true);
        doAlphaTransition(aboutES, false);
        doTranslationY(lowerPoly, true);

        doTranslationY(viewPager, true);
        doAlphaTransition(viewPager, true);

        doTranslationY(toAboutES, true);
        doAlphaTransition(toAboutES, true);
        toAboutES.setClickable(true);
        toAboutES.setVisibility(View.VISIBLE);

        doTranslationY(toSpeaker, true);
        doAlphaTransition(toSpeaker, false);
        toSpeaker.setClickable(false);
    }

    public void toAboutES() {
        doTranslationY(upperPoly, false);
        doTranslationY(aboutES, false);
        doAlphaTransition(aboutES, true);
        doTranslationY(lowerPoly, false);

        doTranslationY(viewPager, false);
        doAlphaTransition(viewPager, false);

        doTranslationY(toAboutES, false);
        doAlphaTransition(toAboutES, false);
        toAboutES.setClickable(false);

        doTranslationY(toSpeaker, false);
        doAlphaTransition(toSpeaker, true);
        toSpeaker.setClickable(true);
    }

    private void doTranslationY(View view, boolean up) {
        float dist = up ? distOfScroll : 0f;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, dist);
        animator.setDuration(800);
        animator.start();
    }

    private void doAlphaTransition(View view, boolean fadeIn) {
        ObjectAnimator animator;
        animator = fadeIn ? ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f) : ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0);
        animator.setDuration(800);
        animator.start();
    }

    private void doAlphaTransition(View view, boolean fadeIn, int delayStart) {
        ObjectAnimator animator;
        animator = fadeIn ? ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f) : ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0);
        animator.setDuration(800);
        animator.setStartDelay(delayStart);
        animator.start();
    }
}
