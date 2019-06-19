package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.nitrr.ecell.esummit.ecellapp.R;

public class ESummitActivity extends AppCompatActivity {

    ScrollView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esummit);

        view = findViewById(R.id.view);
        initialize();
    }

    public void initialize() {

    }

}
