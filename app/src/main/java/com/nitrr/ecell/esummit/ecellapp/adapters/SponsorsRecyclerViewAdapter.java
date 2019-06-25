package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.SponsRVData;

import java.util.ArrayList;

public class SponsorsRecyclerViewAdapter extends RecyclerView.Adapter<SponsorsRecyclerViewAdapter.MyViewHolder>{
    private ArrayList<SponsRVData> list;
    private Context context;

    public SponsorsRecyclerViewAdapter(Context context,ArrayList<SponsRVData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_card_sponsors,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        SponsRVData data = list.get(i);
        holder.name.setText(data.getName());
        holder.category.setText(data.getType());
        //Glide.with(context).load(R.color.black_overlay).into(holder.image);
        holder.card.setBackgroundResource(data.getRes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView name;
        TextView category;
        ImageView image;

        public MyViewHolder(@NonNull View view) {
            super(view);
            card = view.findViewById(R.id.spons_card);
            image = view.findViewById(R.id.spons_img);
            name = view.findViewById(R.id.spons_name);
            category = view.findViewById(R.id.spons_type);
        }
    }
}