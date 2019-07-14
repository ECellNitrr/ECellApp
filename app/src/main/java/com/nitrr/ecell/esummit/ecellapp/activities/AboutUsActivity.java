package com.nitrr.ecell.esummit.ecellapp.activities;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.Aim;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.ContactUs;
import com.nitrr.ecell.esummit.ecellapp.fragments.about_us.Team;
import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReciver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AboutUsActivity extends AppCompatActivity{


    private BroadcastReceiver receiver;
    private IntentFilter filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        BottomNavigationView navigation = findViewById(R.id.nav_view);

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


    @Override
    protected void onResume() {
        super.onResume();
        receiver = new NetworkChangeReciver();
        filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        registerReceiver(receiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        if(receiver !=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }
}
