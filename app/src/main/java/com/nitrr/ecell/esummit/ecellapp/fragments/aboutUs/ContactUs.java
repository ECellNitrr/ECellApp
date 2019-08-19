package com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
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
import com.nitrr.ecell.esummit.ecellapp.models.FeedbackModel;
import com.nitrr.ecell.esummit.ecellapp.models.GenericMessage;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactUs extends Fragment implements View.OnClickListener {

    private EditText messageEditText;
    private SharedPref pref = new SharedPref();

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
        Log.e("AboutUs","Contact Us");
        switch (v.getId()){
            case R.id.linkedinlogo: {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/entrepreneurship-cell-nit-raipur/"));
                startActivity(intent);
                break;
            }
            case R.id.twitterlogo: {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com/ecell_nitrr"));
                startActivity(intent);
                break;
            }
            case R.id.facebooklogo: {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/ecellnitrr/"));
                startActivity(intent);
                break;
            }
            case R.id.instagramlogo: {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/ecell.nitraipur/"));
                startActivity(intent);
                break;
            }
            case R.id.messagesubmit:
                if(!TextUtils.isEmpty(messageEditText.getText().toString()))
                    sendMessage();
                else Utils.showLongToast(getContext(), "Message is Empty, Please Type a Feedback Message");
                break;

            case R.id.contactus_address: {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo: 21.2479,81.6039"));
                startActivity(intent);
                break;
            }
            case R.id.contactus_website: {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://ecell.nitrr.ac.in"));
                startActivity(intent);
                break;
            }
            case R.id.contactus_number: {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel: 09406220952"));
                startActivity(intent);
                break;
            }
        }
    }

    private void sendMessage() {
        String name = pref.getFirstName(getContext()) + " " + pref.getLastName(getContext());
        String email = pref.getEmail(getActivity());
        String message = messageEditText.getText().toString();
        AlertDialog dialog = Utils.showProgressBar(getContext(), "Sending Feedback...");

        AppClient.getInstance()
                .createService(APIServices.class)
                .postFeedback(new FeedbackModel(name, email, message))
                .enqueue(new Callback<GenericMessage>() {
            @Override
            public void onResponse(@NonNull Call<GenericMessage> call, @NonNull Response<GenericMessage> response) {
                dialog.dismiss();
                if(getContext() != null) {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            Utils.showLongToast(getContext(), response.body().getMessage());
                            Log.e("ContactUs" , "Response Successful");
                        } else Log.e("ContactUs Feedback", "Response Body Null");
                        messageEditText.setText(null);
                    } else {
                        Utils.showLongToast(getContext(), "Couldn't Send Feedback. Please Try Again after some time.");
                        try {
                            if (response.errorBody() != null) {
                                Log.e("AboutUs, ContactUs", response.errorBody().toString());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<GenericMessage> call, @NonNull Throwable t) {
                dialog.dismiss();
                Utils.showLongToast(getContext(), "Network Connection Error!");
                messageEditText.setText(null);
            }
        });
    }

    private void initialize(View view){
        ImageView linkedin = view.findViewById(R.id.linkedinlogo);
        ImageView twitter = view.findViewById(R.id.twitterlogo);
        ImageView facebook = view.findViewById(R.id.facebooklogo);
        ImageView instagram = view.findViewById(R.id.instagramlogo);
        TextView address = view.findViewById(R.id.contactus_address);
        TextView site = view.findViewById(R.id.contactus_website);
        TextView phoneNumber = view.findViewById(R.id.contactus_number);
        Button messageButton = view.findViewById(R.id.messagesubmit);
        messageEditText = view.findViewById(R.id.message);

        messageButton.setOnClickListener(this);
        linkedin.setOnClickListener(this);
        twitter.setOnClickListener(this);
        facebook.setOnClickListener(this);
        instagram.setOnClickListener(this);
        address.setOnClickListener(this);
        site.setOnClickListener(this);
        phoneNumber.setOnClickListener(this);
    }
}
