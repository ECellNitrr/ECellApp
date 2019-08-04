package com.nitrr.ecell.esummit.ecellapp.activities;

import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.HomeRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs.Aim;
import com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs.ContactUs;
import com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs.Team;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class AboutUsActivity extends BaseActivity {


    private BroadcastReceiver receiver;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        ImageView back = findViewById(R.id.aboutus_back);
        back.setOnClickListener(v -> finish());

        //initial fragment
        loadFragment(new Aim());
        //override color changes when item is clicked
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()) {
                case R.id.navigation_aim:
                    loadFragment(new Aim());
                    return true;

                case R.id.navigation_team:
                    loadFragment(new Team());
                    return true;

                case R.id.navigation_aboutus:
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
        transaction.disallowAddToBackStack();
        transaction.commit();
    }


    @Override
    protected void onDestroy() {
        HomeActivity.setSelected(false);
        super.onDestroy();
    }
}
