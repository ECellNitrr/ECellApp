package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.graphics.Color;
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
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.ArrayList;
import java.util.List;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeRVData> homeRVDataList = new ArrayList<>();

    public HomeRecyclerViewAdapter(Context context, List<HomeRVData> list) {
        this.context = context;
        this.homeRVDataList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.layout_cardview_home, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final HomeRVData data = homeRVDataList.get(i);
        holder.cardName.setText(data.getName());
        Glide.with(context).load(data.getImage()).into(holder.cardImg);
        String color="#FFFFFF";
        if (i==0)
            color="#48CFAD";
        else if (i==1)
            color="#ED5958";
        else if (i==2)
            color="#18A4E9";
        else if (i==3)
            color = "#F2B531";
        holder.cardBg.setCardBackgroundColor(Color.parseColor(data.getColor()));
    }

    @Override
    public int getItemCount() {
        return homeRVDataList.size();
    }

    //Custom Class
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cardName;
        ImageView cardImg;
        CardView cardBg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.card_name);
            cardImg = itemView.findViewById(R.id.card_image);
            cardBg = itemView.findViewById(R.id.home_cardbg);
        }
    }
}
