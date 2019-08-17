package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.MediaPlayerHelper;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class BottomSheetFragmentBquiz extends BottomSheetDialogFragment {

    private TextView messageTextView;
    private LottieAnimationView bquizAnimationView;
    private static int animation = -1;
    private static String message;

    public static BottomSheetFragmentBquiz newInstance() {
        return new BottomSheetFragmentBquiz();
    }

    public static BottomSheetFragmentBquiz newInstance(int animation, String message) {
        BottomSheetFragmentBquiz.animation = animation;
        BottomSheetFragmentBquiz.message = message;

        return new BottomSheetFragmentBquiz();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_fragment_bquiz, container, false);
        messageTextView = view.findViewById(R.id.bquiz_bottom_sheet_message);
        bquizAnimationView = view.findViewById(R.id.bquiz_animation);

        if (animation != -1) {
            bquizAnimationView.setAnimation(animation);
            bquizAnimationView.setAnimation(animation);
            bquizAnimationView.setRepeatCount(Integer.MAX_VALUE);
            bquizAnimationView.playAnimation();
        }

        if (message != null)
            messageTextView.setText(message);

        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setDimAmount(0.8f);
            getDialog().setCanceledOnTouchOutside(false);
        }

        return view;
    }

    public void setMessage(String message) {
        if (message != null)
            messageTextView.setText(message);
    }

    public void setAnimation(int animation) {
        BottomSheetFragmentBquiz.animation = animation;

        if (bquizAnimationView != null)
            if (bquizAnimationView.isAnimating()) {
                bquizAnimationView.pauseAnimation();
                bquizAnimationView.setAnimation(animation);
                bquizAnimationView.playAnimation();
            }
    }

}