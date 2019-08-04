package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerFragment;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeakerData;

import java.util.ArrayList;
import java.util.List;

public class ESummitRecyclerViewAdapter extends RecyclerView.Adapter<ESummitRecyclerViewAdapter.MyViewHolder> {
    private List<ResponseSpeakerData> speakerDataList;
    private Context context;
    private LayoutInflater inflater;

    public ESummitRecyclerViewAdapter(List<ResponseSpeakerData> speakerDataList, Context context) {
        this.speakerDataList = speakerDataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.layout_speaker_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ResponseSpeakerData data = speakerDataList.get(position);
        try {
            holder.year.setText(Integer.toString(data.getYear()));
            holder.name.setText(data.getName());
            if (data.getImage() != null) {
                CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
                progressDrawable.setStrokeWidth(10f);
                progressDrawable.setCenterRadius(90f);
                progressDrawable.start();
                Glide.with(context)
                        .load(data.getImage())
                        .placeholder(progressDrawable)
                        .apply(RequestOptions.circleCropTransform())
                        .into(holder.image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.speakerCardItem.setOnClickListener(view -> {
            try {
                Bundle bundle = new Bundle();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(0, data.getImage());
                arrayList.add(1, data.getName());
                arrayList.add(2, data.getCompany());
                arrayList.add(3, data.getEmail());
                arrayList.add(4, data.getSocialMedia());
                bundle.putStringArrayList("data", arrayList);
                bundle.putInt("year", 2019);
                SpeakerFragment fragment = new SpeakerFragment();
                fragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.es_parent_layout, fragment)
                        .addToBackStack(null)
                        .commit();
            } catch (Exception e) {
                Log.e("ESDetail", e.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return speakerDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name;
        TextView year;
        LinearLayout speakerCardItem;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            year = itemView.findViewById(R.id.speaker_year);
            name = itemView.findViewById(R.id.speaker_name);
            image = itemView.findViewById(R.id.speaker_image);
            speakerCardItem = itemView.findViewById(R.id.speaker_item_card);
        }
    }
}
