package com.nitrr.ecell.esummit.ecellapp.misc.threads;

import android.os.Process;
import androidx.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

public class PriorityThreadFactory implements ThreadFactory {

    private final int threadPriority;

    PriorityThreadFactory(int threadPriority){
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(@NonNull Runnable r) {
        Runnable wrapperRunnable = () -> {
            Process.setThreadPriority(threadPriority);
            r.run();
        };

        return new Thread(wrapperRunnable);
    }
}
