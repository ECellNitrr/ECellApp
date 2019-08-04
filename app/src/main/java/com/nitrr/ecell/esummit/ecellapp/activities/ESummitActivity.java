package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.ESummitRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeaker;
import com.nitrr.ecell.esummit.ecellapp.models.speakers.ResponseSpeakerData;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ESummitActivity extends BaseActivity {

    private List<ResponseSpeakerData> responseSpeakerObjectList;
    private RecyclerView speakerRV;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> callAPI();
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();
        ESummitActivity.this.finish();
    };

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_esummit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        speakerRV = findViewById(R.id.es_speaker_rv);
        ImageView back = findViewById(R.id.esummit_back);
        back.setOnClickListener(v -> finish());
        responseSpeakerObjectList = new ArrayList<>();
        findViewById(R.id.es_nested_sv).scrollTo(0, 0);
        TextView date = findViewById(R.id.e_summit_date);
        date.setText(setESDate());
        callAPI();
    }

    public void callAPI() {
        Call<ResponseSpeaker> call = AppClient.getInstance().createService(APIServices.class).getSpeakerList(getString(R.string.app_access_token));
        call.enqueue(new Callback<ResponseSpeaker>() {
            @Override
            public void onResponse(@NonNull Call<ResponseSpeaker> call, @NonNull Response<ResponseSpeaker> response) {
                if (response.isSuccessful() && getApplicationContext() != null) {
                    Log.e("response", response.toString());
                    if (response.body() == null)
                        Log.e("ES Speaker List", "response body null");
                    else {
                        ResponseSpeaker data = response.body();
                        responseSpeakerObjectList = data.getList();
                        ESummitRecyclerViewAdapter adapter = new ESummitRecyclerViewAdapter(responseSpeakerObjectList, ESummitActivity.this);
                        speakerRV.setAdapter(adapter);
                        speakerRV.setLayoutManager(new LinearLayoutManager(ESummitActivity.this));
                        adapter.notifyDataSetChanged();
                        Log.e("ESummitActivity Data", "list size is" + responseSpeakerObjectList.size());
                    }
                } else {
                    try {
                        if (response.errorBody() != null) {
                            Log.e("ES Speaker List", response.errorBody().string());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseSpeaker> call, @NonNull Throwable t) {
                if (getApplicationContext() != null) {
                    if (!Utils.isNetworkAvailable(getApplicationContext()))
                        Utils.showDialog(ESummitActivity.this, null, false, "Poor Internet Connection", getApplicationContext().getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                    else {
                        Log.e("Failure: ===", "throwable is " + t);
                        Utils.showLongToast(getApplicationContext(), "Something went wrong.");
                    }
                }
            }
        });
    }

    public String setESDate() {
        SharedPref pref = new SharedPref();
        String startDate = pref.getDates(this)[0];
        String endDate = pref.getDates(this)[1];
        return startDate.split("-")[2] + " " + getMonth(startDate) +
                " & " + endDate.split("-")[2] + " " + getMonth(endDate) +
                ", " + startDate.split("-")[0] +
                " | NIT Raipur";
    }

    private String getMonth(String date) {
        switch (date.split("-")[1]) {
            case "01":
                return "Jan";
            case "02":
                return "Feb";
            case "03":
                return "March";
            case "04":
                return "April";
            case "05":
                return "May";
            case "06":
                return "June";
            case "07":
                return "July";
            case "08":
                return "Aug";
            case "09":
                return "Sept";
            case "10":
                return "Oct";
            case "11":
                return "Nov";
            case "12":
                return "Dec";
            default:
                return null;
        }
    }

    @Override
    protected void onDestroy () {
        HomeActivity.setSelected(false);
        super.onDestroy();
    }
}