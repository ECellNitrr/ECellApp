package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.EventRVData;

import java.util.ArrayList;
import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {
    private List<EventRVData> list;
    private LayoutInflater inflater;

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView eventimg;
        TextView event;
        ImageView eventbg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event_name);
            eventbg = itemView.findViewById(R.id.event_bg);
            eventimg = itemView.findViewById(R.id.event_img);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnItemClick(v,getAdapterPosition());
        }
    }

    public void OnItemClick(View v,int pos){
    }
}
