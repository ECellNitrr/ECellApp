package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamList;

import java.util.List;

public class TeamRecyclerViewAdapter extends RecyclerView.Adapter<TeamRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<TeamList> list;

    public TeamRecyclerViewAdapter(Context context, List<TeamList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.layout_element_team,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        TeamList data = list.get(i);
        holder.name.setText(data.getName());
        if(data.getType().equalsIgnoreCase("dir"))
            holder.post.setText(R.string.dir);
        else if(data.getType().equalsIgnoreCase("hcd"))
            holder.post.setText(R.string.hcd);
        else if(data.getType().equalsIgnoreCase("fct"))
            holder.post.setText(R.string.fct);
        else if(data.getType().equalsIgnoreCase("oco"))
            holder.post.setText(R.string.oco);
        else if(data.getType().equalsIgnoreCase("hco"))
            holder.post.setText(R.string.hco);
        else if(data.getType().equalsIgnoreCase("mng"))
            holder.post.setText(R.string.mng);
        else if(data.getType().equalsIgnoreCase("exc"))
            holder.post.setText(R.string.exc);
        else
            holder.post.setText(data.getType());

            Glide.with(context).load(data.getUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView post;
        ImageView img;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.membername);
            post = itemView.findViewById(R.id.memberpost);
            img = itemView.findViewById(R.id.memberpic);

        }
    }
}
