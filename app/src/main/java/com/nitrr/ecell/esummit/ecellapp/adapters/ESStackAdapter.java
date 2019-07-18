package com.nitrr.ecell.esummit.ecellapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeakerData;

import java.util.ArrayList;
import java.util.List;

public class ESStackAdapter extends ArrayAdapter<ResponseSpeakerData> {
    private List<ResponseSpeakerData> details;
    private Context context;

    public ESStackAdapter(@NonNull Context context, int resource, @NonNull List<ResponseSpeakerData> objects) {
        super(context, resource, objects);
        this.context = context;
        details = new ArrayList<>();
        this.details = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = layoutInflater.inflate(R.layout.es_stack_item, parent, false);
        }
        ResponseSpeakerData speakerObject = details.get(position);
        if (speakerObject != null) {
            TextView textView = itemView.findViewById(R.id.speaker_name);
            ImageView imageView = itemView.findViewById(R.id.speaker_image);

            Glide.with(context).load(speakerObject.getImage())/*.apply(options)*/.into(imageView);
            textView.setText(speakerObject.getName());
        }
        return itemView;
    }


    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public ResponseSpeakerData getItem(int i) {
        return details.get(i);
    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder;
//        if (view == null) {
//            view = inflater.inflate(R.layout.es_stack_item, viewGroup, false);
//            holder = new ViewHolder();
//            holder.image = view.findViewById(R.id.image);
//            holder.name = view.findViewById(R.id.speaker_name);
//            view.setTag(holder);
//        } else {
//            holder = (ViewHolder) view.getTag();
//        }
//        Glide.with(context).load(details.get(i).getData().getImage()).into(holder.image);
//        holder.name.setText(details.get(i).getData().getName());
//        return view;
//    }
//
//    public class ViewHolder {
//        TextView name;
//        ImageView image;
//    }
}
