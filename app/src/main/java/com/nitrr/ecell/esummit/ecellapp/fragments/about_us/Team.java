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
    private List<TeamList> list = new ArrayList<>();
    private Call<TeamData> call;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> APICall();

    public Team() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recycler = view.findViewById(R.id.team_recycler);

        APICall();
        return view;
    }

    void APICall() {
        if (!this.isHidden()) {
            APIServices service = AppClient.getInstance().createService(APIServices.class);

            call = service.getTeamData();
            call.enqueue(new Callback<TeamData>() {
                @Override
                public void onResponse(@NonNull Call<TeamData> call, @NonNull Response<TeamData> response) {
                    if (getContext() != null) {
                        if (response.isSuccessful()) {
                            model = response.body();

                            if (model != null) {
                                list = model.getList();
                                setRecyclerView();
                            }
                        } else {
                            Utils.showLongToast(getContext(), "Unable to load data.");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TeamData> call, @NonNull Throwable t) {
                    Utils.showLongToast(getActivity(), "Something went wrong.");

                    Log.e("onFailure", "throwable is " + t.toString());
                    if (getContext() != null)
                        Utils.showDialog(getContext(), null, false, "Something went wrong", getContext().getString(R.string.wasnt_able_to_load), "Retry", refreshListener, null, null);
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
