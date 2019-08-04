package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.HamburgerItemModel;

import java.util.ArrayList;
import java.util.List;

public class HamburgerRecyclerViewAdapter extends RecyclerView.Adapter<HamburgerRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<HamburgerItemModel> list;

    public HamburgerRecyclerViewAdapter(Context context, List<HamburgerItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_hamburgeritem,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HamburgerItemModel data = list.get(position);
        holder.itemName.setText(data.getName());
        holder.img.setImageResource(data.getImg());
        if(position!=0){
                TypedValue outValue = new TypedValue();
                context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground,outValue,true);
                holder.item.setBackgroundResource(outValue.resourceId);
            }
        holder.item.setOnClickListener(data.getListener());
        if(position==list.size()-1)
            holder.divisionLine.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView itemName;
        private ImageView img, divisionLine;
        private LinearLayout item;

        public MyViewHolder(@NonNull View view) {
            super(view);
            item = view.findViewById(R.id.hamburger_item);
            itemName = view.findViewById(R.id.hamburger_itemtext);
            img = view.findViewById(R.id.hamburger_itemicon);
            divisionLine = view.findViewById(R.id.hamburger_item_division_line);
        }
    }
}
