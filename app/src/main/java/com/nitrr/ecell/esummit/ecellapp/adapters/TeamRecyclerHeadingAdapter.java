package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamList;

import java.util.List;

public class TeamRecyclerHeadingAdapter extends RecyclerView.Adapter<TeamRecyclerHeadingAdapter.MyViewHolder> {

    private Context context;
    private List<TeamList> list;
    private TeamRecyclerViewAdapter adapter;
    private int[] size;

    public TeamRecyclerHeadingAdapter(Context context, List<TeamList> list, int[] size) {
        this.context = context;
        this.list = list;
        this.size = size;
    }

//    android.view.InflateException: Binary XML file line #8: Binary XML file line #8: Error inflating class <unknown>
//    Caused by: android.view.InflateException: Binary XML file line #8: Error inflating class <unknown>
//    Caused by: java.lang.reflect.InvocationTargetException
//at java.lang.reflect.Constructor.newInstance0(Native Method)
//    at java.lang.reflect.Constructor.newInstance(Constructor.java:343)

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.layout_card_team, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TeamList data;
        if(position==0)
            data = list.get(0);
        else
            data = list.get(size[position-1]);
        holder.heading.setText(data.getDomain());

        if (context != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            holder.teamRecycler.setLayoutManager(linearLayoutManager);
            if (position == 0)
                adapter = new TeamRecyclerViewAdapter(context, list.subList(0, size[position]));
            else
                adapter = new TeamRecyclerViewAdapter(context, list.subList(size[position-1], size[position]));

            holder.dropdown.setOnClickListener(v -> {
                if (holder.teamRecycler.getVisibility() == View.GONE) {
                    holder.dropdown.setRotation(90);
                    holder.teamRecycler.setVisibility(View.VISIBLE);
                    holder.teamRecycler.setAdapter(adapter);
                } else if (holder.teamRecycler.getVisibility() == View.VISIBLE) {
                    holder.dropdown.setRotation(270);
                    holder.teamRecycler.setVisibility(View.GONE);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return size.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView heading;
        private ImageButton dropdown;
        private RecyclerView teamRecycler;

        MyViewHolder(@NonNull View view) {
            super(view);
            heading = view.findViewById(R.id.team_member_type);
            dropdown = view.findViewById(R.id.team_member_dropdown);
            teamRecycler = view.findViewById(R.id.team_inner_recycler);
        }
    }
}
