package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.SpeakerDetails;

import java.util.List;


public class SpeakerFragment extends Fragment {

    private static List<SpeakerDetails> speaker;
    private static Integer pos;

    public static SpeakerFragment newInstance(int position, List<SpeakerDetails> speakerDetails) {
        SpeakerFragment fragment = new SpeakerFragment();
        Bundle args = new Bundle();
        pos = position;
        speaker = speakerDetails;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speaker, container, false);
        ImageView imageView = view.findViewById(R.id.speaker_img);
        TextView position = view.findViewById(R.id.speaker_position);
        TextView name = view.findViewById(R.id.speaker_name);

        SpeakerDetails data = speaker.get(pos);
        position.setText(pos+"/"+speaker.size());
        Glide.with(view).load(data.getImg()).into(imageView);
        name.setText(data.getName());
        return  view;
    }
}
