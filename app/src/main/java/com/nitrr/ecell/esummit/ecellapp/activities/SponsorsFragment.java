package com.nitrr.ecell.esummit.ecellapp.activities;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.SponsRVData;

import java.util.ArrayList;

public class SponsorsFragment extends Fragment {

    RecyclerView recycler;
    SponsorsRecyclerViewAdapter adapter;
    ArrayList<SponsRVData> list = new ArrayList<SponsRVData>();
    String type;


    public SponsorsFragment() {
        // Required empty public constructor
    }

    public static SponsorsFragment newInstance(String title,int position) {

        Bundle args = new Bundle();

        SponsorsFragment fragment = new SponsorsFragment();
        args.putInt("Position",position);
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sponsors,container,false);
        if(savedInstanceState!=null){
        recycler = view.findViewById(R.id.spons_recycler);
        type = savedInstanceState.get("title").toString();
        setList();
        setRecyclerView();
        }

        return view;
    }

    private void setList() {
        SponsRVData data = new SponsRVData("Resonance",type,R.color.colorPrimaryDark,null);
        list.add(data);
        data = new SponsRVData("Saavan",type,R.color.colorBackground,null);
        list.add(data);
        data = new SponsRVData("Nesley",type,R.color.colorPrimary,null);
        list.add(data);

    }

    void setRecyclerView(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter =new SponsorsRecyclerViewAdapter(getContext(),list);
        recycler.setAdapter(adapter);
    }

}
