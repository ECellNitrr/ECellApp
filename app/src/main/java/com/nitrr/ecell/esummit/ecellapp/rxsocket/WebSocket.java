package com.nitrr.ecell.esummit.ecellapp.rxsocket;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEvent;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventClosed;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventClosing;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventFailure;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventMessage;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.events.SocketEventOpen;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.helpers.SocketLogger;
import com.nitrr.ecell.esummit.ecellapp.rxsocket.helpers.SocketOnSubscribe;

import java.util.concurrent.Callable;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okio.ByteString;

public class WebSocket {

    private okhttp3.WebSocket webSocket = null;

    private final SocketOnSubscribe socketOnSubscribe;
    private PublishProcessor<SocketEvent> socketEventPublishProcessor = PublishProcessor.create();

    private CompositeDisposable disposables = new CompositeDisposable();
    private CompositeDisposable connectionsDisposables = null;

    public WebSocket(@NonNull String connectionURL) {
        this.socketOnSubscribe = new SocketOnSubscribe(connectionURL);
    }

    public WebSocket(@NonNull OkHttpClient client, @NonNull String connectionURL) {
        this.socketOnSubscribe = new SocketOnSubscribe(client, connectionURL);
    }

    private Flowable<SocketEvent> getEventSource() {
        return socketEventPublishProcessor.onErrorResumeNext(throwable -> {
            Log.e("WebSocket", "RxWebSocket EventSubject internal error occured.");
            Log.e("WebSocket", throwable.getMessage());
            throwable.printStackTrace();

            socketEventPublishProcessor = PublishProcessor.create();
            return socketEventPublishProcessor;
        });
    }

    public Flowable<SocketEventOpen> onOpen() {
        return getEventSource().ofType(SocketEventOpen.class).doOnEach(new SocketLogger("onOpen"));
    }

    public Flowable<SocketEventClosed> onClosed() {
        return getEventSource().ofType(SocketEventClosed.class).doOnEach(new SocketLogger("onClosed"));
    }

    public Flowable<SocketEventClosing> onClosing() {
        return getEventSource().ofType(SocketEventClosing.class).doOnEach(new SocketLogger("onClosing"));
    }

    public Flowable<SocketEventFailure> onFailure() {
        return getEventSource().ofType(SocketEventFailure.class).doOnEach(new SocketLogger("onFailure"));
    }

    public Flowable<SocketEventMessage> onMessage() {
        return getEventSource().ofType(SocketEventMessage.class).filter(SocketEventMessage::isEmpty).doOnEach(new SocketLogger("onOpen"));
    }

    public Flowable<SocketEventMessage> onBinaryMessage() {
        return getEventSource().ofType(SocketEventMessage.class).filter(socketEventMessage -> !socketEventMessage.isEmpty()).doOnEach(new SocketLogger("onOpen"));
    }


    /*
     * Methods to setup and close Connections.
     */

    public synchronized void setupConnection() {
        connectionsDisposables = new CompositeDisposable();

        Disposable webSocketInstancesDisposable = getEventSource()
                .ofType(SocketEventOpen.class)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(socketEventOpen -> webSocket = socketEventOpen.getWebSocket(), throwable -> {

                    Log.e("WebSocket", throwable.getMessage());
                    throwable.printStackTrace();
                });

        Disposable connectionDisposable = Flowable.create(socketOnSubscribe, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(socketEvent -> socketEventPublishProcessor.onNext(socketEvent), throwable -> {

                    Log.e("WebSocket", throwable.getMessage());
                    throwable.printStackTrace();
                });

        connectionsDisposables.add(webSocketInstancesDisposable);
        connectionsDisposables.add(connectionDisposable);
        disposables.add(connectionDisposable);
    }

    public synchronized Single<Boolean> closeConnection(){
        return Single.fromCallable(() -> {
            if (webSocket != null) {

                disposables.add(getEventSource()
                        .ofType(SocketEventClosed.class)
                        .subscribe(socketEventClosed -> {

                            connectionsDisposables.clear();
                            disposables.clear();
                        }, Throwable::printStackTrace));

                return webSocket.close(1000, "Socket closed.");

            } else
                throw new RuntimeException("Socket Not Connected.");

        }).doOnSuccess(success -> webSocket = null);
    }

    public synchronized Single<Boolean> closeConnection(int code, @Nullable String reason){
        return Single.fromCallable(() -> {
           if (webSocket != null){

               disposables.add(getEventSource()
               .ofType(SocketEventClosed.class)
               .subscribe(socketEventClosed -> {

                   connectionsDisposables.clear();
                   disposables.clear();

               }, Throwable::printStackTrace));

               return webSocket.close(code, reason);

           } else
               throw new RuntimeException("Socket Not Connected");

        }).doOnSuccess(success -> webSocket = null);
    }


    /*
     * Methods to send Messages.
     */

    public synchronized Single<Boolean> sendMessage(@NonNull Object payload){
        return Single.fromCallable(() -> {
            if (webSocket != null)
                return webSocket.send(new Gson().toJson(payload));

            else
                throw new RuntimeException("Socket not connected.");
        });
    }

    public synchronized Single<Boolean> sendMessage(@NonNull ByteString byteString){
        return Single.fromCallable(() -> {
            if (webSocket != null)
                return webSocket.send(byteString);

            else
                throw new RuntimeException("Socket not connected.");
        });
    }

    public synchronized Single<Boolean> sendMessage(@NonNull String string){
        return Single.fromCallable(() -> {
            if (webSocket != null)
                return webSocket.send(string);

            else
                throw new RuntimeException("Socket not connected.");
        });
    }

}
