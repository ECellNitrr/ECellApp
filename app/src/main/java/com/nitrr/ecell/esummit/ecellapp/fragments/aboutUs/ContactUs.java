package com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;

public class ContactUs extends Fragment implements View.OnClickListener {

    private ImageView linkedin, twitter, facebook, instagram;
    private Button messagebutton;
    private TextView address, site, phonenumber;
    private EditText message;
    SharedPref pref = new SharedPref();

    public ContactUs() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        initialize(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.e("onclick called","contact us page");
        switch (v.getId()){
            case R.id.linkedinlogo:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/company/entrepreneurship-cell-nit-raipur/"));
                startActivity(intent);
                break;
            }
            case R.id.twitterlogo:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/ecell_nitrr"));
                startActivity(intent);
                break;
            }
            case R.id.facebooklogo:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/ecellnitrr/"));
                startActivity(intent);
                break;
            }
            case R.id.instagramlogo:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/ecell.nitraipur/"));
                startActivity(intent);
                break;
            }
            case R.id.messagesubmit:
                sendMessage();
                break;

            case R.id.contactus_address:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo: 21.2479,81.6039"));
                startActivity(intent);
                break;
            }
            case R.id.contactus_website:{
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://ecell.nitrr.ac.in"));
                startActivity(intent);
                break;
            }
            case R.id.contactus_number:{
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel: 09406220952"));
                startActivity(intent);
                break;
            }
        }
    }

    private void sendMessage() {
        String token = pref.getAccessToken(getContext());
        String email = pref.getEmail(getActivity());
        String mess = message.getText().toString();
        message.setText("");
        Utils.showShortToast(getContext(),"Feedback send");

//        Call<> call =  AppClient.getInstance().createService(APIServices.class).methordName;
    }

    private void initialize(View view){
        linkedin = view.findViewById(R.id.linkedinlogo);
        twitter = view.findViewById(R.id.twitterlogo);
        facebook = view.findViewById(R.id.facebooklogo);
        instagram = view.findViewById(R.id.instagramlogo);
        address = view.findViewById(R.id.contactus_address);
        site = view.findViewById(R.id.contactus_website);
        phonenumber = view.findViewById(R.id.contactus_number);
        messagebutton = view.findViewById(R.id.messagesubmit);
        message = view.findViewById(R.id.message);
        messagebutton.setOnClickListener(this);
        linkedin.setOnClickListener(this);
        twitter.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        address.setOnClickListener(this);
        site.setOnClickListener(this);
        phonenumber.setOnClickListener(this);
    }
}
