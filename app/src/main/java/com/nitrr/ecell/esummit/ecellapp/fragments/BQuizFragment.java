package com.nitrr.ecell.esummit.ecellapp.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.WebSocket;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BQuizFragment extends Fragment {

    private WebSocket webSocket;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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


                    //TODO Consume message here.


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
