package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.BQuizQnAFragment;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizResponseModel;

public class BQuizActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bquiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView bQuizBG = findViewById(R.id.bquiz_bg);
        Glide.with(this)
                .load(R.drawable.bquizbg)
                .apply(new RequestOptions().centerCrop())
                .into(bQuizBG);

        findViewById(R.id.bquiz_proceed).setOnClickListener(v -> {
            BQuizQnAFragment fragment = new BQuizQnAFragment();
            fragment.show(getSupportFragmentManager(), "Bquiz");
        });
    }
}
