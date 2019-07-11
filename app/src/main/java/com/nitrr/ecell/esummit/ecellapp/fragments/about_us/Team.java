package com.nitrr.ecell.esummit.ecellapp.fragments.about_us;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.TeamRVAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamData;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamList;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Team extends Fragment {


    private RecyclerView recycler;
    private TeamRVAdapter adapter;
    private TeamData model;
    private List<TeamList> list = new ArrayList<TeamList>();
    private Call<TeamData> call;
//    private DialogInterface.OnClickListener cancellistener = (dialog, which) -> {
//        dialog.cancel();
//        getFragmentManager().beginTransaction().replace(R.id.frame_container,new Aim()).disallowAddToBackStack().commit();
//    };
    private DialogInterface.OnClickListener refreshlistener = (dialog, which) -> APICall();

    public Team() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recycler = view.findViewById(R.id.team_recycler);
        APICall();
        return view;
    }

    void APICall() {
        if(this.isHidden()==false){
        APIServices service = AppClient.getRetrofitInstance();
        call = service.getTeamData();
        call.enqueue(new Callback<TeamData>() {
            @Override
            public void onResponse(Call<TeamData> call, Response<TeamData> response) {
                if (response.isSuccessful()) {
                    model = response.body();
                    if (model != null) {
                        list = model.getList();
                        setRecyclerView();
                    }
                } else
                    Utils.showDialog(getContext(), null, false, "Poor internet connection", getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, null,null);
            }

            @Override
            public void onFailure(Call<TeamData> call, Throwable t) {
                if (!Utils.isNetworkAvailable(getContext()))
                    Utils.showDialog(getContext(), null, false, getContext().getString(R.string.no_internet), getContext().getString(R.string.wasntabletoload), "Retry", refreshlistener, null,null);
                else {
                    Utils.showLongToast(getActivity(), "Something went wrong.");
                    Log.e("onfailure", "throable is " + t.toString());
                    getActivity().finish();
                }
            }
        });
    }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        call.cancel();
    }

    private void setRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new TeamRVAdapter(getContext(), list);
        recycler.setAdapter(adapter);
    }
}
