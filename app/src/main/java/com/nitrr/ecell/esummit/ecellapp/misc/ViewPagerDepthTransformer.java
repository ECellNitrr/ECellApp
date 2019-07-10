package com.nitrr.ecell.esummit.ecellapp.misc;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import android.view.View;

public class ViewPagerDepthTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(@NonNull View view, float v) {
        int pageWidth = view.getWidth();

        if (v < -1) {
            // This page is way off-screen to the left.
            view.setAlpha(0f);
        }
        else if (v <= 0) {
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setScaleX(0.9f);
            view.setScaleY(0.9f);
        }
        else if (v <= 1) {
            // Fade the page out.
            view.setAlpha(0.3f);
            if((1-v)>0.3)
                view.setAlpha(1 - v);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(v));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        }
        else if(v<=2){
            // This page is way off-screen to the right.
            view.setAlpha(0.3f);
        }
        if(v>0) {
            view.setTranslationX(pageWidth * -v);
            view.setScaleX(0.9f-0.05f*v);
            view.setScaleY(0.9f);
            view.setTranslationY(20 * v);
        }
    }
}
