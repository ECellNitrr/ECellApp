package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.R;

import java.util.Objects;

public class EventDataFragment extends Fragment {

    public EventDataFragment getInstance(Bundle bundle, int pos) {
        EventDataFragment fragment = new EventDataFragment();
        bundle.putInt("position",pos);
        fragment.setArguments(bundle);
        return fragment;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arg = this.getArguments();

        View view = inflater.inflate(R.layout.layout_event_details, container, false);
        TextView text = view.findViewById(R.id.event_text);
        TextView url = view.findViewById(R.id.event_url);
        if(arg!=null){
            text.setText(arg.getString("event_details"),null);
        }
        Spanned link = Html.fromHtml(Objects.requireNonNull(getContext()).getString(R.string.read_more), Html.FROM_HTML_MODE_LEGACY);
        url.setText(link);
        Bundle bundle = getArguments();
        if(bundle!=null)
            if(bundle.getString("url")!=null)
                url.setVisibility(View.GONE);
        url.setOnClickListener((v) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("url")));
            startActivity(intent);
        });
        return view;
    }

}
