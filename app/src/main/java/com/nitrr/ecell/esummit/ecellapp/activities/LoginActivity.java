package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class LoginActivity extends AppCompatActivity {


    ImageView lowerIcon, upArrow, downArrow, fbButton, googleButton;
    ImageButton signInButton, registerButton;
    Button signin,register;
    TextView signInText;
    LinearLayout registerlayout;
    LoginAnimation loginanimation;
    EditText otp1,otp2,otp3,otp4;
    DialogInterface.OnClickListener confirmlistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };
    DialogInterface.OnClickListener cancellistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        loginanimation = new LoginAnimation(this);

        signin.setOnClickListener((View v) -> {
            Intent intent = new Intent(this,Home.class);
            startActivity(intent);
        });

        register.setOnClickListener((View v) -> {
            showOTPdialog();
            });

        registerButton.setOnClickListener((View v) -> loginanimation.toRegisterScreen(this));

        signInButton.setOnClickListener((View v) -> loginanimation.toSignInScreen(this));

        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });

        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    void initialize(){
        upArrow = findViewById(R.id.up_arrow);
        downArrow = findViewById(R.id.down_arrow);
        signInText = findViewById(R.id.to_sign_in_text);
        registerlayout = findViewById(R.id.lower_linear_layout);
        signInButton = findViewById(R.id.to_sign_in_button);
        registerButton = findViewById(R.id.to_register_button);
        lowerIcon = findViewById(R.id.ic_lower_ecell);
        signin = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);
        registerButton = findViewById(R.id.to_register_button);
        signInButton = findViewById(R.id.to_sign_in_button);
        fbButton = findViewById(R.id.fb_button);
        googleButton = findViewById(R.id.google_button);



        signInText.setVisibility(View.INVISIBLE);
        signInButton.setVisibility(View.INVISIBLE);
        signInButton.setEnabled(false);
        registerButton.setEnabled(true);
        upArrow.setVisibility(View.INVISIBLE);

//        lowerIcon.setVisibility(View.INVISIBLE);
        lowerIcon.animate().translationY(300f).setDuration(10).setInterpolator(new AccelerateInterpolator()).start();
    }

    void showOTPdialog(){
        View v =Utils.showDialog(this,R.layout.layout_otp,false,null,null,"CONFIRM",confirmlistener,"CANCEL",cancellistener);
        otp1 = v.findViewById(R.id.otp1);
        otp2 = v.findViewById(R.id.otp2);
        otp3 = v.findViewById(R.id.otp3);
        otp4 = v.findViewById(R.id.otp4);
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp2.requestFocus();
            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp3.requestFocus();
                else if(s.length()==0)
                    otp1.requestFocus();
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                    otp4.requestFocus();
                else if(s.length()==0)
                    otp2.requestFocus();
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(otp4.length()==0)
                    otp3.requestFocus();
            }
        });
    }
}
