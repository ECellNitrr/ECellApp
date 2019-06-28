package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.EventFragment;
import com.nitrr.ecell.esummit.ecellapp.models.EventRVData;

import java.util.ArrayList;
import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {
    private List<EventRVData> list;
    private LayoutInflater inflater;
    private View.OnClickListener onItemClickListener;


    public EventRecyclerViewAdapter(Context context,List<EventRVData> list) {
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.layout_cardview_event,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        EventRVData data = list.get(i);
        myViewHolder.eventimg.setImageResource(data.getImage());
        myViewHolder.event.setText(data.getName());
        myViewHolder.eventbg.setAlpha(data.getAlpha());
        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventname = data.getName();
                Bundle bundle = new Bundle();
                bundle.putString("eventname",eventname);
                EventFragment fragment = new EventFragment();
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.event_layout, fragment).addToBackStack(null).commit();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventimg;
        TextView event;
        ImageView eventbg;
        CardView card;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event_name);
            eventbg = itemView.findViewById(R.id.event_bg);
            eventimg = itemView.findViewById(R.id.event_img);
            card = itemView.findViewById(R.id.event_card);
        }
    }
}
