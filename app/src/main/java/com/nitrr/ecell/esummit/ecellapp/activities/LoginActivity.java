package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;

public class LoginActivity extends AppCompatActivity {


    ImageView lowerIcon, upArrow, downArrow, fbButton, googleButton;
    ImageButton signInButton, registerButton;
    TextView signInText;
    LinearLayout register;
    LoginAnimation loginanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        loginanimation = new LoginAnimation(this);


        registerButton = findViewById(R.id.to_register_button);
        registerButton.setOnClickListener((View v) -> loginanimation.toRegisterScreen(this));

        signInButton = findViewById(R.id.to_sign_in_button);
        signInButton.setOnClickListener((View v) -> loginanimation.toSignInScreen(this));

        fbButton = findViewById(R.id.fb_button);
        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });

        googleButton = findViewById(R.id.google_button);
        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    void initialize(){
        upArrow = findViewById(R.id.up_arrow);
        downArrow = findViewById(R.id.down_arrow);
        signInText = findViewById(R.id.to_sign_in_text);
        register = findViewById(R.id.lower_linear_layout);
        signInButton = findViewById(R.id.to_sign_in_button);
        registerButton = findViewById(R.id.to_register_button);
        lowerIcon = findViewById(R.id.ic_lower_ecell);

        signInText.setVisibility(View.INVISIBLE);
        signInButton.setVisibility(View.INVISIBLE);
        signInButton.setEnabled(false);
        registerButton.setEnabled(true);
        upArrow.setVisibility(View.INVISIBLE);

//        lowerIcon.setVisibility(View.INVISIBLE);
        lowerIcon.animate().translationY(300f).setDuration(10).setInterpolator(new AccelerateInterpolator()).start();
    }
}
