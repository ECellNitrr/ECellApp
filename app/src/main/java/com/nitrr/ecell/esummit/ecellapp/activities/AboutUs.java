package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.Aim;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.ContactUs;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.Team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    TextView toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.about_us_toolbar_text);

//        toolbar.setText(R.string.aim_text);
        //initial fragment
        loadFragment(new Aim());
        //override color changes when item is clicked
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.navigation_aim:
//                    toolbar.setText(R.string.aim_text);
                    loadFragment(new Aim());
                    return true;

                case R.id.navigation_team:
//                    toolbar.setText(R.string.team);
                    loadFragment(new Team());
                    return true;

                case R.id.navigation_aboutus:
//                    toolbar.setText(R.string.contact_us);
                    loadFragment(new ContactUs());
                    return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
