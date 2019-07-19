package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.fragments.SpeakerDetail;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeakerData;

import java.util.ArrayList;
import java.util.List;

public class ESRVAdapter extends RecyclerView.Adapter<ESRVAdapter.MyViewHolder> {
    private List<ResponseSpeakerData> speakerDataList;
    private Context context;
    private LayoutInflater inflater;

    public ESRVAdapter(List<ResponseSpeakerData> speakerDataList, Context context) {
        this.speakerDataList = speakerDataList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.es_rv_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ResponseSpeakerData data = speakerDataList.get(position);
        holder.name.setText(data.getName());
        holder.year.setText(data.getYear());
        try {
            Glide.with(context).load(data.getImage()).apply(RequestOptions.circleCropTransform()).into(holder.image);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.image.setOnClickListener(view -> {
            try {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(0, data.getCompany());
                arrayList.add(1, data.getEmail());
                arrayList.add(2, data.getContact());
                arrayList.add(3, data.getSocialMedia());
                arrayList.add(4, data.getName());
                arrayList.add(5, data.getImage());
                Intent i = new Intent(context, SpeakerDetail.class);
                i.putStringArrayListExtra("data", arrayList);
                i.putExtra("year", data.getYear());
                context.startActivity(i);
            } catch (Exception e) {
                Log.e("ESDetail", e.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return speakerDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;
        TextView year;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.detail_speaker_name);
            image = itemView.findViewById(R.id.speaker_image);
            year = itemView.findViewById(R.id.speaker_year);
        }
    }
}
