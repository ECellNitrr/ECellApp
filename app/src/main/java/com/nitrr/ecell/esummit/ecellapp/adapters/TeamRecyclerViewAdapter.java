package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamList;

import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

public class TeamRecyclerViewAdapter extends RecyclerView.Adapter<TeamRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<TeamList> list;
    private boolean loaded = false;

    TeamRecyclerViewAdapter(Context context, List<TeamList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.layout_element_team, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        if (i % 2 == 0)
            holder.bg.setBackgroundColor(Color.parseColor("#888888"));
        else
            holder.bg.setBackgroundColor(Color.parseColor("#AAAAAA"));
        TeamList data = list.get(i);
        holder.name.setText(data.getName());
        if(data.getName().equalsIgnoreCase("MNG") || data.getName().equalsIgnoreCase("EXC"))
            holder.img.setVisibility(View.GONE);

        if(data.getImg()!=null){
            CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
            progressDrawable.setStrokeWidth(5f);
            progressDrawable.setCenterRadius(30f);
            progressDrawable.start();
            Glide.with(context).load(data.getImg()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    loaded = true;
                    return false;
                }
            }).placeholder(progressDrawable).transform(new CropSquareTransformation()).into(holder.img);

        holder.bg.setOnClickListener(v -> {
            if(loaded){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_spons_alertdialog, null);
            builder.setView(view);
            builder.setCancelable(true);
            TextView memberName = view.findViewById(R.id._name);
            ImageView memberImg = view.findViewById(R.id._image);
            memberImg.setMaxHeight(view.getWidth());
            memberName.setText(data.getName());
            Glide.with(context).load(data.getImg()).into(memberImg);
            builder.create().show();
            }
        });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        LinearLayout bg;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.membername);
            img = itemView.findViewById(R.id.memberpic);
            bg = itemView.findViewById(R.id.team_member_backround);

        }
    }
}
