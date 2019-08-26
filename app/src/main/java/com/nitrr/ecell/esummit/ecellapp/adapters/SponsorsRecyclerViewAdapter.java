package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.HomeActivity;
import com.nitrr.ecell.esummit.ecellapp.misc.CustomHamburgerDialog;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;

import java.util.List;

public class SponsorsRecyclerViewAdapter extends RecyclerView.Adapter<SponsorsRecyclerViewAdapter.MyViewHolder> {
    private List<SponsRVData> list;
    private Context context;
    private int pos;
    private long hamburgerLastClickedTime = 0;

    public SponsorsRecyclerViewAdapter(Context context, List<SponsRVData> list, int i) {
        this.context = context;
        this.list = list;
        pos = i;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_card_sponsors, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        final Boolean[] loader = {false};
        SponsRVData data = list.get(i);
        switch (pos%5) {
            case 0: {
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_1);
                break;
            }
            case 1: {
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_2);
                break;
            }
            case 2: {
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_3);
                break;
            }
            case 3: {
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_4);
                break;
            }
            case 4:{
                holder.card.setBackgroundResource(R.drawable.spons_cardbg_5);
                break;
            }
        }

        holder.name.setText(data.getName());


        if (data.getImg() != null) {
            CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
            progressDrawable.setStrokeWidth(5f);
            progressDrawable.setCenterRadius(70f);
            progressDrawable.start();
            Glide.with(context).load(data.getImg()).placeholder(progressDrawable).apply(new RequestOptions().transform(new RoundedCorners(50))).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    loader[0] = true;
                    return false;
                }
            }).into(holder.image);
        }


        holder.image.setOnClickListener(v -> {
            if (SystemClock.elapsedRealtime() - hamburgerLastClickedTime < 500)
                return;
            hamburgerLastClickedTime = SystemClock.elapsedRealtime();
            if(loader[0]){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_spons_alertdialog, null);
            builder.setView(view);
            builder.setCancelable(true);
            view.setMinimumHeight(view.getWidth());
            TextView sponsName = view.findViewById(R.id._name);
            ImageView sponsImg = view.findViewById(R.id._image);
            if (data.getWebsite() != null && !data.getWebsite().contentEquals("null"))
                sponsImg.setOnClickListener(v1 -> {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(data.getWebsite()));
                    context.startActivity(intent);
                });
            sponsName.setText(data.getName());
            Glide.with(context).load(data.getImg()).into(sponsImg);
            builder.create().show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        TextView name;
        ImageView image;

        public MyViewHolder(@NonNull View view) {
            super(view);
            card = view.findViewById(R.id.spons_card);
            image = view.findViewById(R.id.spons_img);
            name = view.findViewById(R.id.spons_name);
        }
    }
}