package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;

import java.util.ArrayList;
import java.util.List;

public class SponsorsFragment extends Fragment {

    private RecyclerView recycler;
    private SponsorsRecyclerViewAdapter adapter;
    private List<SponsRVData> list = new ArrayList<>();

    public SponsorsFragment() {
        // Required empty public constructor
    }

    public SponsorsFragment getInstance(Bundle bundle, int index, int pos) {
        SponsorsFragment fragment = new SponsorsFragment();
        bundle.putInt("index", index);
        bundle.putInt("position", pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        Bundle arg = this.getArguments();
        if (arg != null) {
            intialiselist(arg);
        }
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        recycler = view.findViewById(R.id.spons_recycler);
        if(arg!=null)
            setRecyclerView(arg.getInt("position"));
        return view;
    }

    private void intialiselist(Bundle arguments) {
        String name;
        String img;
        String type;
        String id;
        int year;
        String website;
        int index = arguments.getInt("index");
        int pos = arguments.getInt("position");
        while (index > 0) {
            name = arguments.getString("name" + index);
            id = arguments.getString("id" + index);
            type = arguments.getString("type" + index);
            img = arguments.getString("image" + index);
            year = arguments.getInt("year" + index);
            website = arguments.getString("website" + index);
            list.add(new SponsRVData(name, id, type, img, year, website));
            index--;
        }
    }


    private void setRecyclerView(int i) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new SponsorsRecyclerViewAdapter(getContext(), list, i);
        recycler.setAdapter(adapter);
    }

}
