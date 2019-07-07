package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.HomeRVData;

import java.util.List;

public class HomeRVAdapter extends RecyclerView.Adapter<HomeRVAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeRVData> homeRVDataList;

    public HomeRVAdapter(Context context, List<HomeRVData> list) {
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
        holder.cardBg.setCardBackgroundColor(Color.parseColor(data.getColor()));
        if(data.getListener()!=null)
            holder.cardBg.setOnClickListener(data.getListener());
    }

    @Override
    public int getItemCount() {
        return homeRVDataList.size();
    }

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
