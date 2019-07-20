package com.nitrr.ecell.esummit.ecellapp.fragments.about_us;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;

public class ContactUs extends Fragment implements View.OnClickListener {

    private ImageView whatsapp, linkedin, twitter, facebook, instagram;
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
        switch (v.getId()){
            case R.id.whatsapplogo:{
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(""));
                startActivity(intent);
                break;
            }
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
        }
    }

    private void sendMessage() {
        String token = pref.getAccessToken();
        String email = pref.getEmail();
        String name = pref.getFirstName();

//        Call<> call =  AppClient.getInstance().createService(APIServices.class).methordName;
    }

    void initialize(View view){
        whatsapp = view.findViewById(R.id.whatsapplogo);
        linkedin = view.findViewById(R.id.linkedinlogo);
        twitter = view.findViewById(R.id.twitterlogo);
        facebook = view.findViewById(R.id.facebooklogo);
        instagram = view.findViewById(R.id.instagramlogo);
    }
}
