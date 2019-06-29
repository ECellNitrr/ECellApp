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

        } else if (v <= 0) {
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);

        } else if (v <= 1) {
            // Fade the page out.
            view.setAlpha(1 - v);

            view.setTranslationX(pageWidth * -v);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(v));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else {
            // This page is way off-screen to the right.
            view.setAlpha(0f);
        }
    }
}
