package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.List;

public class BquizOptionsAdapter extends RecyclerView.Adapter<BquizOptionsAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<String> optionsList;
    private BquizOptionsAdapter.onClickListener onClickListener;

    public BquizOptionsAdapter(Context context, List<String> optionsList) {
        this.context = context;
        this.optionsList = optionsList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BquizOptionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.itemview_bquiz_options, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BquizOptionsAdapter.MyViewHolder holder, int position) {
        if (null != optionsList) {
            holder.tvAnswerNumber.setText((position + 1) + ". ");
            holder.tvAnswerText.setText(optionsList.get(position));
            holder.cvAnswer.setOnClickListener(v -> {

                if (onClickListener != null)
                    onClickListener.onClick(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return optionsList == null ? 0 : optionsList.size();
    }

    public void setOnClickListener(BquizOptionsAdapter.onClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    public void setNewList(List<String> newOptions){
        optionsList = newOptions;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvAnswerNumber, tvAnswerText;
        CardView cvAnswer;

        MyViewHolder(View itemView) {
            super(itemView);
            tvAnswerText = itemView.findViewById(R.id.tvOptionText);
            tvAnswerNumber = itemView.findViewById(R.id.tvOptionNumber);
            cvAnswer = itemView.findViewById(R.id.cvBquizOptions);
        }
    }

    public interface onClickListener{
        void onClick(int position);
    }
}
