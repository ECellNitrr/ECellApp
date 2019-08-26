package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;

import java.util.ArrayList;
import java.util.List;

public class SponsorsFragment extends Fragment {

    private RecyclerView recycler, prevRecycler;
    private SponsorsRecyclerViewAdapter adapter;
    private List<SponsRVData> list2019 = new ArrayList<>();
    private List<SponsRVData> prevlist = new ArrayList<>();
    private TextView prevSponsors;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list2019.clear();
        prevlist.clear();
        Bundle arg = this.getArguments();
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        recycler = view.findViewById(R.id.recycler_spons);
        prevRecycler = view.findViewById(R.id.recycler_prev_spons);
        prevSponsors = view.findViewById(R.id.prev_sponors);
        if (arg != null) {
            intialiseList(arg);
            set2019SponsorsRecyclerView(arg.getInt("position"));
            setPrevSponsorsRecyclerView(arg.getInt("position"));
        }
        return view;
    }

    private void intialiseList(Bundle arguments) {
        String name;
        String img;
        String type;
        String id;
        int year;
        String website;
        int index = arguments.getInt("index");
        int pos = arguments.getInt("position");
        while (index > 0) {
            index--;
            name = arguments.getString("name" + index);
            id = arguments.getString("id" + index);
            type = arguments.getString("type" + index,"");
            img = arguments.getString("image" + index);
            year = arguments.getInt("year" + index);

            website = arguments.getString("website" + index);
            if(year == 2019)
                list2019.add(new SponsRVData(name, id, type, img, year, website));
            else
                prevlist.add(new SponsRVData(name, id, type, img, year, website));

        }
        switch (pos%5) {
            case 0: {
                prevSponsors.setBackgroundResource(R.drawable.spons_cardbg_1);
                break;
            }
            case 1: {
                prevSponsors.setBackgroundResource(R.drawable.spons_cardbg_2);
                break;
            }
            case 2: {
                prevSponsors.setBackgroundResource(R.drawable.spons_cardbg_3);
                break;
            }
            case 3: {
                prevSponsors.setBackgroundResource(R.drawable.spons_cardbg_4);
                break;
            }
            case 4: {
                prevSponsors.setBackgroundResource(R.drawable.spons_cardbg_5);
            }
        }
    }


    private void set2019SponsorsRecyclerView(int i) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(gridLayoutManager);
        recycler.setNestedScrollingEnabled(false);
        adapter = new SponsorsRecyclerViewAdapter(getContext(), list2019, i);
        recycler.setAdapter(adapter);
    }

    private void setPrevSponsorsRecyclerView(int i){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2, RecyclerView.VERTICAL, false);
        prevRecycler.setLayoutManager(gridLayoutManager);
        prevRecycler.setNestedScrollingEnabled(false);
        adapter = new SponsorsRecyclerViewAdapter(getContext(), prevlist, i);
        prevRecycler.setAdapter(adapter);
    }

}
