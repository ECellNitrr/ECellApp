package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ViewPagerAdapter  extends PagerAdapter {
    private List<SpeakerDetails> speaker = new ArrayList<>();
    private Context context;
    public ViewPagerAdapter(Context context, List<SpeakerDetails> speakerDetailsList) {
        this.context=context;
        speaker = speakerDetailsList;
    }

    @Override
    public int getCount() {
        return speaker.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_speaker,container,false);
        container.addView(view);

        ImageView imageView = view.findViewById(R.id.speaker_img);
        TextView pos = view.findViewById(R.id.speaker_position);
        TextView name = view.findViewById(R.id.speaker_name);
        ConstraintLayout card = view.findViewById(R.id.speaker_bg);

        switch(position%4){
            case 0:{
                card.setBackgroundResource(R.drawable.spons_cardbg_2);
                break;
            }
            case 1:{
                card.setBackgroundResource(R.drawable.spons_cardbg_3);
                break;
            }
            case 2:{
                card.setBackgroundResource(R.drawable.spons_cardbg_4);
                break;
            }
            case 3:{
                card.setBackgroundResource(R.drawable.spons_cardbg_5);
                break;
            }
        }

        SpeakerDetails data = speaker.get(position);
        int speakerpos = position+1;
        pos.setText(speakerpos+"/"+speaker.size());
        Glide.with(view).load(data.getImg()).into(imageView);
        name.setText(data.getName());

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
