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
            data = list.get(size[position]-1);
        if(data.getDomain().contentEquals("none"))
            if(data.getType().contentEquals("OCO"))
                holder.heading.setText("Overall Coordinator");
            else
                holder.heading.setText("Faculty");
        else if(data.getDomain().contentEquals("tech"))
            holder.heading.setText("Technology");
        else if(data.getDomain().contentEquals("design"))
            holder.heading.setText("Design");
        else if(data.getDomain().contentEquals("spons"))
            holder.heading.setText("Sponsors");
        else if(data.getDomain().contentEquals("pr"))
            holder.heading.setText("PR and Marketing");
        else if(data.getDomain().contentEquals("doc"))
            holder.heading.setText("Documentation");

        if (context != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            holder.teamRecycler.setLayoutManager(linearLayoutManager);


            holder.dropdown.setOnClickListener(v -> {

                if (holder.teamRecycler.getVisibility() == View.GONE) {
                    holder.dropdown.setRotation(90);
                    holder.teamRecycler.setVisibility(View.VISIBLE);
                    if (position == 0)
                        adapter = new TeamRecyclerViewAdapter(context, list.subList(0, size[position]));
                    else
                        adapter = new TeamRecyclerViewAdapter(context, list.subList(size[position-1], size[position]));
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
