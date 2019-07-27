package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.OTPDialogFragment;

import java.util.List;

public class OTPAdapter extends RecyclerView.Adapter<OTPAdapter.MyViewHolder>{

    private Context context;
    private OTPDialogFragment fragment;
    private List<String> list;

    public OTPAdapter(Context context, List<String> list,OTPDialogFragment fragment) {
        this.fragment = fragment;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public OTPAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_otpdialog,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String text = list.get(position);
        holder.materialButton.setText(text);
        holder.materialButton.setBackgroundColor(context.getResources().getColor(R.color.transparent));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MaterialButton materialButton;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            materialButton = itemView.findViewById(R.id.dialpad_item);
            materialButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            MaterialButton button = v.findViewById(v.getId());
            String text = button.getText().toString();
            int n;
            if(text.equalsIgnoreCase("back"))
                n=-1;
            else if (text.equalsIgnoreCase("confirm"))
                n=-2;
            else
                n=Integer.parseInt(text);
            fragment.update(n);
        }
    }
}
