package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.LeaderBoardUserDetail;

import java.util.ArrayList;
import java.util.List;

public class BquizLeaderBoardRecyclerViewAdapter extends RecyclerView.Adapter<BquizLeaderBoardRecyclerViewAdapter.MyViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private List<LeaderBoardUserDetail> leaderBoardUserDetailList = new ArrayList<>();


    public BquizLeaderBoardRecyclerViewAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setLeaderboardUserDetailsList(List<LeaderBoardUserDetail> leaderBoardUserDetailList) {
        this.leaderBoardUserDetailList = leaderBoardUserDetailList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (leaderBoardUserDetailList != null)
            if (leaderBoardUserDetailList.get(position) != null) {

                holder.nameLeaderboard.setText(leaderBoardUserDetailList.get(position).getName() + " ");
                holder.rankLeaderBoard.setText(leaderBoardUserDetailList.get(position).getScore() + " ");
            }
    }

    @Override
    public int getItemCount() {
        return leaderBoardUserDetailList == null ? 0 : leaderBoardUserDetailList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameLeaderboard, rankLeaderBoard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameLeaderboard = itemView.findViewById(R.id.name_leaderboard);
            rankLeaderBoard = itemView.findViewById(R.id.rank_leaderboard);
        }
    }
}
