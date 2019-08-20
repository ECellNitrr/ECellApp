package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.BquizLeaderBoardRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizLeaderBoardResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoardFragment extends DialogFragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerViewLeaderBoard;
    private BquizLeaderBoardRecyclerViewAdapter leaderBoardAdapter;


    public LeaderBoardFragment() {
    }

    public static LeaderBoardFragment newInstance() {
        LeaderBoardFragment fragment = new LeaderBoardFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar_Fullscreen);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leader_board, container, false);
        recyclerViewLeaderBoard = view.findViewById(R.id.rv_leader_board);
        progressBar = view.findViewById(R.id.leader_progress_bar);

        setUpLeaderboardRecyclerView();
        apiCallForBquizLeaderboard();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog.getWindow() != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void setUpLeaderboardRecyclerView() {
        leaderBoardAdapter = new BquizLeaderBoardRecyclerViewAdapter(getContext());
        recyclerViewLeaderBoard.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLeaderBoard.setAdapter(leaderBoardAdapter);
    }

    private void apiCallForBquizLeaderboard() {
        APIServices services = AppClient.getInstance().createService(APIServices.class);
        Call<BquizLeaderBoardResponse> responseCall = services.getLeaderBoard(new SharedPref().getAccessToken(getActivity()));

        responseCall.enqueue(new Callback<BquizLeaderBoardResponse>() {
            @Override
            public void onResponse(Call<BquizLeaderBoardResponse> call, Response<BquizLeaderBoardResponse> response) {
                if (response.isSuccessful()) {
                    BquizLeaderBoardResponse jsonResponse = response.body();

                    if (jsonResponse != null) {
                        Log.e("BQ", jsonResponse.toString() + " ");
                        progressBar.setVisibility(View.GONE);

                        leaderBoardAdapter.setLeaderboardUserDetailsList(jsonResponse.getLeaderboard());
                        leaderBoardAdapter.notifyDataSetChanged();
                    }
                } else if (getContext() != null) {
                    Toast.makeText(getContext(), "Something went wrong, Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BquizLeaderBoardResponse> call, Throwable t) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Leaderboard isn't available at this moment.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
