package com.nitrr.ecell.esummit.ecellapp.activities;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.Goal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.MenuItem;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    private MenuItem goal;
    private MenuItem team;
    private MenuItem aboutus;
    private BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initialize();
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initialize() {
        navView = findViewById(R.id.nav_view);
        goal = findViewById(R.id.navigation_goal);
        team = findViewById(R.id.navigation_team);
        aboutus = findViewById(R.id.navigation_aboutus);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_goal:
                    goal.setIcon(R.drawable.ic_goal_2);
                    goal.setTitle("AIM");
                    team.setIcon(R.drawable.ic_teamwork);
                    team.setTitle("");
                    aboutus.setIcon(R.drawable.ic_email);
                    aboutus.setTitle("");
                    Fragment goalfrag = new Goal();
                    getSupportFragmentManager().beginTransaction().replace(R.id.goal_fragment,goalfrag).addToBackStack(null).commit();
                    return true;
                case R.id.navigation_team:
                    goal.setIcon(R.drawable.ic_goal);
                    goal.setTitle("");
                    team.setIcon(R.drawable.ic_teamwork_2);
                    team.setTitle("TEAM");
                    aboutus.setIcon(R.drawable.ic_email);
                    aboutus.setTitle("");
                    return true;
                case R.id.navigation_aboutus:
                    goal.setIcon(R.drawable.ic_goal);
                    goal.setTitle("");
                    team.setIcon(R.drawable.ic_teamwork);
                    team.setTitle("");
                    aboutus.setIcon(R.drawable.ic_email2);
                    aboutus.setTitle("CONTACT US");
                    return true;
            }
            return false;
        }
    };

}
