package com.nitrr.ecell.esummit.ecellapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class Login extends AppCompatActivity {

    ImageView image;

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
    }

    public void onGClick(View view) {
        // Build a GoogleSignInClient with the options specified by gso.
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    public void ontransiton(View view) {
        LoginAnimation loginanimation = new LoginAnimation(this);
        loginanimation.doanimation(this);
        Utils u=new Utils();
        u.showToast(this,"animation called");
    }

    void initialize(){
        image = findViewById(R.id.lowerpoly_ic_Ecell);
        image.setVisibility(View.INVISIBLE);
    }
}
