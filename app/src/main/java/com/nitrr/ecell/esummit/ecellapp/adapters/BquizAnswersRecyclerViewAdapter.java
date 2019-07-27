package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.ArrayList;

public class BquizAnswersRecyclerViewAdapter extends RecyclerView.Adapter<BquizAnswersRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> answers;

    public BquizAnswersRecyclerViewAdapter(Context context, ArrayList<String> answers) {
        this.context = context;
        this.answers = answers;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_bquiz_answer_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (answers.get(position) != null)
            holder.answerText.setText(answers.get(position));
    }

    @Override
    public int getItemCount() {
        return answers == null ? 0 : answers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView answerText;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            answerText = itemView.findViewById(R.id.recycler_bquiz_answer_layout_text);
        }
    }
}
