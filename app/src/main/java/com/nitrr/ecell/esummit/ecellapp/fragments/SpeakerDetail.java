package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.ArrayList;

public class SpeakerDetail extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_speaker_detail);
        ArrayList<String> arrayList = getIntent().getStringArrayListExtra("data");
        TextView name = findViewById(R.id.detail_speaker_name);
        ImageView image = findViewById(R.id.detail_speaker_image);
        TextView year = findViewById(R.id.detail_speaker_year);
        TextView company = findViewById(R.id.speaker_company);
        TextView email = findViewById(R.id.speaker_email);
        TextView contact = findViewById(R.id.speaker_contact);
        TextView socialMedia = findViewById(R.id.speaker_social_media);
        company.setText(arrayList.get(0));
        email.setText(arrayList.get(1));
        contact.setText(arrayList.get(2));
        socialMedia.setText(arrayList.get(3));
        name.setText(arrayList.get(4));
        Glide.with(this).load(arrayList.get(5)).apply(RequestOptions.circleCropTransform()).into(image);
        year.setText(getIntent().getIntExtra("year", 2019));
    }
}
