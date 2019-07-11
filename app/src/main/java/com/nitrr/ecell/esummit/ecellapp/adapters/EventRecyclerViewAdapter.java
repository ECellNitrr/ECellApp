package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.EventFragment;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventData;

import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder> {
    private List<EventData> list;
    private Context context;
    private View.OnClickListener onItemClickListener;
    private float alpha = 0.2f;


    public EventRecyclerViewAdapter(Context context,List<EventData> list) {
        this.context = context;
        this.list = list;
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
        Glide.with(context).load(data.getImage()).into(myViewHolder.eventimg);
        myViewHolder.event.setText(data.getName());
        myViewHolder.eventbg.setAlpha(alpha);
        if((i+1)%3==0)
            alpha-=0.2f;
        else
            alpha+=0.2f;
        myViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(data.isFlag()){Resource(Integer.parseInt(
                String eventname = data.getName();
                Bundle bundle = new Bundle();
                bundle.putInt("position", i);
                EventFragment fragment = new EventFragment();
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.event_layout, fragment).addToBackStack(null).commit();
//                }
//                else{
//                    Utils.showLongToast(context,"This EventActivity hasn't been approved yet");
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
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
