package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.crashlytics.android.Crashlytics;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.BquizOptionsAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.QuestionDetailsModel;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;

public class BQuizActivity extends BaseActivity {

    private ImageView bquizLogo;
    private TextView tvBquizQuestion,timeAlloted;
    private Button btnSubmit,btnRetry;
    private RecyclerView rvBquizOptions;
    private BquizOptionsAdapter bquizOptionsAdapter;
    private List<QuestionDetailsModel> questionDetailsModels = new ArrayList<>();



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bquiz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initview();
        initAdapter();
        loadQuestion();
    }

    private void initview(){
        bquizLogo =findViewById(R.id.iv_bquiz_logo);
        tvBquizQuestion=findViewById(R.id.tv_bquiz_question);
        btnSubmit=findViewById(R.id.btn_submit);
        btnRetry=findViewById(R.id.btn_retry);
        rvBquizOptions=findViewById(R.id.rv_bquiz_option);
        rvBquizOptions.setLayoutManager(new LinearLayoutManager(this));


    }

    private void initAdapter(){

        bquizOptionsAdapter = new BquizOptionsAdapter(this, questionDetailsModels, this);
        rvBquizOptions.setAdapter(bquizOptionsAdapter);
    }

    private void loadQuestion(){

        /// from socket
        timer(time);

    }

    private void submitAnswer(){

    }

    private void timer(timeAllotedValue){
        new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished){
                timeAlloted.setText(String.valueOf(timeAllotedValue));
                timeAllotedValue--;
            }
            public  void onFinish(){
                timeAlloted.setText("FINISH!!");
            }
        }.start();
    }

}
