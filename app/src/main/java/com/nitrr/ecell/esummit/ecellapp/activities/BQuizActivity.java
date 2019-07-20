package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.nitrr.ecell.esummit.ecellapp.R;

import io.fabric.sdk.android.Fabric;

public class BQuizActivity extends BaseActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bquiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());

    }
}
