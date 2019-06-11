package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class Login extends AppCompatActivity {

    ImageView image;
    ImageView signinScreenTransitionArrow;
    ImageView lowerpoly_ic_Ecell;
    ImageButton signinTransitionButtonBackground;
    TextView signinText;
    LinearLayout registeredittexts;
    LoginAnimation loginanimation;

    // Configure sign-in to request the user's ID, email address, and basic
    // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();
    EditText user;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    public void onGClick(View view) {
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void ontransiton(View view) {
        loginanimation = new LoginAnimation(this);
        registeredittexts.setEnabled(true);
        loginanimation.toregisterscreen(this);
    }

    void initialize(){
        signinScreenTransitionArrow = findViewById(R.id.signinscreen_transitionbutton_arrow);
        signinTransitionButtonBackground = findViewById(R.id.imageButton1);
        signinText = findViewById(R.id.signinText);
        registeredittexts = findViewById(R.id.linearLayoutLowerPolygon);
        lowerpoly_ic_Ecell = findViewById(R.id.ic_lower_ecell);

        registeredittexts.setEnabled(false);
        signinText.setVisibility(View.GONE);
        signinTransitionButtonBackground.setVisibility(View.GONE);
        signinScreenTransitionArrow.setVisibility(View.GONE);
        lowerpoly_ic_Ecell.setVisibility(View.INVISIBLE);
    }

    public void onsigninintransition(View view) {
        loginanimation.tosigninscreen(this);
    }
}
