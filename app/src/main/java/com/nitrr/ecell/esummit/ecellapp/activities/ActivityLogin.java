package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;

public class ActivityLogin extends AppCompatActivity {


    ImageView lowerIcon, downArrow, fbButton, googleButton;
    ImageButton signInButton, registerButton;
    TextView signInText;
    LinearLayout register;
    LoginAnimation loginanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();


        registerButton = findViewById(R.id.to_register_button);
        registerButton.setOnClickListener((View v) -> {
            loginanimation = new LoginAnimation(this);
            register.setEnabled(true);
            loginanimation.toRegisterScreen(this);
        });

        signInButton = findViewById(R.id.to_sign_in_button);
        signInButton.setOnClickListener((View v) -> loginanimation.toSignInScreen(this));

        fbButton = findViewById(R.id.fb_button);
        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });

        googleButton = findViewById(R.id.google_button);
        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    void initialize(){
        downArrow = findViewById(R.id.down_arrow);
        signInText = findViewById(R.id.sign_in_text);
        register = findViewById(R.id.lower_polygon);
        signInButton = findViewById(R.id.to_sign_in_button);
        lowerIcon = findViewById(R.id.ic_lower_ecell);

        register.setEnabled(false);
        signInText.setVisibility(View.GONE);
        signInButton.setVisibility(View.GONE);
        downArrow.setVisibility(View.GONE);
        lowerIcon.setVisibility(View.INVISIBLE);
    }
}
