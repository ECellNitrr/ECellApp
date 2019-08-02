package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.EventFragment;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventData;

import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {
    private List<EventData> list;
    private Context context;
    private List<Float> floats;

    public EventRecyclerViewAdapter(Context context,List<EventData> list,List<Float> floats) {
        this.context = context;
        this.list = list;
        this.floats = floats;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_cardview_event,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        EventData data = list.get(i);
        Glide.with(context).load(data.getImage()).into(myViewHolder.eventImage);
        myViewHolder.event.setText(data.getName());
        myViewHolder.eventbg.setAlpha(floats.get(i));

        myViewHolder.card.setOnClickListener(v -> {

            Log.e("EventRecyclerView","Event Card Index " + i + " is selected");
            if(data.isFlag()){
                Log.e("Flag is true","Event Card Index " + i + " is selected");
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                bundle.putString("event_img", data.getImage());
                bundle.putString("event_name", data.getName());
                bundle.putString("event_venue", data.getVenue());
                bundle.putString("event_data", data.getDate());
                bundle.putString("event_time", data.getTime());
                bundle.putString("event_details", data.getDetails());
                bundle.putString("id", data.getId() + "");
                EventFragment fragment = new EventFragment();
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.event_layout, fragment)
                        .addToBackStack(null)
                        .commit();
            }
            else {
                Utils.showLongToast(context,"This Event hasn't been approved yet");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView event;
        ImageView eventbg;
        CardView card;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            event = itemView.findViewById(R.id.event_name);
            eventbg = itemView.findViewById(R.id.event_bg);
            eventImage = itemView.findViewById(R.id.event_img);
            card = itemView.findViewById(R.id.event_card);
        }
    }
}
