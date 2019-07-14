package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends ArrayAdapter {
    private List<SpeakerDetails> speaker = new ArrayList<>();
    private Context context;

    public ViewPagerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

    }
}
