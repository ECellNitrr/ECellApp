package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.bquiz.BquizAnswerModel;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.WebSocket;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BQuizFragment extends DialogFragment {

    private WebSocket webSocket;
    private TextView response;
    private EditText resText;

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null)
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bquiz, container, false);

        view.findViewById(R.id.connect).setOnClickListener(v -> setUpWebSocket("https://26a0f217.ngrok.io/ws/"));
        view.findViewById(R.id.send).setOnClickListener(v -> sendMessage(new BquizAnswerModel(resText.getText().toString().trim())));

        response = view.findViewById(R.id.response);
        resText = view.findViewById(R.id.res_text);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(webSocket != null)
            webSocket.closeConnection()
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
    }

    @SuppressLint("CheckResult")
    private void setUpWebSocket(String URL) {
        webSocket = new WebSocket(URL);

        webSocket.onOpen()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventOpen -> Utils.showLongToast(getContext(), "B-Quiz is live."), Throwable::printStackTrace);

        webSocket.onClosed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosed -> Utils.showLongToast(getContext(), "B-Quiz closed."), Throwable::printStackTrace);

        webSocket.onClosing()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventClosing -> Utils.showLongToast(getContext(), "B-Quiz is closing."), Throwable::printStackTrace);

        webSocket.onMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventMessage -> {

                    response.setText(socketEventMessage.getMessage());

                }, Throwable::printStackTrace);

        webSocket.onFailure()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(socketEventFailure -> Utils.showLongToast(getContext(), "Some exception occurred. Contact Technical TeamList."),
                        Throwable::printStackTrace);

        webSocket.setupConnection();
    }

    @SuppressLint("CheckResult")
    private void sendMessage(Object message){
        if (webSocket != null){
            webSocket.sendMessage(message)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            success -> Utils.showLongToast(getContext(), "Answer submitted successfully."),
                            throwable -> Utils.showLongToast(getContext(), throwable.getMessage()));

        } else
            throw new RuntimeException("Socket not Initialized.");
    }
}
