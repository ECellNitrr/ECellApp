package com.nitrr.ecell.esummit.ecellapp.fragments.aboutUs;

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
import com.nitrr.ecell.esummit.ecellapp.adapters.TeamRecyclerHeadingAdapter;
import com.nitrr.ecell.esummit.ecellapp.adapters.TeamRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamData;
import com.nitrr.ecell.esummit.ecellapp.models.team.TeamList;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Team extends Fragment {

    private RecyclerView recycler;
    private TeamRecyclerHeadingAdapter adapter;
    private TeamData model;
    private List<TeamList> list = new ArrayList<>();
    private int[] size = new int[7];
    private Call<TeamData> call;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> APICall();

    public Team() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        recycler = view.findViewById(R.id.team_recycler);
        if (list.isEmpty()) {
            APICall();
        }
        return view;
    }

    private void APICall() {
        if (!this.isHidden()) {
            APIServices service = AppClient.getInstance().createService(APIServices.class);

            call = service.getTeamData("2019");
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
                            try {
                                Log.e("Teamerrorbody",response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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

        List<TeamList> faculty = new ArrayList<>(),
                oc = new ArrayList<>(),
                tech = new ArrayList<>(),
                pr = new ArrayList<>(),
                spons = new ArrayList<>(),
                design = new ArrayList<>(),
                doc = new ArrayList<>();
        //Sorting as per domain
        for(int x=0; x<list.size();x++){
            TeamList member = list.get(x);
            if(member.getDomain().contentEquals("none"))
                if(member.getType().contentEquals("OCO"))
                    oc.add(member);
                else
                    faculty.add(member);
            else if(member.getDomain().contentEquals("tech"))
                tech.add(member);
            else if(member.getDomain().contentEquals("doc"))
                doc.add(member);
            else if(member.getDomain().contentEquals("pr"))
                pr.add(member);
            else if(member.getDomain().contentEquals("spons"))
                spons.add(member);
            else if(member.getDomain().contentEquals("design"))
                design.add(member);
        }

        list.clear();

        faculty = sortList(faculty);
        size[0] = faculty.size();
        list.addAll(faculty);

        oc = sortList(oc);
        size[1] = size[0]+oc.size();
        list.addAll(oc);

        tech = sortList(tech);
        size[2] = size[1]+tech.size();
        list.addAll(tech);

        design = sortList(design);
        size[3] = size[2]+design.size();
        list.addAll(design);

        spons = sortList(spons);
        size[4] = size[3]+spons.size();
        list.addAll(spons);

        pr = sortList(pr);
        size[5] = size[4]+pr.size();
        list.addAll(pr);

        doc = sortList(doc);
        size[6] = size[5]+doc.size();
        list.addAll(doc);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(linearLayoutManager);
        adapter = new TeamRecyclerHeadingAdapter(getContext(), list,size);
        recycler.setAdapter(adapter);

//        final boolean[] loading = {true};
//        final int[] pastVisibleItems = new int[1];
//        final int[] visibleItemsCount = new int[1];
//        final int[] totalItemCount = new int[1];
//        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                if(dy > 0){
//                    visibleItemsCount[0] = linearLayoutManager.getChildCount();
//                    totalItemCount[0] = linearLayoutManager.getItemCount();
//                    pastVisibleItems[0] = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
//
//                    if(loading[0]){
//                        if((visibleItemsCount[0] + pastVisibleItems[0]) >= totalItemCount[0]){
//                            loading[0] = false;
//                            Log.e("...","LastItemNow");
//                        }
//                    }
//                    if(!loading[0] && (totalItemCount[0] - visibleItemsCount[0]) <= (pastVisibleItems[0] + 5)){
//                        Log.e("loading false", "element changed");
//                        loading[0] = true;
//                    }
//
//                }
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }

    List<TeamList> sortList(List<TeamList> list){
        List<TeamList> DIR = new ArrayList<>(),
                HCD  = new ArrayList<>(),/*(Head of Career Dapartment)*/
                FCT  = new ArrayList<>(),/*(faculty incharge)*/
                MNG  = new ArrayList<>(),
                HCO  = new ArrayList<>(),
                OCO  = new ArrayList<>(),
                EXC  = new ArrayList<>();
        for(int x=0; x<list.size();x++){
            TeamList member = list.get(x);
            if(member.getType().contentEquals("HCD"))
                HCD.add(member);
            else if(member.getType().contentEquals("DIR"))
                DIR.add(member);
            else if(member.getType().contentEquals("FCT"))
                FCT.add(member);
            else if(member.getType().contentEquals("MNG"))
                MNG.add(member);
            else if(member.getType().contentEquals("HCO"))
                HCO.add(member);
            else if(member.getType().contentEquals("OCO"))
                OCO.add(member);
            else if(member.getType().contentEquals("EXC"))
                EXC.add(member);
        }

        list.clear();
        list.addAll(DIR);
        list.addAll(HCD);
        list.addAll(FCT);
        list.addAll(OCO);
        list.addAll(HCO);
        list.addAll(MNG);
        list.addAll(EXC);
        return list;
    }
}
