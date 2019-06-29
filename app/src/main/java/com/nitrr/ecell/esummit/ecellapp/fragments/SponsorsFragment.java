package com.nitrr.ecell.esummit.ecellapp.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.models.SponsRVData;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SponsorsFragment extends Fragment {

    RecyclerView recycler;
    SponsorsRecyclerViewAdapter adapter;
    List<SponsRVData> list = new ArrayList<SponsRVData>();
    static String type;


    public SponsorsFragment() {
        // Required empty public constructor
    }

    public static SponsorsFragment newInstance(String title,int position) {

        Bundle args = new Bundle();

        SponsorsFragment fragment = new SponsorsFragment();
        args.putInt("Position",position);
        type = title;
        args.putString("title",title);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        View view=inflater.inflate(R.layout.fragment_sponsors,container,false);
        recycler = view.findViewById(R.id.spons_recycler);
        APIServices service = AppClient.getRetrofitInstance();
        Call<List<SponsRVData>> call =service.getAllPhotos();
        call.enqueue(new Callback<List<SponsRVData>>() {
            @Override
            public void onResponse(Call<List<SponsRVData>> call, Response<List<SponsRVData>> response) {
                list=response.body();
                setRecyclerView();
            }

            @Override
            public void onFailure(Call<List<SponsRVData>> call, Throwable t) {
                Log.e("Retrofit info","Something went wrong! erroe is: "+t);
            }
        });
        return view;
    }

    private void setList() {
        SponsRVData data = new SponsRVData("Resonance",type,R.drawable.spons_cardbg_1,null);
        list.add(data);
        data = new SponsRVData("Saavan",type,R.drawable.spons_cardbg_2,null);
        list.add(data);
        data = new SponsRVData("Nesley",type,R.drawable.spons_cardbg_3,null);
        list.add(data);

    }

    void setRecyclerView(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter =new SponsorsRecyclerViewAdapter(getContext(),list);
        recycler.setAdapter(adapter);
    }

}
