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
import com.nitrr.ecell.esummit.ecellapp.adapters.SponsorsRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.Sponsors.SponsRVData;
import com.nitrr.ecell.esummit.ecellapp.models.Sponsors.SponsorsModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sponsors extends Fragment {

    com.nitrr.ecell.esummit.ecellapp.activities.Sponsors sponsors;
    private static String type;
    private RecyclerView recycler;
    private SponsorsRecyclerViewAdapter adapter;
    private SponsorsModel model;
    private List<SponsRVData> list = new ArrayList<SponsRVData>();
    private DialogInterface.OnClickListener cancellistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            getActivity().finish();
        }
    };
    private DialogInterface.OnClickListener refreshlistener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            APICall();
        }
    };
    public Sponsors() {
        // Required empty public constructor
    }

    public static Sponsors newInstance(int position) {

        Bundle args = new Bundle();

        Sponsors fragment = new Sponsors();
        args.putInt("Position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        list.clear();
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);
        recycler = view.findViewById(R.id.spons_recycler);
        APICall();
        return view;
    }

    void APICall() {
        APIServices service = AppClient.getInstance().createService(APIServices.class);
        Call<SponsorsModel> call = service.getSponsData();
        call.enqueue(new Callback<SponsorsModel>() {
            @Override
            public void onResponse(Call<SponsorsModel> call, Response<SponsorsModel> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    if (model != null) {
                        list = model.getList();
                        setRecyclerView();
                    } else {
                        Log.e("response list empty", "response is empty and is: " + response.toString());
                    }
                } else {
                    Log.e("response failure", "resoponse is " + response.toString());
                    Utils.showDialog(getContext(), null, false, "Server Down", getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, "Cancel", cancellistener);
                }
            }

            @Override
            public void onFailure(Call<SponsorsModel> call, Throwable t) {
                if (!Utils.isNetworkAvailable(getContext()))
                    Utils.showDialog(getContext(), null, false, getContext().getString(R.string.no_internet), getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, "Cancel", cancellistener);
                else {
                    Utils.showToast(getActivity(), "Something went wrong.");
                    Log.e("onfailure", "throable is " + t.toString());
                    getActivity().finish();
                }
            }
        });
    }


    void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new SponsorsRecyclerViewAdapter(getContext(), list);
        recycler.setAdapter(adapter);
    }

}
