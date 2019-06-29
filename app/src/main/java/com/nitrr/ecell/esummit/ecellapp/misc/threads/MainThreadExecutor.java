package com.nitrr.ecell.esummit.ecellapp.misc.threads;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

public class MainThreadExecutor implements Executor {

    private final Handler handler = new Handler(Looper.getMainLooper());

    MainThreadExecutor() {
    }

    @Override
    public void execute(@NonNull Runnable command) {
        handler.post(command);
    }
}
