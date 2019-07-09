package com.nitrr.ecell.esummit.ecellapp.fragments.about_us;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.TeamRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.Sponsors.SponsRVData;

import java.util.ArrayList;
import java.util.List;

public class Team extends Fragment {


    RecyclerView recycler;
    TeamRVAdapter adapter;
    List<SponsRVData> list = new ArrayList<SponsRVData>();

    public Team() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recycler = view.findViewById(R.id.team_recycler);
//        APIServices service = AppClient.getRetrofitInstance();
//        Call<List<SponsRVData>> call =service.getSponsData();
//        call.enqueue(new Callback<List<SponsRVData>>() {
//            @Override
//            public void onResponse(Call<List<SponsRVData>> call, Response<List<SponsRVData>> response) {
//                list=response.body();
//                setRecyclerView();
//            }
//
//            @Override
//            public void onFailure(Call<List<SponsRVData>> call, Throwable t) {
//                Log.e("Retrofit info","Something went wrong! erroe is: "+t);
//            }
//        });
        return view;
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter =new TeamRVAdapter(getContext(),list);
        recycler.setAdapter(adapter);
    }
}
