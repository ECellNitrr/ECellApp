package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.activities.SponsorsActivity;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsRVData;
import com.nitrr.ecell.esummit.ecellapp.models.sponsors.SponsorsModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorsFragment extends Fragment {

    private static String type;
    private RecyclerView recycler;
    private SponsorsRecyclerViewAdapter adapter;
    private List<SponsRVData> list = new ArrayList<SponsRVData>();

    public SponsorsFragment() {
        // Required empty public constructor
    }

    public static SponsorsFragment newInstance(Bundle bundle,int pos) {
        SponsorsFragment fragment = new SponsorsFragment();
        bundle.putInt("index",pos);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        intialiselist(this.getArguments());
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        recycler = view.findViewById(R.id.spons_recycler);
        setRecyclerView();
        return view;
    }

    private void intialiselist(Bundle arguments) {
        String name;
        String img;
        String type;
        String id;
        int index = arguments.getInt("index");
        while (index>0){
            name = arguments.getString("name"+index);
            id = arguments.getString("id"+index);
            type = arguments.getString("type"+index);
            img = arguments.getString("image"+index);
            list.add(new SponsRVData(name,id,type,img));
            index--;
        }
    }


    void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new SponsorsRecyclerViewAdapter(getContext(), list);
        recycler.setAdapter(adapter);
    }

}
