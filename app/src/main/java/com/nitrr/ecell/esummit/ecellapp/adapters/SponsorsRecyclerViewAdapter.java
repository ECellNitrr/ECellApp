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
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.SponsRVData;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SponsorsRecyclerViewAdapter extends RecyclerView.Adapter<SponsorsRecyclerViewAdapter.MyViewHolder>{
    private List<SponsRVData> list;
    private Context context;

    public SponsorsRecyclerViewAdapter(Context context,List<SponsRVData> list) {
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
//        Picasso.Builder builder= new Picasso.Builder(context);
//        builder.downloader(new OkHttpDownloader(context));
//        builder.build().load(data.getImg()).
//                placeholder(R.drawable.ic_placeholder)
//                .error(R.drawable.ic_placeholder)
//        .into(holder.image);
        switch(i%5){
            case 0:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_1);
                break;
            }
            case 1:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_2);
                break;
            }
            case 2:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_3);
                break;
            }
            case 3:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_4);
                break;
            }
            case 4:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_5);
                break;
            }
        }

        holder.name.setText(data.getName());
        holder.category.setText(data.getType());
        //Picasso.with(context).load(data.getImg()).placeholder(R.drawable.ic_hand_shake);
        Glide.with(context).asGif().load(data.getImg()).placeholder(R.drawable.loading1).transform(new CircleCrop()).into(holder.image);
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