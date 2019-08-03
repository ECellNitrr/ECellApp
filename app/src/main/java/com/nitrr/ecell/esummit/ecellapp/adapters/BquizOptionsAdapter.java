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
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.QuestionDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class BquizOptionsAdapter extends RecyclerView.Adapter<BquizOptionsAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<QuestionDetailsModel> questionDetailsModels = new ArrayList<>();
   // private SelectAnswerInterface callback;
    private int lastSelected = -1;

    public BquizOptionsAdapter(Context context, List<QuestionDetailsModel> questionDetailsModels) {
        this.context = context;
        this.questionDetailsModels = questionDetailsModels;
        layoutInflater = LayoutInflater.from(context);
      //  this.callback = callback;
    }


    @NonNull
    @Override
    public BquizOptionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.itemview_bquiz_options, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BquizOptionsAdapter.MyViewHolder holder, int position) {
        if(null != questionDetailsModels){
            final QuestionDetailsModel data = questionDetailsModels.get(position);
            if(null != data){
                holder.tvAnswerNumber.setText((position+1) + ".");

                if(null != data.getValue()){
                    holder.tvAnswerText.setText(data.getValue());
                }

                holder.cvAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lastSelected = holder.getAdapterPosition();
                        notifyDataSetChanged();
                    }
                });

                if(position == lastSelected){
                    holder.cvAnswer.setCardBackgroundColor(context.getResources().getColor(R.color.bquizDarkBg));
                    holder.tvAnswerText.setTextColor(context.getResources().getColor(R.color.colorWhite));
                    holder.tvAnswerNumber.setTextColor(context.getResources().getColor(R.color.colorWhite));
                }
                else{
                    holder.cvAnswer.setCardBackgroundColor(context.getResources().getColor(R.color.colorWhite));
                    holder.tvAnswerText.setTextColor(context.getResources().getColor(R.color.colorText));
                    holder.tvAnswerNumber.setTextColor(context.getResources().getColor(R.color.colorText));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return questionDetailsModels.size();
    }

    public class MyViewHolder {

        TextView tvAnswerNumber, tvAnswerText;
        CardView cvAnswer;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAnswerText = itemView.findViewById(R.id.tvOptionText);
            tvAnswerNumber = itemView.findViewById(R.id.tvOptionNumber);
            cvAnswer = itemView.findViewById(R.id.cvBquizOptions);
        }


    }
}
