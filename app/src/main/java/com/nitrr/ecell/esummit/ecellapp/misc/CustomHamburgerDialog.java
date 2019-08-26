package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.AboutUsActivity;
import com.nitrr.ecell.esummit.ecellapp.activities.LoginActivity;
import com.nitrr.ecell.esummit.ecellapp.adapters.HamburgerRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.fragments.ChangeNumberFragment;
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;
import com.nitrr.ecell.esummit.ecellapp.models.HamburgerItemModel;

import java.util.ArrayList;
import java.util.List;

public class CustomHamburgerDialog {

    private AlertDialog alertDialog;
    private SharedPref pref = new SharedPref();
    private AppCompatActivity activity;
    private List<HamburgerItemModel> list = new ArrayList<>();
    private AlertDialog.Builder builder;
    private RecyclerView recycler;


    public CustomHamburgerDialog() {
    }

    public CustomHamburgerDialog with(AppCompatActivity activity) {
        this.activity = activity;
        return this;
    }

    public void build() {

        builder = new AlertDialog.Builder(activity);

        View alertView = activity.getLayoutInflater().inflate(R.layout.bottom_hamburger, null);

        recycler = alertView.findViewById(R.id.hamburger_recycler);
        initializeList();
        setRecycler();

        builder.setView(alertView);
        alertDialog = builder.create();

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            WindowManager.LayoutParams params = alertDialog.getWindow().getAttributes();
            params.gravity = Gravity.TOP;
            params.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.verticalMargin = 0.05f;

            alertDialog.getWindow().setAttributes(params);
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.MenuDialogAnimation;
        }

        alertDialog.show();
    }

    private void initializeList(){

        String name;
        SharedPref pref = new SharedPref();
        if (pref.getFirstName(activity).equals("")) {
            name = pref.getEmail(activity).split("@")[0];
        } else
            name = pref.getFirstName(activity);

        addItem(name,R.drawable.ic_username,null);

        addItem(activity.getString(R.string.change_number),R.drawable.ic_call, v1 -> {
            alertDialog.dismiss();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.home_parent_layout, new ChangeNumberFragment())
                    .addToBackStack(null)
                    .commit();});

        addItem(activity.getString(R.string.about_us),R.drawable.about_us_team, v1 -> {
            alertDialog.dismiss();
            Intent intent = new Intent(activity, AboutUsActivity.class);
            activity.startActivity(intent);});

        addItem(activity.getString(R.string.log_out),R.drawable.ic_log_out, v1 -> {
            alertDialog.dismiss();
            pref.clearPrefs(activity);
            Utils.showLongToast(activity, "Logged Out Successfully!");
            activity.finish();
            Intent i = new Intent(activity, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(i);});
    }

    private void addItem(String name, int img, View.OnClickListener listener){
        list.add(new HamburgerItemModel(name, img ,listener));
    }


    private void setRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(new HamburgerRecyclerViewAdapter(activity.getApplicationContext(),list));
    }
//
//    private void showOTPDialog() {
//        OTPDialogFragment fragment = new OTPDialogFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("prevfrag", "Home Activity");
//        fragment.setArguments(bundle);
//
//        if (activity != null) {
//            activity.getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.home_parent_layout, fragment)
//                    .addToBackStack(null)
//                    .commit();
//        }
//        alertDialog.dismiss();
//    }
}
