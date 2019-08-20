package com.nitrr.ecell.esummit.ecellapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.adapters.EventRecyclerViewAdapter;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventData;
import com.nitrr.ecell.esummit.ecellapp.models.events.EventModel;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventActivity extends BaseActivity {
    private EventModel model;
    private List<EventData> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private DialogInterface.OnClickListener refreshListener = (dialog, which) -> APICall();
    private DialogInterface.OnClickListener cancelListener = (dialog, which) -> {
        dialog.cancel();
        EventActivity.this.finish();
    };
    private AlertDialog dialog;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_event;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView back = findViewById(R.id.event_back);
        back.setOnClickListener(v -> finish());
        recyclerView = findViewById(R.id.event_recycler);
        APICall();
    }

    void APICall() {
        dialog = Utils.showProgressBar(this, "Loading Events...");

        AppClient.getInstance()
                .createService(APIServices.class)
                .getEventDetails(new SharedPref().getAccessToken(EventActivity.this))
                .enqueue(new Callback<EventModel>() {
                    @Override
                    public void onResponse(@NonNull Call<EventModel> call, @NonNull Response<EventModel> response) {
                        if (response.isSuccessful() && getApplicationContext() != null) {
                            Log.e("response", response.toString());
                            model = response.body();
                            if (model != null) {
                                list = model.getList();
                                setRecycler();
                            } else {
                                Utils.showDialog(EventActivity.this, null, false, "Something went wrong", getApplicationContext().getString(R.string.wasnt_able_to_load), "Retry", refreshListener, "Cancel", cancelListener);
                                try {
                                    if (response.errorBody() != null) {
                                        Log.e("event body null====", "error body: " + response.errorBody().string());
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                if (response.errorBody() != null) {
                                    Log.e("error body====::::", response.errorBody().string());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Utils.showDialog(EventActivity.this, null, false, "Something Went wrong",
                                    EventActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener,
                                    "Cancel", cancelListener);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<EventModel> call, @NonNull Throwable t) {
                        if (getApplicationContext() != null) {
                            Utils.showDialog(EventActivity.this, null, false, "Something Went wrong",
                                    EventActivity.this.getString(R.string.wasnt_able_to_load), "Retry", refreshListener,
                                    "Cancel", cancelListener);
                        }
                    }
                });
    }

    private void setRecycler() {
        float increment = ((float)list.size())/100;
        float alpha = increment;
        List<Float> floats = new ArrayList<>();
        for (int x = 0; x < list.size(); x++) {
            if (x % 2 == 1)
                alpha += increment*2;
            floats.add(alpha);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        EventRecyclerViewAdapter adapter = new EventRecyclerViewAdapter(this, list, floats);
        recyclerView.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        HomeActivity.setSelected(false);
        super.onDestroy();
    }
}
