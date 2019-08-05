package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.button.MaterialButton;
import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.ArrayList;
import java.util.Objects;

import com.nitrr.ecell.esummit.ecellapp.misc.NetworkChangeReceiver;

public class SpeakerFragment extends Fragment {

    private ImageView image;
    private TextView name;
    private TextView company;
    TextView description;
    private MaterialButton socialMedia;
    private BroadcastReceiver receiver;

    public SpeakerFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker_detail, container, false);
        ArrayList<String> data = this.getArguments() != null ? this.getArguments().getStringArrayList("data") : null;
        if (data != null) {
            initialize(view);
            setData(data.get(0), data.get(1), data.get(2),
                    data.get(3), data.get(4), data.get(5));
        }
        view.findViewById(R.id.speaker_detail_back).setOnClickListener(view1 -> Objects.requireNonNull(getActivity()).onBackPressed());
        return view;
    }

    private void initialize(View v) {
        name = v.findViewById(R.id.detail_speaker_name);
        image = v.findViewById(R.id.detail_speaker_image);
        company = v.findViewById(R.id.speaker_company);
        socialMedia = v.findViewById(R.id.speaker_social_media);
        socialMedia.getBackground().setColorFilter(this.getResources()
                .getColor(R.color.linkedin_background), PorterDuff.Mode.MULTIPLY);
        description = v.findViewById(R.id.speaker_description);
    }

    private void setData(String image, String name, String company, String email, String socialMedia, String description) {
        try {
            if (image != null) {
                Glide.with(Objects.requireNonNull(getContext()))
                        .load(image)
                        .apply(RequestOptions.circleCropTransform())
                        .into(this.image);
            }
        } catch (Exception e) {
            setData(image, name, company, email, socialMedia, description);
        }
        this.name.setText(name);
        this.company.setText(company);
        this.socialMedia.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia))));
        this.description.setText(description);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGED");
        Objects.requireNonNull(getContext()).registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }


    @Override
    public void onDestroy() {
        if (receiver != null) {
            Objects.requireNonNull(getContext()).unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }
}
